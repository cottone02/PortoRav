package it.rjcsoft.prv.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.config.PrvRestConfig;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.FileMonitoraggio;
import it.rjcsoft.prv.model.Monitoraggi;
import it.rjcsoft.prv.model.MonitoraggioMaree;
import it.rjcsoft.prv.model.excel.ExcelMaree;
import it.rjcsoft.prv.repository.IFileMonitoraggioRepository;
import it.rjcsoft.prv.repository.IMonitoraggiRepository;
import it.rjcsoft.prv.repository.IMonitoraggioMareeRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IMonitoraggiMareeService;
import it.rjcsoft.prv.utils.ExcelParser;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class MonitoraggiMareeService extends BaseService implements IMonitoraggiMareeService {
	@Autowired
	private IMonitoraggioMareeRepository monitoraggioMareeRepository;
        @Autowired
	private IMonitoraggiRepository monitoraggioRepository;
	@Autowired
	private IFileMonitoraggioRepository fileMonitoraggioRepository;
	@Autowired
	private PrvRestConfig prvRestConfig;

	@Override
	public boolean insertMonitoraggioMaree(Integer idFile) throws Exception {

	 	log.debug("START - idFile{}", idFile);
                
	 	try {   
                    FileMonitoraggio fileMonitoraggioRiferimento = fileMonitoraggioRepository.findById(idFile).orElseThrow(() -> new PojoNotFound("Monitoraggio with id " + idFile + " not found"));
                    
                    if (fileMonitoraggioRiferimento == null)
	 		return false;
                      
                    Monitoraggi monitoraggioRiferimento = monitoraggioRepository.findById(fileMonitoraggioRiferimento.getIdMonitoring()).orElseThrow(() -> new PojoNotFound("Monitoraggio with id " + fileMonitoraggioRiferimento.getIdMonitoring() + " not found"));
                    String path = prvRestConfig.getMonitoringBasePath() + monitoraggioRiferimento.getCompanyId();

                    File file = new File(path, fileMonitoraggioRiferimento.getFileName());
                    List<ExcelMaree> monitoraggiMareeObj = ExcelParser.parseExcelTo(file.getAbsolutePath(), ExcelMaree.class, 3);
                    
                    if (monitoraggiMareeObj.isEmpty()) {
                        return false;
                        }
                    
                    monitoraggiMareeObj.forEach(monitoraggio -> {
                        MonitoraggioMaree monitoraggioModel = new MonitoraggioMaree();
                        PrvConverterUtils.copyProperties(monitoraggioModel, monitoraggio);
                        
                        monitoraggioModel.setNomeFile(fileMonitoraggioRiferimento.getFileName());
                        monitoraggioModel.setLivelloIdrometrico(monitoraggio.getLvlIdrometrico());
                        monitoraggioModel.setNumeroPagina(monitoraggio.getNumeroPagina());
                        monitoraggioModel.setIdMonitoraggio(idFile);
                        monitoraggioMareeRepository.save(monitoraggioModel);
                    });
                
                    log.info("END - ListAria{}", monitoraggiMareeObj);
                    return true;
	 	} catch (Exception e) {
                    log.error("END - Internal error, message={}", e.getMessage());
                    throw e;
	 	}
	 }
}
