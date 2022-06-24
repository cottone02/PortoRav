/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.rjcsoft.prv.service.impl;

import com.querydsl.core.types.Predicate;
import it.rjcsoft.prv.dto.banchina.BanchinaDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.Banchina;
import it.rjcsoft.prv.repository.IBanchinaRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IBanchinaService;
import it.rjcsoft.prv.utils.PrvConverterUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class BanchinaService extends BaseService implements IBanchinaService  {

    @Autowired
    private IBanchinaRepository banchinaRepository;
    
    @Override
    public List<BanchinaDTO> getAllBanchine() {
       
        log.info("START - service getAllBanchine");
        List<Banchina> banchine = banchinaRepository.findAll();
        List<BanchinaDTO> banchineDto=new ArrayList<>();
        
        banchine.forEach(banchina->{
            BanchinaDTO dto=new BanchinaDTO();
            PrvConverterUtils.copyProperties(dto, banchina);
            banchineDto.add(dto);
        });
        return banchineDto;
    }

    @Override
    public Page<BanchinaDTO> getAllByPage(Predicate predicate, Pageable pageable) {
        log.debug("START - predicate{}, pageable{}", predicate, pageable);
        Page<Banchina> page =banchinaRepository.findAll(predicate,pageable);
        log.trace("banchinePage={}",page.getContent());
        Set<Integer> undeletableBanchine=banchinaRepository.isBanchinaDeletable();
        Page<BanchinaDTO> responsePage = page.map(currBanchina-> {
            BanchinaDTO dto=new BanchinaDTO();
            PrvConverterUtils.copyProperties(dto, currBanchina);
            if(dto!=null)
                {
                    if(!CollectionUtils.isEmpty(undeletableBanchine))
                    dto.setIsDeletable(!undeletableBanchine.contains(currBanchina.getId()));
                    else dto.setIsDeletable(Boolean.TRUE);
                    log.trace("copyProperties SUCCESS, dto={}", dto);
                }
            return dto;
        });
        
        return responsePage;
    }

    @Override
    public BanchinaDTO save(BanchinaDTO banchina)throws BaseEx {
        
        Banchina toSave=null;
        
        try{
            toSave=new Banchina();
            if(PrvConverterUtils.copyProperties(toSave, banchina))
            {
                log.info("Model - Banchina={}",toSave);
                toSave=banchinaRepository.save(toSave);
                log.info("SAVED - banchina={}",toSave);
                BanchinaDTO dto=new BanchinaDTO();
                PrvConverterUtils.copyProperties(dto, toSave);
                return dto;
            }
        }catch(Exception e){
            log.error("ERROR SAVING");
            throw new InternalError("Object not saved");
        }
        log.warn("END");
        return null;
    }

    @Override
    public Boolean delete(int id) throws BaseEx{
        log.info("START - delete Banchina id= {}",id);
        if(banchinaRepository.existsById(id))
        {
            try{
                banchinaRepository.deleteById(id);
                log.info("END - deleted {}",id);
                return true;
            }catch(Exception e)
            {
                log.error("END - not deleted {}, message {}", id, e.getMessage());
                throw new InternalError("Banchina con id ="+id+" non eliminata");
            }
        }
        log.info("Banchina con id = {} non trovata",id);
        return false;
    }

    @Override
    public Boolean update(BanchinaDTO banchina)throws BaseEx {
        
        log.debug("START - updateAzienda{}",banchina);
        Integer id=banchina.getId();
        Banchina exist=banchinaRepository.findById(id).orElseThrow(()->new PojoNotFound("Banchina con id = {} non trovata",id));
        try{
            if(PrvConverterUtils.copyProperties(exist, banchina))
            {
                log.info("Model - Azienda = {}",exist);
                exist=banchinaRepository.save(exist);
                log.info("UPDATED banchina={}",exist);
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
    
}
