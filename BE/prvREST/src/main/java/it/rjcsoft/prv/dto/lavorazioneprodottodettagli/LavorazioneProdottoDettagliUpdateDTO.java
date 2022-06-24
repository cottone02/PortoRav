package it.rjcsoft.prv.dto.lavorazioneprodottodettagli;

import javax.validation.constraints.NotNull;

public class LavorazioneProdottoDettagliUpdateDTO {
	
	@NotNull(message = "Id must be not null")
	private Integer id;

}
