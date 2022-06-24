package it.rjcsoft.prv.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

import it.rjcsoft.prv.dto.censimentodotazioniazienda.DotazioneDTO;
import it.rjcsoft.prv.dto.censimentointerventi.InterventoDTO;
import it.rjcsoft.prv.dto.censimentoprotezioni.ProtezioneContenimentoDTO;
import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.LavorazioneDTO;
import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.TipoLavorazioneDTO;

public class PrvComparatorUtils {

	private static final boolean isDigit(char ch) {
		return ((ch >= 48) && (ch <= 57));
	}

	private static final String getChunk(String s, int slength, int marker) {
		StringBuilder chunk = new StringBuilder();
		char c = s.charAt(marker);
		chunk.append(c);
		marker++;
		if (isDigit(c)) {
			while (marker < slength) {
				c = s.charAt(marker);
				if (!isDigit(c))
					break;
				chunk.append(c);
				marker++;
			}
		} else {
			while (marker < slength) {
				c = s.charAt(marker);
				if (isDigit(c))
					break;
				chunk.append(c);
				marker++;
			}
		}
		return chunk.toString();
	}

	public static int compare(String s1, String s2) {
		if (StringUtils.isBlank(s1) || StringUtils.isBlank(s2)) {
			return 0;
		}

		int thisMarker = 0;
		int thatMarker = 0;
		int s1Length = s1.length();
		int s2Length = s2.length();

		while (thisMarker < s1Length && thatMarker < s2Length) {
			String thisChunk = getChunk(s1, s1Length, thisMarker);
			thisMarker += thisChunk.length();

			String thatChunk = getChunk(s2, s2Length, thatMarker);
			thatMarker += thatChunk.length();

			int result = 0;
			if (isDigit(thisChunk.charAt(0)) && isDigit(thatChunk.charAt(0))) {
				int thisChunkLength = thisChunk.length();
				result = thisChunkLength - thatChunk.length();
				if (result == 0) {
					for (int i = 0; i < thisChunkLength; i++) {
						result = thisChunk.charAt(i) - thatChunk.charAt(i);
						if (result != 0) {
							return result;
						}
					}
				}
			} else {
				result = thisChunk.compareTo(thatChunk);
			}

			if (result != 0)
				return result;
		}

		return s1Length - s2Length;
	}

	public static void sortDotazioni(List<DotazioneDTO> dotazioneList) {
		if (CollectionUtils.isEmpty(dotazioneList)) {
			return;
		}
		Comparator<DotazioneDTO> comparator = (DotazioneDTO d1, DotazioneDTO d2) -> compare(
				RegExUtils.removeAll(d1.getAttrezzatura(), "\\s+"), RegExUtils.removeAll(d2.getAttrezzatura(), "\\s+"));
		Collections.sort(dotazioneList, comparator);
	}
	
	public static void sortTipoLavorazione(List<TipoLavorazioneDTO> tipoLavorazioneList) {
		if (CollectionUtils.isEmpty(tipoLavorazioneList)) {
			return;
		}
		Comparator<TipoLavorazioneDTO> comparator = (TipoLavorazioneDTO d1, TipoLavorazioneDTO d2) -> compare(
				RegExUtils.removeAll(d1.getTipo(), "\\s+"), RegExUtils.removeAll(d2.getTipo(), "\\s+"));
		Collections.sort(tipoLavorazioneList, comparator);
	}
	
	public static void sortLavorazione(List<LavorazioneDTO> lavorazioneList) {
		if (CollectionUtils.isEmpty(lavorazioneList)) {
			return;
		}
		Comparator<LavorazioneDTO> comparator = (LavorazioneDTO d1, LavorazioneDTO d2) -> compare(
				RegExUtils.removeAll(d1.getLavorazione(), "\\s+"), RegExUtils.removeAll(d2.getLavorazione(), "\\s+"));
		Collections.sort(lavorazioneList, comparator);
	}

	public static void sortInterventi(List<InterventoDTO> interventoList) {
		if (CollectionUtils.isEmpty(interventoList)) {
			return;
		}
		Comparator<InterventoDTO> comparator = (InterventoDTO i1, InterventoDTO i2) -> compare(
				RegExUtils.removeAll(i1.getTipologia(), "\\s+"), RegExUtils.removeAll(i2.getTipologia(), "\\s+"));
		Collections.sort(interventoList, comparator);
	}

	public static void sortProtezioni(List<ProtezioneContenimentoDTO> protezioneList) {
		if (CollectionUtils.isEmpty(protezioneList)) {
			return;
		}
		Comparator<ProtezioneContenimentoDTO> comparator = (ProtezioneContenimentoDTO p1,
				ProtezioneContenimentoDTO p2) -> compare(RegExUtils.removeAll(p1.getTipologia(), "\\s+"),
						RegExUtils.removeAll(p2.getTipologia(), "\\s+"));
		Collections.sort(protezioneList, comparator);
	}

}
