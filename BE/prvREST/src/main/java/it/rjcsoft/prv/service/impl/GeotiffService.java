package it.rjcsoft.prv.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Optional;

import com.querydsl.core.types.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.rjcsoft.prv.config.PrvRestConfig;
import it.rjcsoft.prv.dto.geotiff.GeotiffAllDTO;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.Geotiff;
import it.rjcsoft.prv.repository.IGeotiffNoBase64Repository;
import it.rjcsoft.prv.repository.IGeotiffRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IGeotiffService;
import it.rjcsoft.prv.service.IGeotiffStyleService;
import it.rjcsoft.prv.taskexecutor.TaskExecutorToLoadGeotiffStyles;
import it.rjcsoft.prv.utils.GeoTiffUtils;
import it.rjcsoft.prv.utils.PrvConverterUtils;
import it.rjcsoft.prv.utils.PrvFileUtils;

@Service
public class GeotiffService extends BaseService implements IGeotiffService {

	@Autowired
	private IGeotiffRepository geotiffRepository;

	@Autowired
	private IGeotiffNoBase64Repository geotiffNoBase64Repository;

	@Autowired
	private PrvRestConfig prvRestConfig;

	@Autowired
	private IGeotiffStyleService geotiffStyleService;

	@Override
	public Page<GeotiffAllDTO> findAll(Predicate predicate, Pageable pageable, boolean isBase64Needed, Integer userId) {
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
		Page<?> geotiffPage = null;
		if (isBase64Needed) {
			geotiffPage = geotiffRepository.findAll(predicate, pageable);
		} else {
			geotiffPage = geotiffNoBase64Repository.findAll(predicate, pageable);
		}
		log.trace("geotiffPage={}", geotiffPage);
		Page<GeotiffAllDTO> respPage = geotiffPage.map(currGeotiff -> {
			GeotiffAllDTO dto = new GeotiffAllDTO();
			if (PrvConverterUtils.copyProperties(dto, currGeotiff)) {
				dto.setUrl(prvRestConfig.getPngBasePath() + dto.getId());
				log.trace("copyProperties SUCCESS, dto={}", dto);
				dto.setIsOwner(false);
				if (Objects.equals(userId, dto.getIdUtente())) {
					dto.setIsOwner(true);
				}
				return dto;
			}
			log.warn("copyProperties FAILED, currCensimento={}", currGeotiff);
			return null;
		});
		log.trace("END - page={}", respPage);
		return respPage;
	}

	@Override
	public void save(MultipartFile file, String uom, Integer userId) throws Exception {
		log.debug("START - saving geotiff={}, uom={}", file.getOriginalFilename(), uom);
		try {
			File geotiffFile = PrvFileUtils.saveFile(file, prvRestConfig.getGeotiffBasePath());
			Geotiff geotiff = new Geotiff();
			geotiff.setNomeFile(geotiffFile.getName());
			geotiff.setGeometria(GeoTiffUtils.convertGeotiffToPolygon(geotiffFile));
			geotiff.setContent(GeoTiffUtils.fromFileToBase64(geotiffFile));
			geotiff.setUom(uom);
			geotiff.setProcessed(false);
			geotiff.setIdUtente(userId);
			Geotiff geotiffSaved = geotiffRepository.save(geotiff);
			GeotiffAllDTO dto = PrvConverterUtils.initGeotiffAllDTO(geotiffSaved);
			TaskExecutorToLoadGeotiffStyles taskExecutor = TaskExecutorToLoadGeotiffStyles.getInstance();
			taskExecutor.laod(dto, geotiffStyleService);
		} catch (Exception e) {
			log.error("END - error during saving file={}, {}", file.getOriginalFilename(), e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public GeotiffAllDTO update(GeotiffAllDTO geotiffDTO) {
		log.debug("START - update {}", geotiffDTO);
		Integer id = geotiffDTO.getId();

		Optional<Geotiff> geotiffOptional = geotiffRepository.findById(id);
		if (!geotiffOptional.isPresent()) {
			return null;
		}

		Geotiff geotiff = geotiffOptional.get();

		try {
			if (PrvConverterUtils.copyPropertiesNotNull(geotiff, geotiffDTO)) {
				log.info("Model - geotiff={}", geotiff);
				geotiff = geotiffRepository.save(geotiff);
				log.info("END - saved {}", geotiff);
				return PrvConverterUtils.initGeotiffAllDTO(geotiff);

			}
		} catch (Exception e) {
			log.error("ERROR saving {}, message={}", geotiff, e.getMessage(), e);
			return null;
		}
		log.warn("END - copyProperties FAILED, geotiffDTO={}", geotiffDTO);
		return null;
	}

	@Override
	public File getFileById(Integer id) throws Exception {
		Optional<Geotiff> geotiff = geotiffRepository.findById(id);
		if (!geotiff.isPresent()) {
			log.error("No geotiff found with id={}", id);
			throw new PojoNotFound("No geotiff found with id=" + id);
		}
		String fileName = geotiff.get().getNomeFile();
		File returnFile = null;
		if (StringUtils.isNotBlank(fileName)) {
			returnFile = new File(prvRestConfig.getGeotiffBasePath(), fileName);
		}
		if (returnFile != null && returnFile.exists()) {
			return returnFile;
		} else {
			log.error("File {}/{} not found", prvRestConfig.getGeotiffBasePath(), fileName);
			throw new FileNotFoundException(
					"File " + prvRestConfig.getGeotiffBasePath() + "/" + fileName + "not found");
		}
	}
	

	@Override
	public Boolean deleteById(Integer id) throws Exception {
		try {
			log.debug("START - trying to delete geotiff");	
			PrvFileUtils.deleteFile(getFileById(id));		
			geotiffRepository.deleteById(id);
		
			log.info("END - geotiff deleted  id {}", id);
			return true;
		} catch (Exception e) {
			log.error("END - geotiff not exist {}",id);
			return false;
			
		}
	}

}
