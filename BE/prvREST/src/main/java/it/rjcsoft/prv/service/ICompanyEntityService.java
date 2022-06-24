package it.rjcsoft.prv.service;

import java.util.List;

import it.rjcsoft.prv.dto.companyentity.CompanyEntityDTO;
import it.rjcsoft.prv.model.CompanyEntity;

public interface ICompanyEntityService {
	public List<CompanyEntityDTO>  getAllCompany();
	public CompanyEntity getCompanyById(Integer id);
}
