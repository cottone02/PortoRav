package it.rjcsoft.prv.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Joiner;

import it.rjcsoft.prv.dto.SchedaDueDotazioniAziendaDto;
import it.rjcsoft.prv.enums.SearchOperationEnum;
import it.rjcsoft.prv.model.SchedaDueDotazioniAzienda;
import it.rjcsoft.prv.repository.spec.SchedaDueSpecificationBuilder;
import it.rjcsoft.prv.service.ISchedaDueService;

@RestController
@RequestMapping("/schededue")
public class SchedaDueController {

	@Autowired
	private ISchedaDueService schedaDueService;

	@GetMapping("")
	@ResponseBody
	public ResponseEntity<Page<SchedaDueDotazioniAziendaDto>> search(
			@RequestParam(value = "search", required = false) String search,
			@PageableDefault(sort = { "id", "numScheda" }) Pageable pageable) {

		SchedaDueSpecificationBuilder builder = new SchedaDueSpecificationBuilder();

		if (search != null) {

			String operationSetExper = Joiner.on("|").join(SearchOperationEnum.SIMPLE_OPERATION_SET);
			Pattern pattern = Pattern
					.compile("(\\p{Punct}?)(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
			Matcher matcher = pattern.matcher(search + ",");
			while (matcher.find()) {
				builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(5), matcher.group(4),
						matcher.group(6));
			}
		}

		Specification<SchedaDueDotazioniAzienda> spec = builder.build(); 
		
		return ResponseEntity.ok(schedaDueService.findAll(spec, pageable));
	}

}
