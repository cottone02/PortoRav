package it.rjcsoft.prv.repository.spec;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import it.rjcsoft.prv.model.SchedaDueDotazioniAzienda;
import it.rjcsoft.prv.repository.spec.bean.SearchCriteria;

public class SchedaDueSpecificationBuilder {

	private final List<SearchCriteria> params;

	public SchedaDueSpecificationBuilder() {
		params = new ArrayList<>();
	}

	public final SchedaDueSpecificationBuilder with(final String key, final String operation, final Object value,
			final String prefix, final String suffix) {
		return with(null, key, operation, value, prefix, suffix);
	}

	public final SchedaDueSpecificationBuilder with(SchedaDueSpecification spec) {
		params.add(spec.getCriteria());
		return this;
	}

	public final SchedaDueSpecificationBuilder with(SearchCriteria criteria) {
		params.add(criteria);
		return this;
	}

	public final SchedaDueSpecificationBuilder with(final String orPredicate, final String key, final String operation,
			Object value, final String prefix, final String suffix) {
		params.add(new SearchCriteria(orPredicate, key, operation, prefix, value, suffix));
		return this;
	}

	public Specification<SchedaDueDotazioniAzienda> build() {
		if (params.isEmpty())
			return null;
		
		Specification<SchedaDueDotazioniAzienda> result = new SchedaDueSpecification(params.get(0));
		
		for (int i = 1; i < params.size(); i++) {
			Specification<SchedaDueDotazioniAzienda> currResult = new SchedaDueSpecification(params.get(i));
			if (result == null) {
				result = currResult;
				continue;
			}
			Specification<SchedaDueDotazioniAzienda> sp = Specification.where(result);
			if (sp == null) {
				continue;
			}
			if (params.get(i).isOrPredicate()) {
				result = sp.or(currResult);
			} else {
				result = sp.and(currResult);
			}
		}
		
		return result;
	}

}
