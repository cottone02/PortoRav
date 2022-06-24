package it.rjcsoft.prv.service;

import java.io.File;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import it.rjcsoft.prv.dto.geotiff.GeotiffAllDTO;

public interface IGeotiffService {

	public void save(MultipartFile file, String uom, Integer userId) throws Exception;

	public Page<GeotiffAllDTO> findAll(Predicate predicate, Pageable pageable, boolean base64Needed, Integer userId);

	public File getFileById(Integer id) throws Exception;

	public GeotiffAllDTO update(GeotiffAllDTO geotiffDTO);

	Boolean deleteById(Integer id) throws Exception;
	
}
