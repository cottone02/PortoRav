package it.rjcsoft.prv.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import it.rjcsoft.prv.config.PrvRestConfig;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.ProdottiAllegati;
import it.rjcsoft.prv.repository.IProdottiAllegatiRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IProdottiAllegatiService;
import it.rjcsoft.prv.utils.PrvFileUtils;

@Service
public class ProdottiAllegatiService extends BaseService implements IProdottiAllegatiService {

    @Autowired
    private IProdottiAllegatiRepository prodottiAllegatiRepository;

    @Autowired
    private PrvRestConfig prvRestConfig;

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public void save(Integer schedaId, MultipartFile[] files) throws Exception {

        log.debug("START - saving nFiles={} for censimentoProdotti={}", files.length, schedaId);

        File directory = new File(prvRestConfig.getProdottiAllegatiBasePath(), String.format("%d", schedaId));

        try {
            if (!directory.exists()) {
                Files.createDirectories(directory.toPath());
            }
            List<ProdottiAllegati> censimentiProdottiAllegati = new ArrayList<>();
            for (MultipartFile file : files) {
                File fileSaved = PrvFileUtils.saveFile(file, directory.getAbsolutePath());
                ProdottiAllegati prodottiAllegati = new ProdottiAllegati();
                prodottiAllegati.setSchedaId(schedaId);
                prodottiAllegati.setNomeFile(fileSaved.getName());
                censimentiProdottiAllegati.add(prodottiAllegati);
            }
            prodottiAllegatiRepository.saveAll(censimentiProdottiAllegati);
            log.info("END - saved censimentiProdottiAllegati={}", StringUtils.join(censimentiProdottiAllegati));
        } catch (Exception e) {
            log.error("END - Not saved directory={}, e={}, schedaId={}", directory.getAbsolutePath(), e.getMessage(),
                    schedaId);
            throw e;
        }
    }

    @Override
    public File downloadFileById(Integer id) throws BaseEx {

        log.debug("START - censimentoProdottoAllegatoId={}", id);
        ProdottiAllegati prodottiAllegati = prodottiAllegatiRepository.findById(id)
                .orElseThrow(() -> new PojoNotFound("CensimentoProdottoAllegato with id " + id + " not found"));

        if (prodottiAllegati != null) {
            String basePath = String.format("%s%d", prvRestConfig.getProdottiAllegatiBasePath(),
                    prodottiAllegati.getSchedaId());
            File fileToDownload = new File(basePath, prodottiAllegati.getNomeFile());
            if (fileToDownload.exists()) {
                log.info("END - found file={}", fileToDownload.getAbsolutePath());
                return fileToDownload;
            } else {
                log.error("END - FILE id={} NOT FOUND", id);
                return null;
            }
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) throws BaseEx {
        log.debug("START - delete ProdottoAllegato id={}", id);

        ProdottiAllegati prodottiAllegati = prodottiAllegatiRepository.findById(id)
                .orElseThrow(() -> new PojoNotFound("ProdottoAllegato with id " + id + " not found"));
        try {
            prodottiAllegatiRepository.deleteById(id);
            String basePath = String.format("%s%d", prvRestConfig.getProdottiAllegatiBasePath(),
                    prodottiAllegati.getSchedaId());
            File file = new File(basePath, prodottiAllegati.getNomeFile());
            PrvFileUtils.deleteFile(file);
            log.info("END - deleted {}", id);
        } catch (Exception e) {
            log.error("END - not deleted {}, message={}", id, e.getMessage(), e);
            throw new InternalError("ProdottoAllegato id=" + id + " non deleted");
        }
    }

    @Override
    public void deleteBySchedaId(Integer schedaId) throws BaseEx {
        log.debug("START - delete ProdottoAllegato schedaId={}", schedaId);

        List<ProdottiAllegati> prodottiAllegatis = prodottiAllegatiRepository.findBySchedaId(schedaId)
                .orElseThrow(() -> new PojoNotFound("ProdottoAllegato with schedaId " + schedaId + " not found"));

        if (CollectionUtils.isEmpty(prodottiAllegatis)) {
            log.warn("END - No prodottiAllegati found with schedaId={}", schedaId);
            return;
        }

        try {
            prodottiAllegatiRepository.deleteAll(prodottiAllegatis);
            String basePath = String.format("%s%d", prvRestConfig.getProdottiAllegatiBasePath(), schedaId);
            prodottiAllegatis.stream()
                    .forEach(allegato -> PrvFileUtils.deleteFile(new File(basePath, allegato.getNomeFile())));
            log.info("END - deleted {}", schedaId);
        } catch (Exception e) {
            log.error("END - not deleted {}, message={}", schedaId, e.getMessage(), e);
            throw new InternalError("ProdottoAllegato schedaId=" + schedaId + " non deleted");
        }
    }
}
