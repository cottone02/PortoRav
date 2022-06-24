package it.rjcsoft.prv.service.impl;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.config.PrvRestConfig;
import it.rjcsoft.prv.dto.geotiff.GeotiffAllDTO;
import it.rjcsoft.prv.dto.stili.CustomStiliDTO;
import it.rjcsoft.prv.dto.stili.StiliDTO;
import it.rjcsoft.prv.enums.SCALE;
import it.rjcsoft.prv.model.Geotiff;
import it.rjcsoft.prv.model.Stili;
import it.rjcsoft.prv.repository.IGeotiffRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IGeotiffService;
import it.rjcsoft.prv.service.IGeotiffStyleService;
import it.rjcsoft.prv.service.IStiliService;
import it.rjcsoft.prv.utils.PrvConverterUtils;
import it.rjcsoft.prv.utils.StyleUtils;

@Service
public class GeotiffStyleService extends BaseService implements IGeotiffStyleService {

	@Autowired
	private IStiliService stiliService;

	@Autowired
	private IGeotiffService geotiffService;

	@Autowired
	private PrvRestConfig prvRestConfig;

	@Autowired
	private IGeotiffRepository geotiffRepository;

	@Override
	public void generateCustomStyle(GeotiffAllDTO dto, CustomStiliDTO newCustomStyleDTO) throws Exception {
		File geotiff = new File(prvRestConfig.getGeotiffBasePath(), dto.getNomeFile());
		Stili stile = new Stili();
		try {
			if (!geotiff.exists()) {
				throw new FileNotFoundException("END - file=" + geotiff.getAbsolutePath() + " not found");
			}
			Color customColor = new Color(newCustomStyleDTO.getMainColor_RED(), newCustomStyleDTO.getMainColor_GREEN(), newCustomStyleDTO.getMainColor_BLUE());
			SCALE scale = SCALE.valueOf(newCustomStyleDTO.getScaleValue());
			
			stile.setIdGeotiff(dto.getId());
			String desc  =String.format("%s_%s", scale.name(), newCustomStyleDTO.getName());
			stile.setDescrizione(desc);
			stile.setStato(false);
			stile.setGeotiffBase64("iVBORw0KGgoAAAANSUhEUgAAAFoAAABaCAIAAAC3ytZVAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAA3jSURBVHhe7VxtrFTFGd57KU1qg9VWrOY2NnxEsKEaxSKKCXIvYFL8jP3hB9e0KRiL9YdBm9QaW5TaP0qtba1QEhEVYmlM9IckUmPaH8YfeKkhQqpWqR/lwhK5rUDZM++c7fPMnN17duacs3t2z+62iQ8vy96zc2beeeb9mjN7KVU/Qwyf0dGAXtChQ62rEoqEfKNDXKlWw/Lh8Mkn9WO/CtfcKVevqCxbKjfeEN63Tj/ycLjzpWqIVlX8xW24KxTcqHCv7bB76CYdmA0nZWZVLsuDD6ilI0GpJKVSWCqp2hv7aqV+BZ/ijbZvRkf1s9vV8ROmUxCl6z0Xjq7QIaEKsarVUH94QM+ZgylVahPGDO2bvAKOeO8dt1dVRdG+NEaJxisOhdERVjVtGlrifbmsv/ddOwH72jYLVtCJNRnbFX98/LeavgdT4Quoj/ToDIXRwQChRcqHZdo0eERQGnCmVKyAGvrdLTeagARj/N+gAxTAJhjt3n9fZs60S+eo3j3BWCBFr14NDRBtqQkjdfvomI5QwTv0ud8wFtGRU7R3L+6qlAYQm9RTWwVRqwp12kf7dGBsQbB8+mmbBVoU6I0JWJHPT5VzhvR5c/WCS/Tli/RF8/WMc+Tss/hRLbnkcjp1+ml6YkKYy9u0kfbpwIDh4CCmVykNOmplSHDrTcHevSJ0MIFlIwJDe/bFUoPLG1aD0GQmGN2uXfqULzo9ZEhE4vZtkYr50Q4diFuVjz+y3uEo5AjWGfqp4WH9l1dZesGm7D8tAgSxcquqiaP6nrtl+vSmlmgtK7zggvaCa246sGzyyivW2iGONlZAkw1yavNmJkPDoAErKPwT9dUMbMlSlJVpBKSvSxfW+3fGhdQVo26M9PlIyU1H5Udrmy4RGugdf6C140+BMFNTprRRK1e2ooZSQQ5jzEUHmFarVzlDxsUul37+eSyhiQgmouVbnmZAl7AW0zUg58+rj+tLZLxHP0EGjm5vhhx0BKtXpQ1sRc2fD6sWrF+vAEsJ39itpmTFcriVnDjR4qq0RAfmGNyz1hkmLqApeGOPyhMXigJrUljuV5pEWWVNqhma08FI9sqfmgz26b+R7QvcO7QOBFvuGKHkpo3QhGklSdTAgHlS0ES9Fuj46COna1/0FYt76SOJYNI5NJ69bE0DexYdWHC8ZseLuuhly3KVFN0ANlD646zFk+9cj2iDP9ENHppYB+pOmF8UopuJHhnpLx0AvIEVraebFVxXH7yXmw6N6lEC9cyzaf2miRpe0tEWqiDI39+tP3ByBEsbGDtKDHPJdCDosNRJT+lpgvZqZCTqpX9AGFH79jm6WaGGVw5jcxQ1bUQaHVU59zynIyvcSn/6KWOn99GkmDjSX2BF1b0/OekoZoTV6pHDiQWzSwfyFvPRgffTQnSwe0xxHyJ6+fLUNhjvisXcb/QVcIZgIOH5gJQGsJZs4OVdlw4GZy0ya5bvJtjIq4UL+YDF9AJrY+z0mk3KsmX9jaycCZQ05hBXzF6RHTu4l2iERwcIKR/GDYnZBHeb4o7TZAmK4mf58rT4wk6uWGxU6g/skxTZ+IRvxdBNpkzBXKOmNfjOUpVp0xLjgnBv5tZa6A+x02lZF+iBeiRq2idQwySXgW5q31tRoxoa6IDxhOWyn6KC0iBNoGYXPhBH0mwEAp/yzbJnoAkfP8aFcbQyMQ7z0TK5xg10IL2Gq7/vmwauyLZtaVwAmKuk5xpcD4aXpN7cffBIdGjIoYPWAd0msP1PpMNMN3FKYBEBwI/DcSCPwEacIeMiIyOSXg52FaxTx8YSdVOjK+PPqBro0B8eAGHODRC1+ffmqV7UMA3oNRwZSewBQhMzuaZ5R4XDDKmxqfW04krHlrkxdsyZ47SGsAt7Qwuz4PZo+XKnh7gg1/ScjEjz4Lnt/lLRX8YPmkZERAcmjLXlZ94NMPK8E0DsTLMRCHJNX54FYNBE66iM3gxnQmmJNhEdqDKlXEZJ6zuY+vOr2VHDB/KIIHZ6XdUFNhI17SFYXi642NXEMIJNHR9dTVqHiHrgAaepFa5kTjoA3NDURnocV7mzePRRRw0rLF4d69BLlzqNrICO9nYfPMtOjyNgqsd7X6xQOHHUUcOK2rPHPgSp0VHV/kpS41tvzndu1ghrI063cYFP9c5GeOjJ8JEQH9euxeKhSc1ZTBx1G8Gp9u7FZ3ljRx1cEPScvvelZqhZU54+FAs7CxkYSFBmxQrrAREdgNvCWAe/otYx0AVqVqfzuiCYIY60yXd+6OuvS7AOvJpPa85SPuxzRkXbtQsHsLCMfQ0H6lU9Iq+/lpA98WpmWnOWLU/6jeRzU9t2Ex/02/Q4wuqwJ3vf8NBBZ2gI5m5r0xodGx5xWkDkrK8Wx0YEjdjpDVQX2kiX9742mjrjetax5k6/kT5vbuEP+NAd9/veWFaoQ/frEWdQCPWJ1x1y9QqnBeWSSwq3DgD5LnvvS766lmvQrzNcJAHrzYiOyrKkGuzyRV3aXIBk5Ne0yArTpU/ZdkUDXforwSv8yl/dOm68wWlBuWh+N6zDggfcmTUr+OrG4FyJxrEiiVtHeN+6hNgx4+tpxzNFIeMZGqUL9Uiis7ixI3zkYacFRM4+q3vWYaG1yrAReFPh+xpMyHcWL7PsfCnRo4Ju8wHlMPrISLaNFJhrZP9+t38z04a6AyZEhhqFi9N9OrBeyCNqeImvgBWoYWrWYtxWXnxBeV/d5dDm04gOwGkBYaXY5aKoDsw1Y++L1StqXyPnz/N5l9mz7aeTdPjOAgPWu3Zh9Qos1dOAAThO9r6ms1xjvpXAIyjfMeXaa83T5RodKhS/EVjUXziFpwFdZyMCxsk406OHDy+JmrYBXQ1gHdY1nJ5/tq7xeUeow9FRp5EVtOpBAIkjY+8LoY3k/C6xBQ/b337H6c2K+scB+93TGh2iw6efxQcJNnLyP/GTiB4A4Qqx03deK4ysbeUaxEF15w+d3qL5kgHOMaID3KjjJ2iNsaZW1D139yB2OLD7GkeTuICvvCUiFtU3OnuFz4MbnqSbCeukB2dy6qm9pwOw9YivjxUsm801rWsWVip+b6BDbd2CCTZUpRZyx+1OawjtxabbnnPCM71m5zUtKWUaqWuu8isOOISSSSubpIPncCrwnQWiL13I8NFzOiyk2XkNg322cuZUOjEYqVO/FM8UMeswXSbSwehFvvrDBw05c++LXBM1TYHWon96fyId8uB6ZN86GujQwOO/SQgfkFWr+mYeVK3JviZ6PpIOstZ4C67wEJZkTd7aEDvIYtK3rhlv8KZP1mEhYdbet4IFS7ERrGK4YQMNvPEWkBsMDSGjmklHaKADQNSUlTclroO68MIuPRxrEfTl9PMaStK+BjonRw0QFAuiFi4dCDo2Pyd2Ee7ezWjPwNQHcJ2B5md6kXrYKGMuaklCbkKKkUWX+dWtZx2oyJTSt93m/wIrOMJ4mt+FyFf/FAusdsZ5Dde8ZiP8zsabe3glodmA+teEboEOLkBiBLGiSoOFnzbkBZTMOK/BdfqUOZJP9HpcPDljrp1q1GMNLh0WIERtecrpxQrIVpueiAqz/oFTbWYjamgo8fcWwBef8vlhJpUOBNSqqNNPS2QXog+Ns0m/we9DeLplC5Prpk1BioGn0AH7QBw6dswO5gxJa8TrwX8mEtxLYE6MnY3qpQlMBmqrM85grknZ/iXTYQGXCbdvw2B+HLGMqH67DIA8kr33rQuSAz0dVRejSjIy6cBY+DtvXhr9YETefcc+OOkjYKPYy2GqjnqOUNudO2n5KZ4CZNFhgcHCpK+4W+Fv+4yNsVFfwe1FUxt56Od+3eWgOR0AJstg4fQeE/nxvVwi5vFeE4MowEoI9cjXhhyt6kK/RjEW3ZGF1uhAbRcEzhhxQVVDMzEto3t6BX7n8c2/YsKV9P/4pNqybi3RQWDdJ45iVGekuDC+bvwdo31PbAQhQIkg12ZHDT1rZuuL1DIdrHlFTpxwBouLVYv57Pgx097QUiwz6JK5hCEg/OUG678ZkZ5vaEGtZsAcdABYkKAaqqlT46P6Qj1mz9ZjY4aN4pIx6eUvn8j99zNlNg7qS7DgW2icC/noAPiLlFDMnGWlKYSP6DjmNXhuO9rzSQruNPbSuuny3IyDoUjmf/CiKyfVNVeh23r/zrgQQxN10794iOO1PJZFbjoAeyon374ybZtXF6s0RC24WP36sfDoJ3lLFH55/m9vy5of0AdTKIgLx8Lryy+37iBxtEOHBYaTD95DQqmUpjg6ZUhE0PXXyeuvhYcOgh1Qazmyb/Cq9u9XL74g53/TLLXbQ4YEpcHKmdM72V62T4ex3xBbQ7mSu4am62YFzdDYinPd+dGag38UkChRn5s2MbLkdJA42qfDAu6NvW9QPmxVdybZoti72rsXrKHiODljLojg/33TWeTumA74DBaDAa8qf9yhBgehHybmrHbhgv6tBalFl6kjRzC6ifBGOkCndNTBxKFD7BfVvrdARy6fb0P46Py0LyNhiZg9agcOEkdhdMQB1eRIWa28peb/nbIDfpHFQAGJnn6mrF9vTaFwdIUOCRWPRaoiKDfGD1ZGb7YzsROLz7N1Iadbt6AwhhEqeAaCFkrCotEVOhywgAJHwJ4xuesuvWIF/y/KRsPBe/uKH/EK1mTWLHXtNWrdOhkfhyGwvuyGPTSiF3RgJSl0cR7iMMaQI5Oo+ZOEgTL2xJOA+nXeifBs7rV/enC+0ws6/m9Qrf4XfjNnON6tuwsAAAAASUVORK5CYII=");
			stile.setLegendBase64("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");
			stiliService.save(stile);
			StyleUtils style = new StyleUtils(customColor, newCustomStyleDTO.getMinValue(), newCustomStyleDTO.getMaxValue(), newCustomStyleDTO.getUseOpacity().booleanValue(), newCustomStyleDTO.getFixedOpacity(), newCustomStyleDTO.getUseSpectrum().booleanValue(),
					newCustomStyleDTO.getUseReverseGradient().booleanValue(), scale, newCustomStyleDTO.getUserLevels());
			StiliDTO stiliDTO = style.convert(geotiff, dto.getId(), dto.getUom());
			PrvConverterUtils.copyPropertiesNotNull(stile, stiliDTO);
			stile.setStato(true);
			stiliService.save(stile);
			log.trace("stile={}", stile);
		} catch (Exception e) {
			log.error(e.getMessage());
			stile.setIdGeotiff(dto.getId());
			stile.setStato(false);
			stiliService.save(stile);
			throw e;
		} finally {
			dto.setProcessed(true);
			geotiffService.update(dto);
		}
	}


	@Override
	public void generateStyle(GeotiffAllDTO dto, boolean useOpacity, boolean useSpectrum, String descrizione)
			throws Exception {
		File geotiff = new File(prvRestConfig.getGeotiffBasePath(), dto.getNomeFile());
		Stili stile = new Stili();
		try {
			if (!geotiff.exists()) {
				throw new FileNotFoundException("END - file=" + geotiff.getAbsolutePath() + " not found");
			}
			Double minValue = null;
			Double maxValue = null;
			Color customColor = new Color(155, 155, 255);
			Double fixedOpacity = null;
			for (SCALE scale : SCALE.values()) {
				stile = new Stili();
				stile.setIdGeotiff(dto.getId());
				stile.setDescrizione(scale.name() + descrizione);
				StyleUtils style = new StyleUtils(customColor, minValue, maxValue, useOpacity, fixedOpacity,
						useSpectrum, false, scale, new ArrayList<>());
				StiliDTO stiliDTO = style.convert(geotiff, dto.getId(), dto.getUom());
				PrvConverterUtils.copyPropertiesNotNull(stile, stiliDTO);
				stile.setStato(true);
				stiliService.save(stile);
				log.trace("stile={}", stile);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			stile.setIdGeotiff(dto.getId());
			stile.setStato(false);
			stiliService.save(stile);
			throw e;
		} finally {
			dto.setProcessed(true);
			geotiffService.update(dto);
		}
	}

	@Override
	public GeotiffAllDTO createStyleByGeotiffId(int id) throws Exception {
		log.debug("START - trying to create geotiff styles");
		try {
			Optional<Geotiff> geotiff;
			geotiff = geotiffRepository.findById(id);
			GeotiffAllDTO gDto = null;
			if (!geotiff.isEmpty()) {
				Geotiff g = geotiff.get();
				gDto = new GeotiffAllDTO();
				PrvConverterUtils.copyProperties(gDto, g);
				generateStyle(gDto, false, false, "");
				generateStyle(gDto, true, false, " CON TRASPARENZA");
				generateStyle(gDto, true, true, " CON SPECTRUM E TRASPARENZA");
				generateStyle(gDto, false, true, " CON SPECTRUM");
				log.info("END - geotiff styles succesfully created{}", gDto);
			}
			return gDto;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("END - Internal error, message={}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public GeotiffAllDTO createCustomStyleByGeotiffId(int id, CustomStiliDTO newCustomStyleDTO) throws Exception {
		log.debug("START - trying to create geotiff styles");
		try {
			Optional<Geotiff> geotiff;
			geotiff = geotiffRepository.findById(id);
			GeotiffAllDTO gDto = null;
			if (geotiff.isPresent()) {
				Geotiff g = geotiff.get();
				gDto = new GeotiffAllDTO();
				PrvConverterUtils.copyProperties(gDto, g);
				generateCustomStyle(gDto, newCustomStyleDTO);
				log.info("END - geotiff styles succesfully created{}", gDto.toString());
			} else {
				log.warn("END - geotiff with id={} NOT FOUND", id);
			}
			return gDto;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("END - Internal error, message={}", e.getMessage(), e);
			throw e;
		}
	}

}
