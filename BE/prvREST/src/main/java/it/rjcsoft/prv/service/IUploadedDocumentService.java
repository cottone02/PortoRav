package it.rjcsoft.prv.service;

import java.io.File;
import java.util.List;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import it.rjcsoft.prv.dto.UploadedDocumentDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.model.UploadedDocument;

public interface IUploadedDocumentService {

	public Page<UploadedDocumentDTO> findAll(Predicate predicate, Pageable pageable);

	public void deleteByIdDocumentAndName(int id, String name) throws BaseEx;

	public void deleteUploadedDocument(int id) throws BaseEx;

	public List<String> getFileNamesById(int id);

	public String saveFile(MultipartFile file, Integer company);

	public UploadedDocument initNewDocument(UploadedDocumentDTO uploadedDocumentDTO);

	public File getZipFile(int id);

	public File getByIdAndFileName(int id, String fileName);

	public Integer getCompanyByDocumentId(int id);

	public boolean registerFile(Integer id, List<String> fileNames);

	public ResponseEntity<Object> updateDoc(UploadedDocumentDTO uploadedDocumentDTO) throws BaseEx;

}
