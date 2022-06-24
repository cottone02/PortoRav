package it.rjcsoft.prv.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import it.rjcsoft.prv.model.SchedaDueDotazioniAzienda;
import it.rjcsoft.prv.repository.spec.bean.SearchCriteria;

public class SchedaDueSpecification implements Specification<SchedaDueDotazioniAzienda> {

	private static final long serialVersionUID = 8782024973027644107L;
	private transient SearchCriteria criteria;

	public SchedaDueSpecification() {
		super();
	}

	public SchedaDueSpecification(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(final Root<SchedaDueDotazioniAzienda> root, final CriteriaQuery<?> query,
			final CriteriaBuilder builder) {
		
		switch (criteria.getOperation()) {
		case EQUALITY:
			return builder.equal(root.get(criteria.getKey()), criteria.getValue());
		case NEGATION:
			return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
		case GREATER_THAN:
			return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
		case LESS_THAN:
			return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
		case LIKE:
			return builder.like(builder.lower(root.get(criteria.getKey())),
					criteria.getValue().toString().toLowerCase());
		case STARTS_WITH:
			return builder.like(builder.lower(root.get(criteria.getKey())),
					criteria.getValue().toString().toLowerCase() + "%");
		case ENDS_WITH:
			return builder.like(builder.lower(root.get(criteria.getKey())),
					"%" + criteria.getValue().toString().toLowerCase());
		case CONTAINS:
			return builder.like(builder.lower(root.get(criteria.getKey())),
					"%" + criteria.getValue().toString().toLowerCase() + "%");
		default:
			return null;
		}
	}

	public SearchCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SchedaDueSpecification [criteria=");
		builder.append(criteria);
		builder.append("]");
		return builder.toString();
	}

}
