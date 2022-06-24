package it.rjcsoft.prv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.dto.companyentity.CompanyEntityDTO;
import it.rjcsoft.prv.model.CompanyEntity;
import it.rjcsoft.prv.repository.ICompanyEntityRepository;
import it.rjcsoft.prv.utils.PrvConverterUtils;

@Service
public class CompanyEntityService extends BaseService implements ICompanyEntityService {

	@Autowired
	private ICompanyEntityRepository companyEntityRepository;

	@Override
	public List<CompanyEntityDTO> getAllCompany() {

		try {
			log.info("Start getAllCompany");
			List<CompanyEntityDTO> companies = new ArrayList<>();
			List<CompanyEntity> companyEntity = companyEntityRepository.findAll();
			companyEntity.forEach(company -> companies.add(PrvConverterUtils.initCompanyEntityDTO(company)));
			return companies;
		} catch (Exception e) {
			log.error("Error during getAllCompany {}", e.getMessage(), e);
			throw e;
		}

	}

	@Override
	public CompanyEntity getCompanyById(Integer id) {
		try {
			log.info("Start companyById={}", id);
			Optional<CompanyEntity> companyEntity = companyEntityRepository.findById(id);
			if (companyEntity.isPresent()) {
				return companyEntity.get();
			}

			return null;
		} catch (Exception e) {
			log.error("Error during getCompanybyId {}", e.getMessage(), e);
			throw e;
		}
	}

}
