package it.rjcsoft.prv.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.querydsl.core.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.rjcsoft.prv.config.PrvRestConfig;
import it.rjcsoft.prv.dto.UploadedDocumentDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.CompanyEntity;
import it.rjcsoft.prv.model.UploadedDocument;
import it.rjcsoft.prv.model.UploadedDocumentFile;
import it.rjcsoft.prv.model.UploadedDocumentFilePKEY;
import it.rjcsoft.prv.repository.ICompanyEntityRepository;
import it.rjcsoft.prv.repository.IUploadedDocumentFileRepository;
import it.rjcsoft.prv.repository.IUploadedDocumentRepository;
import it.rjcsoft.prv.utils.PrvConverterUtils;
import it.rjcsoft.prv.utils.PrvFileUtils;

@Service
public class UploadedDocumentService extends BaseService implements IUploadedDocumentService {

	@Autowired
	private IUploadedDocumentRepository uploadedDocumentRepository;
	
	@Autowired
	private IUploadedDocumentFileRepository uploadedDocumentFileRepository;
	
	@Autowired
	private ICompanyEntityRepository companyEntityRepository;

	@Autowired
	private PrvRestConfig prvRestConfig;
	
	@Override
	public Page<UploadedDocumentDTO> findAll(Predicate predicate, Pageable pageable) {
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
        Page<UploadedDocument> uploadedDocumentPage = uploadedDocumentRepository.findAll(predicate, pageable);
        log.trace("documentiFormPage={}", uploadedDocumentPage);
        Page<UploadedDocumentDTO> respPage = uploadedDocumentPage.map(currUploadedDocument -> {
        	UploadedDocumentDTO dto = new UploadedDocumentDTO();
            if (PrvConverterUtils.copyProperties(dto, currUploadedDocument)) {
            	Optional<CompanyEntity> company = companyEntityRepository.findById(dto.getCompanyId());
            	dto.setCompanyName(company.get().getName());
                log.trace("copyProperties SUCCESS, dto={}", dto);
                return dto;
            }
            log.warn("copyProperties FAILED, currDocumentiForm={}", currUploadedDocument);
            return null;
        });
        log.trace("END - page={}", respPage);
        return respPage;
	}

	
	//@Override
	@Transactional//(rollbackFor = { InternalError.class })
        public void deleteByIdDocumentAndName(int id, String name) throws BaseEx {
        log.debug("START - delete uploadedDocumentFile id={}", id, "and name={}", name);
        UploadedDocumentFilePKEY pkey = new UploadedDocumentFilePKEY(id, name);
		uploadedDocumentFileRepository.deleteById(pkey);
        uploadedDocumentFileRepository.findById(pkey).orElseThrow(() -> new PojoNotFound("UploadedDocument with id " + id + " and name" + name + " not found"));
		try {
			String directoryPath = prvRestConfig.getDocumentsBasePath();
			File file = new File(directoryPath, name);
			PrvFileUtils.deleteFile(file);
			
			log.info("END - deleted {}", id, " - ", name);
		} catch (Exception e) {
			log.error("END - not deleted {}, message={}", id, name, e.getMessage(), e);
			throw new InternalError("uploadedDocumentFile id=" + id + "and name= " +name+ " non deleted");
		}
    }
	
	@Transactional
	public void deleteUploadedDocument(int id) throws BaseEx {
        log.debug("START - delete uploadedDocumentFile id={}", id);
		
        uploadedDocumentRepository.findById(id).orElseThrow(() -> new PojoNotFound("UploadedDocument with id " + id + " not found"));
		try {
			int company = getCompanyByDocumentId(id);
			
			List<String> nameFileList = getFileNamesById(id);
			uploadedDocumentFileRepository.deleteAllById(id);
			uploadedDocumentRepository.deleteById(id);
		
			nameFileList.forEach(nameFile -> {
				File file = new File(prvRestConfig.getDocumentsBasePath().concat(""+company), nameFile);
				PrvFileUtils.deleteFile(file);
			});
			
			log.info("END - deleted {}", id);
		} catch (Exception e) {
			log.error("END - not deleted {}, message={}", id, e.getMessage(), e);
			throw new InternalError("uploadedDocumentFile id=" + id + " non deleted");
		}
    }
	
	@Override
	public List<String> getFileNamesById(int id) {
		List<String> list = new ArrayList<String>();
		List<UploadedDocumentFile> listAll = uploadedDocumentFileRepository.findAllFile(id);
		listAll.forEach(element -> {
			if(element.getIdDocument() == id)
				list.add(element.getFileName());
			});
		return list;

	}
	
	@Override
	public String saveFile(MultipartFile file, Integer company) {
		log.debug("START - file={}, companyId={}", file, company);

		String fileName = file.getOriginalFilename();
		
		File directory = new File(prvRestConfig.getDocumentsBasePath(), String.format("%d", company));

		if (!directory.exists()) {
			directory.mkdir();
		}

		File myFile = new File(directory, fileName);
		
		int i = 1;

		while (myFile.exists()) {
			String newFileName = fileName;
			newFileName = i + "_" + fileName;
			i++;
			myFile = new File(directory, newFileName);
		}

		log.info("Original fileName={}, CREATING myFile={}", fileName, myFile.getAbsolutePath());

		try (FileOutputStream fos = new FileOutputStream(myFile)) {
			fos.write(file.getBytes());
		} catch (Exception e) {
			log.error("FILE {} NOT SAVED", myFile.getAbsolutePath());
			return null;
		}

		log.info("END - file={} SAVED", myFile.getAbsolutePath());

		return myFile.getName();

	}
	
	
	@Override
	public UploadedDocument initNewDocument(UploadedDocumentDTO uploadedDocumentDTO) {
		log.debug("START - initNewDocument, uploadedDocumentDTO={}", uploadedDocumentDTO);
		UploadedDocument uploadedDocument = new UploadedDocument();

		boolean isCopied = PrvConverterUtils.copyProperties(uploadedDocument, uploadedDocumentDTO);

		if (isCopied) {
			log.info("Property copied correctly, uploadedDocumentDTO={}, uploadedDocument={}", uploadedDocumentDTO,
					uploadedDocument);
			return uploadedDocumentRepository.save(uploadedDocument);
		} else {
			log.warn("END - NOT COPIED uploadedDocumentDTO={}, uploadedDocument={}", uploadedDocumentDTO,
					uploadedDocument);
			return null;
		}

	}
	
	
	@Override
	public boolean registerFile(Integer id, List<String> fileNames) {
		try {
			fileNames.forEach(filename -> {
				UploadedDocumentFile uploadedFile = new UploadedDocumentFile();
				uploadedFile.setIdDocument(id);
				uploadedFile.setFileName(filename);
				uploadedDocumentFileRepository.save(uploadedFile);
			});
			return true;
		} catch(Exception e){
			log.error("FILE {} NOT SAVED {}", fileNames, e.getMessage());
			return false;
		}
		
	}
	
	
	@Override
	public File getZipFile(int id) {
		log.debug("START - Zip document id={}", id);
		String documentsBasePath = prvRestConfig.getDocumentsBasePath().concat("/"+ getCompanyByDocumentId(id));
		String pathStr = String.format("%sdocuments_%d.zip", documentsBasePath, id);
		List<String> filePaths = getFileNamesById(id);
		log.trace("ZIPFILE - documentBasePath={}, pathStr={}, filePaths={}", documentsBasePath, pathStr, filePaths);
		File zipFile = PrvFileUtils.zipFile(documentsBasePath, pathStr, filePaths);

		if (zipFile != null) {
			log.info("END - zipFile={}", zipFile.getAbsolutePath());
		} else {
			log.info("END- zipFile NOT CREATED");
		}

		return zipFile;

	}

	
	@Override
	public File getByIdAndFileName(int id, String fileName) {
		log.debug("START - Fetch path of monitoring id={}", id);
		File file = null;
		UploadedDocumentFilePKEY pkey = new UploadedDocumentFilePKEY(id, fileName);
		Optional<UploadedDocumentFile> upFile = uploadedDocumentFileRepository.findById(pkey);
		
		if (upFile.isEmpty()) {
			return file;
		}
		String documentsBasePath = prvRestConfig.getDocumentsBasePath();
		Optional<UploadedDocument> doc = uploadedDocumentRepository.findById(id);
		File folder = new File(documentsBasePath, doc.get().getCompanyId().toString());
		file = new File(folder, fileName);
		if (!file.exists()) {
			file = null;
		}
		return file;
	}
	
	@Override
	public Integer getCompanyByDocumentId(int id) {
		Optional<UploadedDocument> document = uploadedDocumentRepository.findById(id);
		int companyId = document.get().getCompanyId();
		return companyId;

	}
	
	@Override
	public ResponseEntity<Object> updateDoc(UploadedDocumentDTO uploadedDocumentDTO) throws BaseEx {
		UploadedDocument uploadedDocument = new UploadedDocument();
		UploadedDocument upDocument = uploadedDocumentRepository.findById(uploadedDocumentDTO.getId())
				.orElseThrow(() -> new PojoNotFound("UploadedDocument with id " + uploadedDocumentDTO.getId() + " not found"));
		if (!PrvConverterUtils.copyPropertiesNotNull(uploadedDocument, uploadedDocumentDTO)) {
			return new ResponseEntity<>("Error during object copy", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		int companyId = getCompanyByDocumentId(uploadedDocumentDTO.getId());
		uploadedDocument.setCompanyId(companyId);
		uploadedDocument.setUserId(upDocument.getUserId());
		UploadedDocument upDoc = uploadedDocumentRepository.save(uploadedDocument);
		if (upDoc != null) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	/*@Override
	public Integer initNewDocument(UploadedDocumentDTO uploadedDocumentDTO) {
		log.debug("START - initNewDocument, uploadedDocumentDTO={}", uploadedDocumentDTO);
		UploadedDocument uploadedDocument = new UploadedDocument();

		boolean isCopied = PrvConverterUtils.copyProperties(uploadedDocument, uploadedDocumentDTO);

		if (isCopied) {
			log.info("Property copied correctly, uploadedDocumentDTO={}, uploadedDocument={}", uploadedDocumentDTO,
					uploadedDocument);
			return uploadedDocumentRepository.insertNewDocument(uploadedDocument);
		} else {
			log.warn("END - NOT COPIED uploadedDocumentDTO={}, uploadedDocument={}", uploadedDocumentDTO,
					uploadedDocument);
			return null;
		}

	}*/

	/*@Override
	public String saveFile(MultipartFile file, Integer company) {
		log.debug("START - file={}, companyId={}", file, company);

		String fileName = file.getOriginalFilename();

		File directory = new File(prvRestConfig.getDocumentsBasePath(), String.format("%d", company));

		if (!directory.exists()) {
			directory.mkdir();
		}

		File myFile = new File(directory, fileName);

		int i = 1;

		while (myFile.exists()) {
			String newFileName = fileName;
			newFileName = i + "_" + fileName;
			i++;
			myFile = new File(directory, newFileName);
		}

		log.info("Original fileName={}, CREATING myFile={}", fileName, myFile.getAbsolutePath());

		try (FileOutputStream fos = new FileOutputStream(myFile)) {
			fos.write(file.getBytes());
		} catch (Exception e) {
			log.error("FILE {} NOT SAVED", myFile.getAbsolutePath());
			return null;
		}

		log.info("END - file={} SAVED", myFile.getAbsolutePath());

		return myFile.getName();

	}

	/*@Override
	public Boolean registerFile(Integer id, List<String> fileNames) {

		return uploadedDocumentRepository.insertFileByIdDocument(id, fileNames);

	}*/
	
	

	/*@Override
	public UploadedDocumentListDTO getDocumentsByPageIndex(int pageSize, int pageIndex,
			UploadedDocumentFilterDTO filter, boolean isAdmin) {

		UploadedDocumentListDTO uploadedDocumentListDTO = new UploadedDocumentListDTO();

		Integer companyId = filter.getCompanyId();

		if (companyId == null && !isAdmin) {
			uploadedDocumentListDTO.setResponseEnum(ResponseEnum.NO_COMPANY);
			return uploadedDocumentListDTO;
		}

		uploadedDocumentListDTO = uploadedDocumentRepository.findByPage(pageSize, pageIndex, filter, isAdmin);

		if (uploadedDocumentListDTO == null) {
			log.warn(NOT_FOUND);
			uploadedDocumentListDTO = new UploadedDocumentListDTO();
			uploadedDocumentListDTO.setResponseEnum(ResponseEnum.NOT_FOUND);
		}

		return uploadedDocumentListDTO;
	}

	@Override
	public ResponseEntity<Object> deleteById(int idDocument) {
		log.debug("START - idDocument={}", idDocument);
		List<String> filePaths = uploadedDocumentRepository.selectFileNamesById(idDocument);

		log.debug("Found {} files to delete. List {}", filePaths.size(), StringUtils.join(filePaths));
		String documentsBasePath = prvRestConfig.getDocumentsBasePath();
		for (String currFilePath : filePaths) {
			File target = new File(documentsBasePath, currFilePath);
			if (!target.exists()) {
				continue;
			}
			PrvFileUtils.deleteFile(target);
			log.info("file={} DELETED", target.getAbsolutePath());
		}

		boolean isDeleted = uploadedDocumentRepository.deleteRowById(idDocument, filePaths.size());

		if (!isDeleted) {
			log.warn("END - NOT DELETED IN DB");
			return new ResponseEntity<>(FILE_NOT_DELETED, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(HttpStatus.OK);

	}*/

	/*@Override
	public File getZipFile(int id) {
		log.debug("START - Zip document id={}", id);
		String documentsBasePath = prvRestConfig.getDocumentsBasePath();
		String pathStr = String.format("%sdocuments_%d.zip", documentsBasePath, id);
		List<String> filePaths = uploadedDocumentRepository.selectFileNamesById(id);
		log.trace("ZIPFILE - documentBasePath={}, pathStr={}, filePaths={}", documentsBasePath, pathStr, filePaths);
		File zipFile = PrvFileUtils.zipFile(documentsBasePath, pathStr, filePaths);

		if (zipFile != null) {
			log.info("END - zipFile={}", zipFile.getAbsolutePath());
		} else {
			log.info("END- zipFile NOT CREATED");
		}

		return zipFile;

	}

	@Override
	public File getByIdAndFileName(int id, String fileName) {
		log.debug("START - Fetch path of monitoring id={}", id);
		File file = null;
		Integer idCompany = uploadedDocumentRepository.selectCompanyByFileNameId(id, fileName);
		if (idCompany == null) {
			return file;
		}
		String documentsBasePath = prvRestConfig.getDocumentsBasePath();
		File folder = new File(documentsBasePath, idCompany.toString());
		file = new File(folder, fileName);
		if (!file.exists()) {
			file = null;
		}
		return file;
	}

	@Override
	public List<String> getFileNamesById(int id) {
		return uploadedDocumentRepository.selectFileNames(id);

	}

	@Override
	public Boolean deleteFileByNameId(String fileName, int id) {
		log.debug("START - Delete fileName={}, id={}", fileName, id);
		String documentsBasePath = prvRestConfig.getDocumentsBasePath();
		Integer companyId = uploadedDocumentRepository.selectCompanyByFileNameId(id, fileName);
		if (companyId == null) {
			log.error("END - company NOT FOUND, fileName={}, id={}", fileName, id);
			return false;
		}
		File targetFolder = new File(documentsBasePath, companyId.toString());
		File target = new File(targetFolder, fileName);
		if (!target.exists()) {
			log.error("END - File not found in FS. Path={}", target.getAbsolutePath());
			return false;
		}
		PrvFileUtils.deleteFile(target);
		log.info("file={} DELETE", target.getAbsolutePath());

		return uploadedDocumentRepository.deleteRowByFileNameId(id, fileName);
	}

	@Override
	public ResponseEntity<Object> updateDoc(UploadedDocumentDTO uploadedDocumentDTO) {
		UploadedDocument uploadedDocument = new UploadedDocument();
		if (!PrvConverterUtils.copyProperties(uploadedDocument, uploadedDocumentDTO)) {
			return new ResponseEntity<>("Error during object copy", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Boolean response = uploadedDocumentRepository.updateDocumentInfo(uploadedDocument);
		if (Boolean.TRUE.equals(response)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Integer getCompanyByDocumentId(int id) {
		return uploadedDocumentRepository.selectCompanyByDocumentId(id);

	}*/

}
