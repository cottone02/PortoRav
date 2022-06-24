
package it.rjcsoft.prv.service;

import com.querydsl.core.types.Predicate;
import it.rjcsoft.prv.dto.monitoraggi.MonitoraggiFullDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import java.io.File;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IMonitoraggiService {

	public Integer nuovoMonitoraggio(MonitoraggiFullDTO monitoringDTO);

	public String saveFile(MultipartFile file);

	public Boolean registerFile(Integer id, List<String> fileNames);

	public Page<MonitoraggiFullDTO> getAll(Predicate predicate, Pageable pageable);

	public ResponseEntity<Object> deleteById(int id) throws BaseEx;

	public File getZipFile(int id);

	public List<String> getFileNamesById(int id);

	public Boolean deleteFileByNameId(String fileName, int id);

	public ResponseEntity<Object> updateMonitoraggio(MonitoraggiFullDTO monitoringDTO);

	// public Integer getCompanyByMonitoringId(int id);

	public File getFileByNameAndId(Integer id, String fileName);

	public boolean sendMonitoringMail(MonitoraggiFullDTO monitoringDTO, String action);

	// public Monitoring findOne(int id);

	// public MonitoringListDTO getDocumentsByIds(Set<Integer> ids, boolean isAdmin,
	// Integer companyId);

	public Integer companyByMonitoringId(int id);

	public long getTotalNumber();
        
        public List<MonitoraggiFullDTO> getMonitoringByIds(int[] ids, int companyId);

}
