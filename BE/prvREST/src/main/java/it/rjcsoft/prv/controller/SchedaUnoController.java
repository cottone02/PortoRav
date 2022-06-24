package it.rjcsoft.prv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import it.rjcsoft.prv.dto.SchedaUnoInfoAziendaDto;
import it.rjcsoft.prv.model.SchedaUnoInfoAzienda;
import it.rjcsoft.prv.repository.ISchedaUnoRepository;
import it.rjcsoft.prv.service.ISchedaUnoService;

@RestController
@RequestMapping("/schedeuno")
public class SchedaUnoController {

	@Autowired
	private ISchedaUnoService schedaUnoInfoAziendaService;

	@GetMapping("")
//	 @ApiResponses(value = {
//    @ApiResponse(code = 204, message = "vuoto"),
//    @ApiResponse(code = 400, message = ResponseEnum.MSG_400),
//    @ApiResponse(code = 404, message = ResponseEnum.MSG_404),
//    @ApiResponse(code = 500, message = ResponseEnum.MSG_500),
//    @ApiResponse(code = 409, message = ResponseEnum.MSG_409),
//})
	public ResponseEntity<Page<SchedaUnoInfoAziendaDto>> getAll(
			@QuerydslPredicate(root = SchedaUnoInfoAzienda.class, bindings = ISchedaUnoRepository.class) Predicate predicate,
			@PageableDefault(sort = { "id", "numScheda" }) Pageable pageable) {
		return ResponseEntity.ok(schedaUnoInfoAziendaService.findAll(predicate, pageable));

	}

}
