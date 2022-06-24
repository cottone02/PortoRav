package it.rjcsoft.prv.service.impl;

import java.util.List;

import com.querydsl.core.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.dto.azienda.AziendaFullDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.Azienda;
import it.rjcsoft.prv.model.Utente;
import it.rjcsoft.prv.repository.IAziendaRepository;
import it.rjcsoft.prv.repository.IUtenteRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IAziendaService;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class AziendaService extends BaseService implements IAziendaService {

    @Autowired
    private IAziendaRepository aziendaRepository;
    
    @Autowired
    private IUtenteRepository utenteRepository;
    
    @Override
    public Page<AziendaFullDTO> getAll(Predicate predicate, Pageable pageable) {
        log.debug("START - predicate{}, pageable{}",predicate, pageable);
        Page <Azienda> utentePage=aziendaRepository.findAll(predicate, pageable);
        log.trace("utentePage={}",utentePage.getContent());
        Page <AziendaFullDTO> responsePage=utentePage.map(currentUtente -> {
            AziendaFullDTO dto=new AziendaFullDTO();
            PrvConverterUtils.copyProperties(dto, currentUtente);
                if(dto!=null)
                    return dto;
                return null;
        });
        return responsePage;
    }

    @Override
    public AziendaFullDTO createAzienda(AziendaFullDTO aziendadto) throws BaseEx{
        Azienda azienda =null;
        
        try{
            azienda =new Azienda();
            if(PrvConverterUtils.copyProperties(azienda, aziendadto))
            {
                log.info("Model - Azienda {}",azienda);
                azienda=aziendaRepository.save(azienda);
                log.info("SAVED - Azienda {}", azienda);
                AziendaFullDTO dto=new AziendaFullDTO();
                PrvConverterUtils.copyProperties(dto, azienda);
                return dto;
            }
        }catch(Exception e)
            {
                log.error("ERROR SAVING");
                throw new InternalError("Oggetto non salvato");
            }
        log.warn("END");
        return null;
    }
    
    //qui risponde con response enum nell'oggetto, come lo faccio?
    // booleano true o false in base a come va?
    @Override
    public boolean updateAzienda(AziendaFullDTO aziendaUpd) throws BaseEx
    {
        log.debug("START - updateAzienda{}",aziendaUpd);
        Integer id=aziendaUpd.getId();
        Azienda azienda=aziendaRepository.findById(id).orElseThrow(()->new PojoNotFound("Azienda con id = {} non trovata",id));
        try{
            if(PrvConverterUtils.copyProperties(azienda, aziendaUpd))
            {
                log.info("Model - Azienda = {}",azienda);
                azienda=aziendaRepository.save(azienda);
                log.info("UPDATED");
                return true;
            }
        }catch(Exception e)
        {
            log.error("ERRORE Update");
            throw new InternalError("Oggetto non aggiornato");
        }
        log.warn("END copyProperties FAILED");
        return false;
    }
    
    @Override
    public boolean deleteAzienda (int id) throws BaseEx
    {
        log.info("START - deleteAzienda id= {}",id);
        if(aziendaRepository.existsById(id))
        {
            try{
                aziendaRepository.deleteById(id);
                log.info("END - deleted {}",id);
                return true;
            }catch(Exception e)
            {
                log.error("END - not deleted {}, message {}", id, e.getMessage());
                throw new InternalError("Azienda con id ="+id+" non eliminata");
            }
        }
        log.info("Azienda con id = {} non trovata",id);
        return false;
        
    }
    
    @Override
    public List<Utente> getUtentiByIdAzienda(int id) throws BaseEx
    {
        log.info("START - get Utenti by azienda id = {}",id);
        boolean esiste=aziendaRepository.existsById(id);
        if(esiste)
        {
            List<Utente> utenti=utenteRepository.findByCompanyId(id);
            log.info("END get utenti");
            if(utenti.isEmpty())
                return null;
            else return utenti;
        }
        else
            throw new PojoNotFound("Azienda con id = {} non esiste",id);
    }
    
}
