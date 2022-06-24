package it.rjcsoft.prv.controller;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.rjcsoft.prv.dto.stili.StiliDTO;
import it.rjcsoft.prv.service.IStiliService;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/stili")
public class StiliController extends BaseController {

	@Autowired
	private IStiliService stiliService;

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT })
	@GetMapping("/{idGeotiff}")
	public ResponseEntity<List<StiliDTO>> getStiliById(@PathVariable Integer idGeotiff) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - idGeotiff={}", idGeotiff);
		List<StiliDTO> returnList = stiliService.getAllStiliById(idGeotiff);
		if (CollectionUtils.isEmpty(returnList)) {
			log.info("END - no content");
			return ResponseEntity.noContent().build();
		} else {
			log.info("END - 200, returnList={}", returnList);
			return ResponseEntity.ok(returnList);
		}

	}

}
