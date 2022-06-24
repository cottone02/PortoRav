package it.rjcsoft.prv.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import it.rjcsoft.prv.exceptions.BaseEx;

public interface IProdottiAllegatiService {

    public void save(Integer schedaId, MultipartFile[] files) throws Exception;

    public File downloadFileById(Integer id) throws BaseEx;

	public void deleteById(Integer id) throws BaseEx;

    public void deleteBySchedaId(Integer schedaId) throws BaseEx;
    
}
