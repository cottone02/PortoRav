package it.rjcsoft.prv.controller;

import java.io.IOException;

import javax.validation.Valid;
import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.rjcsoft.prv.dto.geotiff.GeotiffAllDTO;
import it.rjcsoft.prv.dto.stili.CustomStiliDTO;
import it.rjcsoft.prv.service.IGeotiffStyleService;
import it.rjcsoft.prv.utils.PrvUtils;

@RestController
@RequestMapping("/geotiffStyle")
public class GeotiffStyleController extends BaseController {

	@Autowired
	private IGeotiffStyleService geotiffStyleService;
	
	@GetMapping("")
	public ResponseEntity<Object> insertGeotiff2(@RequestBody CustomStiliDTO newCustomStyleDTO) throws IOException {
		log.debug("START - trying to insert geotiff");

		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> insertGeotiff(@PathVariable int id) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - trying to insert geotiff");
		try {
			GeotiffAllDTO gDto = geotiffStyleService.createStyleByGeotiffId(id);
			if (gDto == null) {
				log.info("END - geotiff not saved id={}", id);

				return ResponseEntity.noContent().build();
			}

			log.info("END - geotiff saved{}", gDto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error("END - Internal error, message={}", e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Object> generateCustomizedGeotiffStyle(@PathVariable int id, @Valid @RequestBody CustomStiliDTO newCustomStyleDTO) {
		MDC.put(PRV_TID, PrvUtils.generateTID());
		log.debug("START - GenerateCustomizedGeotiffStyle geoTiffId={}, newCustomStyleDTO={}",id ,newCustomStyleDTO);
		try {
			GeotiffAllDTO gDto = geotiffStyleService.createCustomStyleByGeotiffId(id, newCustomStyleDTO);
			if (gDto == null) {
				log.info("END - geotiff not saved id={}", id);
				return ResponseEntity.noContent().build();
			}
			log.info("END - geotiff saved {}", gDto);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error("END - Internal error, message={}", e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	

}
