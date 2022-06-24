package it.rjcsoft.prv.utils;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.rjcsoft.prv.dto.CodiceIstatAttivitaDTO;
import it.rjcsoft.prv.dto.censimentodotazioniazienda.DotazioneDTO;
import it.rjcsoft.prv.dto.censimentointerventi.InterventoDTO;
import it.rjcsoft.prv.dto.censimentoprodotti.ClasseGranulometricaDTO;
import it.rjcsoft.prv.dto.censimentoprodotti.ProdottoDTO;
import it.rjcsoft.prv.dto.censimentoprotezioni.ProtezioneContenimentoDTO;
import it.rjcsoft.prv.model.ClasseGranulometrica;
import it.rjcsoft.prv.model.CodiceIstatAttivita;
import it.rjcsoft.prv.model.Dotazione;
import it.rjcsoft.prv.model.InterventoMitigazionePolveri;
import it.rjcsoft.prv.model.Prodotto;
import it.rjcsoft.prv.model.ProtezioneContenimentoEmissioni;

public class PrvUtils {

	private PrvUtils() {
	}

	public static String generateTID() {
		return String.format("%d_%02d", System.currentTimeMillis(), ((int) (Math.random() * 100.0)));
	}

	public static CodiceIstatAttivitaDTO findCodiceIstatAttivitaDto(final String codIstatAttivita,
			List<CodiceIstatAttivita> codiciIstat) {
		Iterator<CodiceIstatAttivita> iterator = codiciIstat.iterator();
		CodiceIstatAttivitaDTO codiceIstatAttivitaDTO = new CodiceIstatAttivitaDTO();
		while (iterator.hasNext()) {
			CodiceIstatAttivita codiceIstatAttivita2 = iterator.next();
			if (StringUtils.equalsIgnoreCase(codIstatAttivita, codiceIstatAttivita2.getCodice())) {
				return PrvConverterUtils.initCodiceIstatDTO(codiceIstatAttivita2);
			}
		}
		return codiceIstatAttivitaDTO;
	}

	public static DotazioneDTO findDotazioneDTO(final Integer dotazioneId, List<Dotazione> dotazioni) {
		Iterator<Dotazione> iterator = dotazioni.iterator();
		DotazioneDTO dotazioneDTO = new DotazioneDTO();
		while (iterator.hasNext()) {
			Dotazione dotazione = iterator.next();
			if (dotazioneId == dotazione.getId()) {
				return PrvConverterUtils.initDotazioneDTO(dotazione);
			}
		}
		return dotazioneDTO;
	}

	public static ProtezioneContenimentoDTO findProtezioneDTO(Integer contenimentoId,
			List<ProtezioneContenimentoEmissioni> protezioni) {
		Iterator<ProtezioneContenimentoEmissioni> iterator = protezioni.iterator();
		ProtezioneContenimentoDTO protezioneContenimentoDTO = new ProtezioneContenimentoDTO();
		while (iterator.hasNext()) {
			ProtezioneContenimentoEmissioni protezione = iterator.next();
			if (contenimentoId == protezione.getId()) {
				return PrvConverterUtils.initProtezioneDTO(protezione);
			}
		}
		return protezioneContenimentoDTO;
	}

	public static InterventoDTO findInterventoDTO(Integer interventoId, List<InterventoMitigazionePolveri> interventi) {
		Iterator<InterventoMitigazionePolveri> iterator = interventi.iterator();
		InterventoDTO interventoDTO = new InterventoDTO();
		while (iterator.hasNext()) {
			InterventoMitigazionePolveri intervento = iterator.next();
			if (interventoId == intervento.getId()) {
				return PrvConverterUtils.initInterventoDTO(intervento);
			}
		}
		return interventoDTO;
	}

	public static ProdottoDTO findProdottoDTO(Integer prodottoId, List<Prodotto> prodotti) {
		Iterator<Prodotto> iterator = prodotti.iterator();
		ProdottoDTO prodottoDTO = new ProdottoDTO();
		while (iterator.hasNext()) {
			Prodotto prodotto = iterator.next();
			if (prodottoId == prodotto.getId()) {
				return PrvConverterUtils.initProdottoDTO(prodotto);
			}
		}
		return prodottoDTO;
	}

	public static ClasseGranulometricaDTO findClasseGranulometricaDTO(Integer granulometriaId,
			List<ClasseGranulometrica> classiGranulometriche) {
		Iterator<ClasseGranulometrica> iterator = classiGranulometriche.iterator();
		ClasseGranulometricaDTO classeGranulometricaDTO = new ClasseGranulometricaDTO();
		while (iterator.hasNext()) {
			ClasseGranulometrica classeGranulometrica = iterator.next();
			if (granulometriaId == classeGranulometrica.getId()) {
				return PrvConverterUtils.initClasseGranulometricaDTO(classeGranulometrica);
			}
		}
		return classeGranulometricaDTO;
	}


}
