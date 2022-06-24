package it.rjcsoft.prv.controller;

import javax.validation.Valid;

import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import it.rjcsoft.prv.dto.cumuliaperto.CumuliApertoFullDTO;
import it.rjcsoft.prv.dto.cumuliaperto.CumuliApertoUpdateDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.model.CumuliAperto;
import it.rjcsoft.prv.repository.ICumuliApertoRepository;
import it.rjcsoft.prv.service.ICumuliApertoService;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/cumuliAperto")
public class CumuliApertoController extends BaseController {

	@Autowired
	private ICumuliApertoService cumuliApertoService;

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT,ROLE_READER_EXT })
	@GetMapping("")

	public ResponseEntity<Page<CumuliApertoFullDTO>> getAll(
			@QuerydslPredicate(root = CumuliAperto.class, bindings = ICumuliApertoRepository.class) Predicate predicate,
			@PageableDefault(sort = { "id" }) Pageable pageable) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - predicate={}, pageable={}", predicate, pageable);
		Page<CumuliApertoFullDTO> page = cumuliApertoService.findAll(predicate, pageable);
		if (page.isEmpty()) {
			log.info("END - no content");
			return ResponseEntity.noContent().build();
		} else {
			log.info("END - 200, page={}", page);
			return ResponseEntity.ok(page);
		}
	}

	@Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT })
	@PutMapping("/update")
	public ResponseEntity<Object> update(@Valid @RequestBody CumuliApertoUpdateDTO cumuliApertoUpdateDTO) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - cumuliApertoUpdateDTO={}", cumuliApertoUpdateDTO);

		try {
			cumuliApertoService.update(cumuliApertoUpdateDTO);
		} catch (BaseEx e) {
			log.error("END - UPDATE Internal error, message={}", e.getMessage(), e);
			return e.getResponseEntity();
		}
		return ResponseEntity.ok().build();
	}

}
