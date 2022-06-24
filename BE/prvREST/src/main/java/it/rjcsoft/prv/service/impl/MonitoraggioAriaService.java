package it.rjcsoft.prv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.config.PrvRestConfig;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.FileMonitoraggio;
import it.rjcsoft.prv.model.Monitoraggi;
import it.rjcsoft.prv.model.MonitoraggioAria;
import it.rjcsoft.prv.model.excel.ExcelAria;
import it.rjcsoft.prv.repository.IFileMonitoraggioRepository;
import it.rjcsoft.prv.repository.IMonitoraggiRepository;
import it.rjcsoft.prv.repository.IMonitoraggioAriaRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IMonitoraggiAriaService;
import it.rjcsoft.prv.utils.ExcelParser;
import it.rjcsoft.prv.utils.PrvConverterUtils;
import java.io.File;
import java.util.List;

@Service
public class MonitoraggioAriaService extends BaseService implements IMonitoraggiAriaService {

    @Autowired
    private IMonitoraggioAriaRepository monitoraggioAriaRepository;
    @Autowired
    private IMonitoraggiRepository monitoraggioRepository;
    @Autowired
    private IFileMonitoraggioRepository fileMonitoraggioRepository;
    @Autowired
    private PrvRestConfig prvRestConfig;


    @Override
    public boolean insertMonitoraggioAria(Integer idFile) throws Exception {

        log.debug("START - idFile{}", idFile);
        try {
            FileMonitoraggio fileMonitoraggioRiferimento = fileMonitoraggioRepository.findById(idFile).orElseThrow(() -> new PojoNotFound("Monitoraggio with id " + idFile + " not found"));
            
            if (fileMonitoraggioRiferimento == null) {
                return false;
            }
            
            Monitoraggi monitoraggioRiferimento = monitoraggioRepository.findById(fileMonitoraggioRiferimento.getIdMonitoring()).orElseThrow(() -> new PojoNotFound("Monitoraggio with id " + fileMonitoraggioRiferimento.getIdMonitoring() + " not found"));

            String path = prvRestConfig.getMonitoringBasePath() + monitoraggioRiferimento.getCompanyId();
            File file = new File(path, fileMonitoraggioRiferimento.getFileName());
            List<ExcelAria> monitoraggiAriaObj = ExcelParser.parseExcelTo(file.getAbsolutePath(), ExcelAria.class, null);

            if (monitoraggiAriaObj.isEmpty()) {
                return false;
            }

            monitoraggiAriaObj.forEach(monitoraggio -> {
                MonitoraggioAria monitoraggioModel = new MonitoraggioAria();
                System.out.println(monitoraggio);
                PrvConverterUtils.copyProperties(monitoraggioModel, monitoraggio);
                monitoraggioModel.setNomeFile(fileMonitoraggioRiferimento.getFileName());
                monitoraggioModel.setNumeroPagina(monitoraggio.getNumeroPagina());
                monitoraggioModel.setIdMonitoraggio(idFile);
                monitoraggioAriaRepository.save(monitoraggioModel);
            });

            log.info("END - ListAria{}", monitoraggiAriaObj);
            return true;
        } catch (Exception e) {
            log.error("END - Internal error, message={}", e.getMessage());
            throw e;
        }
    }
}