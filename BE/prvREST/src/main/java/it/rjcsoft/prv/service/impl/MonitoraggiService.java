package it.rjcsoft.prv.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.querydsl.core.types.Predicate;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import it.rjcsoft.prv.config.PrvRestConfig;
import it.rjcsoft.prv.dto.monitoraggi.MonitoraggiFullDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.Azienda;
import it.rjcsoft.prv.model.FileMonitoraggio;
import it.rjcsoft.prv.model.HtmlMail;
import it.rjcsoft.prv.model.Monitoraggi;
import it.rjcsoft.prv.model.QMonitoraggi;
import it.rjcsoft.prv.model.Utente;
import it.rjcsoft.prv.repository.IAziendaRepository;
import it.rjcsoft.prv.repository.IFileMonitoraggioRepository;
import it.rjcsoft.prv.repository.IMonitoraggiRepository;
import it.rjcsoft.prv.repository.IUtenteRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IAziendaService;
import it.rjcsoft.prv.service.IMonitoraggiService;
import it.rjcsoft.prv.utils.PrvConverterUtils;
import it.rjcsoft.prv.utils.PrvFileUtils;
import it.rjcsoft.prv.utils.PrvMailUtils;

@Service
public class MonitoraggiService extends BaseService implements IMonitoraggiService {

    @Autowired
    private IMonitoraggiRepository monitoraggioRepository;

    @Autowired
    private PrvRestConfig prvRestConfig;

    @Autowired
    private IFileMonitoraggioRepository filemonitoraggioRepository;

    @Autowired
    private HtmlMailService htmlMailService;

    @Autowired
    private IAziendaService aziendaService;

    @Autowired
    private IAziendaRepository aziendaRepository;

    @Autowired
    private IUtenteRepository utenteRepository;

    @Override
    public Integer nuovoMonitoraggio(MonitoraggiFullDTO monitoringDTO) {
        log.info("START - nuovoMonitoraggio params= {} ", monitoringDTO);
        Monitoraggi modelMonitoraggio = new Monitoraggi();

        if (monitoringDTO.getLongitude() != null && monitoringDTO.getLatitude() != null) {
            double lon = monitoringDTO.getLongitude().doubleValue();
            double lat = monitoringDTO.getLatitude().doubleValue();

            log.debug("Creating point lat={}, lon={}", lat, lon);

            GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

            Coordinate coord = new Coordinate(lon, lat);
            Point point = geometryFactory.createPoint(coord);
            point.setSRID(4326);
            log.info("Point={}", point);
            modelMonitoraggio.setGeometry(point);

            try {
                PrvConverterUtils.copyPropertiesNotNull(modelMonitoraggio, monitoringDTO);
                monitoraggioRepository.save(modelMonitoraggio);
                log.info("Monitoraggio SAVED");
                return modelMonitoraggio.getId();

            } catch (Exception e) {
                log.warn("ERROR - Monitoraggio not Saved");
            }
        }
        return null;
    }

    @Override
    public Page<MonitoraggiFullDTO> getAll(Predicate predicate, Pageable pageable) {
        log.debug("START - predicate{}, pageable{}", predicate, pageable);
        Page<Monitoraggi> monitoraggiPage = monitoraggioRepository.findAll(predicate, pageable);
        log.trace("monitoraggiPage={}", monitoraggiPage.getContent());
        Page<MonitoraggiFullDTO> responsePage = monitoraggiPage.map(currMonitoring -> {
            MonitoraggiFullDTO dto = new MonitoraggiFullDTO();
            PrvConverterUtils.copyProperties(dto, currMonitoring);
            if (dto != null) {
                return dto;
            }
            return null;
        });

        return responsePage;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> deleteById(int id) throws BaseEx {
        // devo eliminare anche tutti i file ad esso collegati!!!
        log.debug("START - delete Monitoraggio={}", id);
        boolean error = false;
        if (monitoraggioRepository.existsById(id))
            try {
            List<String> filename = getFileNamesById(id);
            if (!filename.isEmpty()) {
                for (String file : filename) {
                    try {
                        Boolean delete = deleteFileByNameId(file, id);
                        log.info("Eliminato -> {}", delete);
                    } catch (Exception e) {
                        log.error("STOP - eliminazione file non riuscita, causa: {}", e.getCause());
                        error = true;
                        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
                    }
                }
            }
            if (!error) {
                monitoraggioRepository.deleteById(id);
                log.info("END - Monitoraggio Eliminato");
                return ResponseEntity.ok().build();
            }

        } catch (Exception e) {
            log.error("END - not deleted monitoraggio with id = {}", id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public ResponseEntity<Object> updateMonitoraggio(MonitoraggiFullDTO monitoringDTO) {
        log.debug("START - update {}", monitoringDTO);
        Integer id = monitoringDTO.getId();
        Monitoraggi monitoraggio = monitoraggioRepository.findById(id).get();
        if (monitoraggio == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else
            try {
            if (PrvConverterUtils.copyPropertiesNotNull(monitoraggio, monitoringDTO)) {
                log.info("Model - Monitoraggio= {}", monitoraggio);
                if (monitoringDTO.getLongitude() != null && monitoringDTO.getLatitude() != null) {
                    double lon = monitoringDTO.getLongitude().doubleValue();
                    double lat = monitoringDTO.getLatitude().doubleValue();

                    log.debug("Creating point lat={}, lon={}", lat, lon);

                    GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

                    Coordinate coord = new Coordinate(lon, lat);
                    Point point = geometryFactory.createPoint(coord);
                    point.setSRID(4326);
                    log.info("Point={}", point);
                    monitoraggio.setGeometry(point);
                }
                monitoraggio = monitoraggioRepository.save(monitoraggio);
                log.info("UPDATED");
                return ResponseEntity.ok().build();
            }
        } catch (Exception e) {

            log.error("ERRORE salvataggio", e);
        }
        log.warn("END copyProperties FAILED");
        return ResponseEntity.noContent().build();
    }

    // Si può implementare direttamente nel controller?
    @Override
    public File getZipFile(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
        // Tools | Templates.
    }

    // dal repo di FileMonitoraggi
    @Override
    public List<String> getFileNamesById(int id) {
        List<String> listafilename = new ArrayList<String>();
        List<FileMonitoraggio> files = filemonitoraggioRepository.findByIdMonitoring(id);
        Monitoraggi company = monitoraggioRepository.findById(id).get();
        files.forEach(file -> {
            if (file != null) {
                listafilename.add(file.getFileName());
            }
        });
        return listafilename;
    }

    @Override
    @Transactional
    public Boolean deleteFileByNameId(String fileName, int id) {
        // id è quello delmonitoring e da questo devo ricavarmi quello del companys
        log.info("START - deleteFileByNameId");
        Monitoraggi company = monitoraggioRepository.findById(id).get();
        try {
            PrvFileUtils.deleteFile(
                    new File(prvRestConfig.getMonitoringBasePath() + company.getCompanyId() + "/" + fileName));
            log.info("id={} e fileNAme={}", id, fileName);
            FileMonitoraggio fileMonitoraggio = filemonitoraggioRepository.findByIdMonitoringAndFileName(id, fileName);
            filemonitoraggioRepository.deleteById(fileMonitoraggio.getId());
            log.info("i -> {}\nexist? -> {}", fileMonitoraggio.getId(), fileMonitoraggio);
            return true;
        } catch (Exception e) {
            log.error("NOT DELETED filename = {} e idmonit={}, messaggio={}", fileName, id, e.getCause());
            return false;
        }
    }

    @Override
    public String saveFile(MultipartFile file) {
        try {
            PrvFileUtils.saveFile(file, prvRestConfig.getMonitoringBasePath());
            return file.getName();
        } catch (IOException e) {
            log.warn("File non salvato");
        }
        return null;
    }

    // Registrare il file monitoraggio
    @Override
    public Boolean registerFile(Integer id, List<String> fileNames) {
        log.info("START - registerFile");
        for (String file : fileNames) {
            FileMonitoraggio filemon = new FileMonitoraggio();
            filemon.setFileName(file);
            filemon.setIdMonitoring(id);
            filemon.setProcessed(Boolean.FALSE);
            filemon = filemonitoraggioRepository.save(filemon);
            if (filemon == null) {
                return false;
            }
        }
        log.info("File Registrati su id={}", id);
        return true;
    }

    // service che dato id del monitoraggio e nome del file mi restituisce il file
    public File getFileByNameAndId(Integer id, String fileName) {
        log.info("START - GetFileByNameAndID");
        Integer company = null;
        File file = null;
        Monitoraggi monitor = monitoraggioRepository.findById(id).get();
        if (monitor != null) {
            company = monitor.getCompanyId();
        }

        File folder = new File(prvRestConfig.getMonitoringBasePath(), company.toString());
        file = new File(folder, fileName);
        if (!file.exists()) {
            file = null;
        }
        return file;
    }

    // Da finire di implementare ma bisogna sistemare anche company etc...
    @Override
    public boolean sendMonitoringMail(MonitoraggiFullDTO monitoringDTO, String action) {
        if (monitoringDTO == null) {
            return false;
        }
        HtmlMail htmlMail = null;
        try {
            htmlMail = htmlMailService.fetchHtmlMail(PrvMailUtils.MONITORING, PrvMailUtils.getLanguage());
        } catch (PojoNotFound e) {
            log.error("END - Html mail not found");
            return false;
        }
        if (htmlMail == null) {
            return false;
        }
        Azienda azienda = aziendaRepository.findById(monitoringDTO.getCompanyId()).get();
        String companyName;
        String userName = monitoringDTO.getPerformedBy();
        if (azienda != null) {
            companyName = azienda.getBusinessName();

        } else {
            return false;
        }
        String baseUrl = prvRestConfig.getBaseUrl();
        String extra_info = "";
        if (companyName != null) {
            extra_info = extra_info.concat(String.format("Azienda: %s<br>", companyName));
        }
        extra_info = extra_info.concat(String.format("Utente: %s<br>", userName));

        // Utilizzato solo per verificare che esistano degli admin
        List<Utente> adminEmails = utenteRepository.findByCompanyIdAndRoleValue(monitoringDTO.getCompanyId(), "ADMIN");
        if (adminEmails.isEmpty()) {
            log.warn("No admins found");
            return false;
        }
        Map<String, String> placeholders = PrvMailUtils.initPlaceholders(PrvMailUtils.MONITORING, baseUrl, action,
                extra_info);
        htmlMailService.initBody(htmlMail, placeholders);
        //Tento di inviare la mail a tutti gli admin
        //List<String> mails=new ArrayList<>();
        //for(Utente utent : adminEmails)
        //{
        //    mails.add(utent.getEmail());
        //}
        //String[] mail=mails.toArray(String[]::new);
        //htmlMailService.sendHtmlEmail(htmlMail, mail);
        return true;

    }

    @Override
    public Integer companyByMonitoringId(int id) {
        Monitoraggi mon = monitoraggioRepository.findById(id).get();
        if (mon != null) {
            return mon.getCompanyId();
        } else {
            return null;
        }
    }

    @Override
    public long getTotalNumber() {
        log.info("START - getting total number of monitorings");

        return monitoraggioRepository.count();
    }

    public List<MonitoraggiFullDTO> getMonitoringByIds(int[] ids, int companyId) {
        List<MonitoraggiFullDTO> toReturn = new ArrayList<>();

        //Controllo se l'id passato sia diverso o uguale a 0! se = 0 significa che la richiesta è stata fatta da un admin
        List<Monitoraggi> monitoraggi;
        if (companyId != 0) {
            monitoraggi = monitoraggioRepository.findByCompanyId(companyId).get();
        } else {
            monitoraggi = monitoraggioRepository.findAll();
        }
        log.debug("MONITORAGGI ={}", monitoraggi);
        if (!monitoraggi.isEmpty()) {

            List<Integer> list = Arrays.stream(ids).boxed().collect(Collectors.toList());
            QMonitoraggi monitoraggiEntity = QMonitoraggi.monitoraggi;
            Predicate orIds;
            //cerco tra tutti i monitoraggi di un azienda oppuretra tutti i monitoraggi!
            if (companyId != 0) {
                orIds = monitoraggiEntity.companyId.eq(companyId).and(monitoraggiEntity.id.in(list));
            } else {
                orIds = monitoraggiEntity.id.in(list);
            }
            log.info("predicate={}", orIds);
            Iterable<Monitoraggi> mon = monitoraggioRepository.findAll(orIds);

            for (Monitoraggi m : mon) {
                MonitoraggiFullDTO MFD = new MonitoraggiFullDTO();
                PrvConverterUtils.copyProperties(MFD, m);
                toReturn.add(MFD);
            }
            log.info("monitoraggi={}", toReturn);
        }
        return toReturn;
    }
}
