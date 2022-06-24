package it.rjcsoft.prv.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.querydsl.core.types.Predicate;

import it.rjcsoft.prv.dto.modulistica.ModulisticaDTO;
import it.rjcsoft.prv.dto.modulistica.ModulisticaUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;


public interface IModulisticaService {

	public Page<ModulisticaDTO> findAll(Predicate predicate, Pageable pageable);

	public void deleteById(int id) throws BaseEx;

	public void updateFile(ModulisticaUpdateDTO modulisticaUpdateDTO) throws BaseEx;

	public ModulisticaDTO save(@Valid ModulisticaDTO modulisticaDTO, MultipartFile file) throws BaseEx;

	public String getPathFileById(int id);


}
