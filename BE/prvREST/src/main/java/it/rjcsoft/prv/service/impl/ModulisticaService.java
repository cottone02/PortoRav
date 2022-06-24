package it.rjcsoft.prv.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import com.querydsl.core.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.rjcsoft.prv.config.PrvRestConfig;
import it.rjcsoft.prv.dto.modulistica.ModulisticaDTO;
import it.rjcsoft.prv.dto.modulistica.ModulisticaUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.DocumentiForm;
import it.rjcsoft.prv.repository.IModulisticaRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IModulisticaService;
import it.rjcsoft.prv.utils.PrvConverterUtils;
import it.rjcsoft.prv.utils.PrvFileUtils;

@Service
public class ModulisticaService extends BaseService implements IModulisticaService {
	
	@Autowired
    private IModulisticaRepository modulisticaRepository;
	
	@Autowired
	private PrvRestConfig prvRestConfig;
	
	@Override
	public Page<ModulisticaDTO> findAll(Predicate predicate, Pageable pageable) {
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
        Page<DocumentiForm> documentiFormPage = modulisticaRepository.findAll(predicate, pageable);
        log.trace("documentiFormPage={}", documentiFormPage);
        Page<ModulisticaDTO> respPage = documentiFormPage.map(currDocumentiForm -> {
        	ModulisticaDTO dto = new ModulisticaDTO();
            if (PrvConverterUtils.copyProperties(dto, currDocumentiForm)) {
       
                log.trace("copyProperties SUCCESS, dto={}", dto);
                return dto;
            }
            log.warn("copyProperties FAILED, currDocumentiForm={}", currDocumentiForm);
            return null;
        });
        log.trace("END - page={}", respPage);
        return respPage;
	}
	
	@Override
	public void updateFile( ModulisticaUpdateDTO modulisticaUpdateDTO) throws BaseEx {
		
		DocumentiForm docForm = modulisticaRepository.findById(modulisticaUpdateDTO.getId())
		.orElseThrow(() -> new PojoNotFound("Modulistica with id " + modulisticaUpdateDTO.getId() + " not found"));
		
		try {
			if(modulisticaUpdateDTO.getFile() != null) {
				File file = PrvFileUtils.sostituisciFile(modulisticaUpdateDTO.getFile(), docForm.getFileName(), prvRestConfig.getFileBasePath());
				if (file != null && file.exists()) {
					try {
						if (PrvConverterUtils.copyPropertiesNotNull(docForm, modulisticaUpdateDTO)) { 
							docForm.setFileName(modulisticaUpdateDTO.getFile().getOriginalFilename());
							log.info("Model - modulistica={}", docForm);
							docForm = modulisticaRepository.save(docForm);
							log.info("END - saved {}", docForm);
							return;
						}
					} catch (Exception e) {
						log.error("ERROR saving {}, message={}", docForm, e.getMessage(), e);
						throw new InternalError("Object not saved", modulisticaUpdateDTO);
					}
					log.warn("END - copyProperties FAILED, modulisticaUpdateDTO={}", modulisticaUpdateDTO);
				}
			} else {
				try {
					if (PrvConverterUtils.copyPropertiesNotNull(docForm, modulisticaUpdateDTO)) { 
						//docForm.setFileName(modulisticaUpdateDTO.getFile().getOriginalFilename());
						log.info("Model - modulistica={}", docForm);
						docForm = modulisticaRepository.save(docForm);
						log.info("END - saved {}", docForm);
						return;
					}
				} catch (Exception e) {
					log.error("ERROR saving {}, message={}", docForm, e.getMessage(), e);
					throw new InternalError("Object not saved", modulisticaUpdateDTO);
				}
				log.warn("END - copyProperties FAILED, modulisticaUpdateDTO={}", modulisticaUpdateDTO);
				
			}
		} catch (IOException e) {
			log.error("END - UPDATE Internal error, message={}", e.getMessage(), e);
			throw new InternalError("File not saved");
		}
		
	}
	
	
	@Override
    public void deleteById(int id) throws BaseEx {
        log.debug("START - delete modulistica id={}", id);

        DocumentiForm doc = modulisticaRepository.findById(id)
				.orElseThrow(() -> new PojoNotFound("Modulistica with id " + id + " not found"));
		try {
			modulisticaRepository.deleteById(id);
			
			File file = new File(prvRestConfig.getFileBasePath(), doc.getFileName());
			PrvFileUtils.deleteFile(file);
			
			log.info("END - deleted {}", id);
		} catch (Exception e) {
			log.error("END - not deleted {}, message={}", id, e.getMessage(), e);
			throw new InternalError("modulo id=" + id + " non deleted");
		}
    }

	@Override
	public ModulisticaDTO save(ModulisticaDTO modulisticaDTO, MultipartFile file) throws BaseEx {
		log.debug("START - save {}", modulisticaDTO);
		Integer id = modulisticaDTO.getId();
		if (id != null && !modulisticaRepository.existsById(id)) {
			log.warn("id={} not found", id);
			throw new PojoNotFound("modulo with id " + id + " not found");
		}
		
		DocumentiForm docForm = new DocumentiForm();
		try {
			if (PrvConverterUtils.copyProperties(docForm, modulisticaDTO)) {
				
				docForm.setFileName(file.getOriginalFilename());
				log.info("Model - modulistica={}", docForm);
				docForm = modulisticaRepository.save(docForm);
				log.info("END - saved modulistica {}", docForm);
				File newFile = PrvFileUtils.saveFile(file, prvRestConfig.getFileBasePath());
				log.info("END - saved modulistica on file system{}", newFile);
				return modulisticaDTO;

			}
			
		} catch (Exception e) {
			log.error("END - not insert {}, message={}", id, e.getMessage(), e);
			throw new InternalError("modulo id=" + id + " non deleted");
		}
		log.warn("END - copyProperties FAILED, modulisticaDTO={}", modulisticaDTO);
		return null;

	}
	
	@Override
	public String getPathFileById(int id) {

		log.debug("START - founding path of document id={}", id);

		Optional<DocumentiForm> modulistica = modulisticaRepository.findById(id);

		if (modulistica.get().getFileName() != null) {
			return String.format("%s%s", prvRestConfig.getFileBasePath(), modulistica.get().getFileName());
		} else {
			return null;
		}
	}
	

}
