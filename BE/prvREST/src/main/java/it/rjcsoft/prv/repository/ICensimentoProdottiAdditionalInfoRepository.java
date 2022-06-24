package it.rjcsoft.prv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.rjcsoft.prv.model.CensimentoProdottiAdditionalInfo;

public interface ICensimentoProdottiAdditionalInfoRepository extends ReadOnlyRepository<CensimentoProdottiAdditionalInfo, Integer> {
    
    @Query(value = "select * from _custom_censimento_prodotto_deletable(:censimentiProdottiIds)", nativeQuery = true)
    public List<CensimentoProdottiAdditionalInfo> findAllByIds(@Param("censimentiProdottiIds") String censimentiProdottiIds);
}
