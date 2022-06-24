
package it.rjcsoft.prv.service;

import com.querydsl.core.types.Predicate;
import it.rjcsoft.prv.dto.azienda.AziendaFullDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.model.Utente;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAziendaService {
    
    public Page<AziendaFullDTO> getAll(Predicate predicate,Pageable pageable);
    
    public AziendaFullDTO createAzienda (AziendaFullDTO azienda) throws BaseEx;
    
    public boolean updateAzienda(AziendaFullDTO aziendaUpd) throws BaseEx;
    
    public boolean deleteAzienda (int id) throws BaseEx;
    
    public List<Utente> getUtentiByIdAzienda(int id) throws BaseEx;
    
}
