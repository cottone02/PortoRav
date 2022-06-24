/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package it.rjcsoft.prv.service;

import com.querydsl.core.types.Predicate;
import it.rjcsoft.prv.dto.banchina.BanchinaDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBanchinaService {
    
    public List<BanchinaDTO> getAllBanchine();
    
    public Page<BanchinaDTO> getAllByPage(Predicate predicate, Pageable pageable);
    
    public BanchinaDTO save(BanchinaDTO banchina)throws BaseEx;
    
    public Boolean delete(int id)throws BaseEx;
    
    public Boolean update(BanchinaDTO banchina)throws BaseEx;
    
}
