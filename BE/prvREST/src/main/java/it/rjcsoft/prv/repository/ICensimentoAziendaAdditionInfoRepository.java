package it.rjcsoft.prv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.rjcsoft.prv.dto.censimentoazienda.CensimentoAziendaAdditionalInfo;

public interface ICensimentoAziendaAdditionInfoRepository extends ReadOnlyRepository<CensimentoAziendaAdditionalInfo, Integer> {
    
    @Query(value = "select * from _custom_get_censimenti_azienda_add_info(:censimentiId)", nativeQuery = true)
    public List<CensimentoAziendaAdditionalInfo> findAllByIds(@Param("censimentiId") String censimentiId);
}
