package it.rjcsoft.prv.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.tomcat.util.buf.StringUtils;
import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.rjcsoft.prv.dto.companyentity.CompanyEntityDTO;
import it.rjcsoft.prv.service.ICompanyEntityService;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/companyEntityController")
public class CompanyEntityController extends BaseController {

	

	@Autowired
	private ICompanyEntityService companyEntityService;

	 @Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER,ROLE_WRITER_EXT,ROLE_READER_EXT })
	@GetMapping("")
	public ResponseEntity<Object> getAllCompanies() {

		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - trying to take companies Name");
		try {
			List<CompanyEntityDTO> cDto = companyEntityService.getAllCompany();
			if (CollectionUtils.isEmpty(cDto)) {
				log.info("END - Companies Name not taken");
				return ResponseEntity.noContent().build();
			}
			log.info("END - Companies Name Taked{}",
					StringUtils.join(cDto.stream().map(CompanyEntityDTO::getName).collect(Collectors.toList())));
			return new ResponseEntity<>(cDto, HttpStatus.OK);
		} catch (Exception e) {
			e.getMessage();
			log.error("END - Internal error, message={}", e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

}
