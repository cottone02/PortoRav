package it.rjcsoft.prv.utils;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import it.rjcsoft.prv.dto.CodiceIstatAttivitaDTO;
import it.rjcsoft.prv.dto.censimentoazienda.CensimentoAziendaDTO;
import it.rjcsoft.prv.dto.censimentoazienda.CensimentoUpdateDTO;
import it.rjcsoft.prv.dto.censimentodotazioniazienda.CensimentoDotazioniDTO;
import it.rjcsoft.prv.dto.censimentodotazioniazienda.DotazioneDTO;
import it.rjcsoft.prv.dto.censimentointerventi.CensimentoInterventiDTO;
import it.rjcsoft.prv.dto.censimentointerventi.InterventoDTO;
import it.rjcsoft.prv.dto.censimentoprodotti.CensimentoProdottiDTO;
import it.rjcsoft.prv.dto.censimentoprodotti.ClasseGranulometricaDTO;
import it.rjcsoft.prv.dto.censimentoprodotti.ProdottoDTO;
import it.rjcsoft.prv.dto.censimentoprodottiallegati.ProdottiAllegatiDTO;
import it.rjcsoft.prv.dto.censimentoprotezioni.CensimentoProtezioneDTO;
import it.rjcsoft.prv.dto.censimentoprotezioni.ProtezioneContenimentoDTO;
import it.rjcsoft.prv.dto.companyentity.CompanyEntityCompleteDTO;
import it.rjcsoft.prv.dto.companyentity.CompanyEntityDTO;
import it.rjcsoft.prv.dto.cumuliaperto.CumuliApertoLocalizzazioneDTO;
import it.rjcsoft.prv.dto.dettagliositodepositi.TipoDepositoStoccaggioDTO;
import it.rjcsoft.prv.dto.dettagliositotrasporti.TipoTrasportoInDepositoDTO;
import it.rjcsoft.prv.dto.geotiff.GeotiffAllDTO;
import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.LavorazioneDTO;
import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.LavorazioneProdottoDettagliFullDTO;
import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.LavorazioneProdottoDettagliInsertFullDTO;
import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.TipoLavorazioneDTO;
import it.rjcsoft.prv.dto.movimentazione.DettaglioMovimentazioneDTO;
import it.rjcsoft.prv.dto.prodottoricevuto.ProdottoRicevutoUpdateDTO;
import it.rjcsoft.prv.dto.stili.StiliDTO;
import it.rjcsoft.prv.dto.utente.UtenteFullDTO;
import it.rjcsoft.prv.model.CensimentoAzienda;
import it.rjcsoft.prv.model.CensimentoDotazioniAzienda;
import it.rjcsoft.prv.model.CensimentoInterventiMitigazione;
import it.rjcsoft.prv.model.CensimentoProdotti;
import it.rjcsoft.prv.model.CensimentoProtezioniContenimento;
import it.rjcsoft.prv.model.ClasseGranulometrica;
import it.rjcsoft.prv.model.CodiceIstatAttivita;
import it.rjcsoft.prv.model.CompanyEntity;
import it.rjcsoft.prv.model.CumuliApertoLocalizzazione;
import it.rjcsoft.prv.model.DettaglioMovimentazione;
import it.rjcsoft.prv.model.Dotazione;
import it.rjcsoft.prv.model.Geotiff;
import it.rjcsoft.prv.model.InterventoMitigazionePolveri;
import it.rjcsoft.prv.model.Lavorazione;
import it.rjcsoft.prv.model.LavorazioneProdottoDettagli;
import it.rjcsoft.prv.model.ProdottiAllegati;
import it.rjcsoft.prv.model.Prodotto;
import it.rjcsoft.prv.model.ProdottoRicevuto;
import it.rjcsoft.prv.model.ProtezioneContenimentoEmissioni;
import it.rjcsoft.prv.model.Stili;
import it.rjcsoft.prv.model.TipoDepositoStoccaggio;
import it.rjcsoft.prv.model.TipoLavorazione;
import it.rjcsoft.prv.model.TipoTrasportoInDeposito;
import it.rjcsoft.prv.model.Utente;

public class PrvConverterUtils {

	private PrvConverterUtils() {
	}

	public static List<String> getPropertyNames(BeanWrapper src) {
		List<String> list = new ArrayList<>();
		try {
			PropertyDescriptor[] propertyDescriptor = src.getPropertyDescriptors();
			for (PropertyDescriptor currPropertyDesc : propertyDescriptor) {
				list.add(currPropertyDesc.getName());
			}
			if (list.isEmpty()) {
				list = null;
			}
		} catch (Exception e) {
			list = null;
		}

		return list;

	}

	public static <T> T convert(Object map, Class<T> toValueType) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.convertValue(map, toValueType);
		} catch (Exception e) {
			return null;
		}
	}

	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}

		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	public static boolean copyProperties(final Object dest, final Object orig) {
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean copyPropertiesNotNull(final Object dest, final Object orig) {
		try {
			org.springframework.beans.BeanUtils.copyProperties(orig, dest, getNullPropertyNames(orig));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean copyPropertiesIgnoreField(final Object dest, final Object orig, String... fieldToIgnore) {
		try {
			org.springframework.beans.BeanUtils.copyProperties(orig, dest, fieldToIgnore);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static CensimentoAziendaDTO initCensimentoAziendaDTO(CensimentoAzienda model) {
		CensimentoAziendaDTO dto = new CensimentoAziendaDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static CodiceIstatAttivitaDTO initCodiceIstatDTO(CodiceIstatAttivita model) {
		CodiceIstatAttivitaDTO dto = new CodiceIstatAttivitaDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static CensimentoDotazioniDTO initCensimentoDotazioniDTO(CensimentoDotazioniAzienda model) {
		CensimentoDotazioniDTO dto = new CensimentoDotazioniDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static DotazioneDTO initDotazioneDTO(Dotazione model) {
		DotazioneDTO dto = new DotazioneDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static TipoLavorazioneDTO initTipoLavorazioneDTO(TipoLavorazione model) {
		TipoLavorazioneDTO dto = new TipoLavorazioneDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static LavorazioneDTO initLavorazioneDTO(Lavorazione model) {
		LavorazioneDTO dto = new LavorazioneDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static Dotazione initDotazione(DotazioneDTO dto) {
		Dotazione model = new Dotazione();
		if (copyProperties(model, dto)) {
			return model;
		}
		return null;
	}

	public static Lavorazione initLavorazione(LavorazioneDTO dto) {
		Lavorazione model = new Lavorazione();
		if (copyProperties(model, dto)) {
			return model;
		}
		return null;
	}

	public static LavorazioneProdottoDettagliFullDTO initLavorazioneProdottoDettagliFullDTO(
			LavorazioneProdottoDettagli model) {
		LavorazioneProdottoDettagliFullDTO dto = new LavorazioneProdottoDettagliFullDTO();
		if (copyProperties(dto, model)) {
			dto.setProdottoDettagliId(model.getProdottoDettaglioId());
			dto.setLavorazioneDTO(initLavorazioneDTO(model.getLavorazione()));
			dto.setTipoLavorazione(model.getTipoLavorazione().getTipo());
			return dto;
		}
		return null;
	}

	public static List<LavorazioneProdottoDettagliFullDTO> initLavorazioneProdottoDettagliFullListDTO(
			List<LavorazioneProdottoDettagli> model) {
		if (CollectionUtils.isNotEmpty(model)) {
			List<LavorazioneProdottoDettagliFullDTO> list = model.stream()
					.map(PrvConverterUtils::initLavorazioneProdottoDettagliFullDTO)
					.collect(Collectors.toList());
			Collections.sort(list, Comparator.comparingInt(LavorazioneProdottoDettagliFullDTO::getId));
			return list;
		} else {
			return null;
		}
	}

	public static CensimentoUpdateDTO initCensimentoUpdateDTO(CensimentoAzienda model) {
		CensimentoUpdateDTO dto = new CensimentoUpdateDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static ProtezioneContenimentoDTO initProtezioneDTO(ProtezioneContenimentoEmissioni model) {
		ProtezioneContenimentoDTO dto = new ProtezioneContenimentoDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static ProtezioneContenimentoEmissioni initProtezione(ProtezioneContenimentoDTO dto) {
		ProtezioneContenimentoEmissioni model = new ProtezioneContenimentoEmissioni();
		if (copyProperties(model, dto)) {
			return model;
		}
		return null;
	}

	public static CensimentoProtezioneDTO initCensimentoProtezioniDTO(CensimentoProtezioniContenimento model) {
		CensimentoProtezioneDTO dto = new CensimentoProtezioneDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static InterventoDTO initInterventoDTO(InterventoMitigazionePolveri model) {
		InterventoDTO dto = new InterventoDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static InterventoMitigazionePolveri initIntervento(InterventoDTO dto) {
		InterventoMitigazionePolveri model = new InterventoMitigazionePolveri();
		if (copyProperties(model, dto)) {
			return model;
		}
		return null;
	}

	public static CensimentoInterventiDTO initCensimentoInterventiDTO(CensimentoInterventiMitigazione model) {
		CensimentoInterventiDTO dto = new CensimentoInterventiDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static CompanyEntityDTO initCompanyEntityDTO(CompanyEntity model) {
		CompanyEntityDTO dto = new CompanyEntityDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static CompanyEntityCompleteDTO initCompanyEntityCompleteDTO(CompanyEntity model) {
		CompanyEntityCompleteDTO dto = new CompanyEntityCompleteDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static ProdottoDTO initProdottoDTO(Prodotto model) {
		ProdottoDTO dto = new ProdottoDTO();
		if (copyProperties(dto, model)) {
			dto.setPolverosita(model.getPolverosita().getTipo());
			return dto;
		}
		return null;
	}

	public static ClasseGranulometricaDTO initClasseGranulometricaDTO(ClasseGranulometrica model) {
		ClasseGranulometricaDTO dto = new ClasseGranulometricaDTO();
		if (model != null && copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static CensimentoProdottiDTO initCensimentoProdottiDTO(CensimentoProdotti model) {
		CensimentoProdottiDTO dto = new CensimentoProdottiDTO();
		if (copyProperties(dto, model)) {
			dto.setProdottoId((model.getProdotto() != null) ? model.getProdotto().getId() : null);
			dto.setGranulometriaId(
					(model.getClasseGranulometrica() != null) ? model.getClasseGranulometrica().getId() : null);
			return dto;
		}
		return null;
	}

	public static GeotiffAllDTO initGeotiffAllDTO(Geotiff model) {
		GeotiffAllDTO dto = new GeotiffAllDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static StiliDTO initStiliDTO(Stili model) {
		StiliDTO dto = new StiliDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static ProdottoRicevutoUpdateDTO initProdottoRicevutoUpdateDTO(ProdottoRicevuto model) {
		ProdottoRicevutoUpdateDTO dto = new ProdottoRicevutoUpdateDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static TipoTrasportoInDepositoDTO initTipoTrasportoInDepositoDTO(TipoTrasportoInDeposito model) {
		TipoTrasportoInDepositoDTO dto = new TipoTrasportoInDepositoDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static TipoDepositoStoccaggioDTO initTipoTrasportoInDepositoDTO(TipoDepositoStoccaggio model) {
		TipoDepositoStoccaggioDTO dto = new TipoDepositoStoccaggioDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static TipoDepositoStoccaggioDTO initTipoDepositoStoccaggioDTO(TipoDepositoStoccaggio model) {
		TipoDepositoStoccaggioDTO dto = new TipoDepositoStoccaggioDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static ProdottiAllegatiDTO initProdottiAllegatiDTO(ProdottiAllegati model) {
		ProdottiAllegatiDTO dto = new ProdottiAllegatiDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static List<ProdottiAllegatiDTO> initProdottiAllegatiDTOList(List<ProdottiAllegati> model) {
		if (CollectionUtils.isNotEmpty(model)) {
			return model.stream().map(PrvConverterUtils::initProdottiAllegatiDTO).collect(Collectors.toList());
		} else {
			return null;
		}
	}

	public static CumuliApertoLocalizzazioneDTO initCumuliApertoDTO(CumuliApertoLocalizzazione model) {
		CumuliApertoLocalizzazioneDTO dto = new CumuliApertoLocalizzazioneDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static LavorazioneProdottoDettagliInsertFullDTO initLavorazioneProdottoDettagliInsertFullDTO(
			LavorazioneProdottoDettagli model) {
		LavorazioneProdottoDettagliInsertFullDTO dto = new LavorazioneProdottoDettagliInsertFullDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static TipoLavorazione initTipoLavorazione(TipoLavorazioneDTO tipoLavorazioneDTO) {
		TipoLavorazione model = new TipoLavorazione();
		if (copyProperties(model, tipoLavorazioneDTO)) {
			return model;
		}

		return null;
	}

	public static UtenteFullDTO initUtenteDTO(Utente model) {
		UtenteFullDTO dto = new UtenteFullDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static List<CumuliApertoLocalizzazioneDTO> initCumuliApertoLocalizzazioneDTO(
			List<CumuliApertoLocalizzazione> model) {
		if (CollectionUtils.isNotEmpty(model)) {
			List<CumuliApertoLocalizzazioneDTO> list = model.stream()
					.map(PrvConverterUtils::initCumuliApertoLocalizzazioneDTO)
					.collect(Collectors.toList());
			Collections.sort(list, Comparator.comparingInt(CumuliApertoLocalizzazioneDTO::getId));
			return list;
		}
		return Collections.emptyList();

	}

	public static CumuliApertoLocalizzazioneDTO initCumuliApertoLocalizzazioneDTO(CumuliApertoLocalizzazione model) {
		CumuliApertoLocalizzazioneDTO dto = new CumuliApertoLocalizzazioneDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static DettaglioMovimentazioneDTO initDettagliMovimentazioneDTO(DettaglioMovimentazione model) {
		DettaglioMovimentazioneDTO dto = new DettaglioMovimentazioneDTO();
		if (copyProperties(dto, model)) {
			return dto;
		}
		return null;
	}

	public static List<DettaglioMovimentazioneDTO> initDettagliMovimentazioneDTO(
			List<DettaglioMovimentazione> model) {
		if (CollectionUtils.isNotEmpty(model)) {
			List<DettaglioMovimentazioneDTO> list = model.stream()
					.map(PrvConverterUtils::initDettagliMovimentazioneDTO)
					.collect(Collectors.toList());
			Collections.sort(list, Comparator.comparingInt(DettaglioMovimentazioneDTO::getId));
			return list;
		}
		return Collections.emptyList();
	}

}
