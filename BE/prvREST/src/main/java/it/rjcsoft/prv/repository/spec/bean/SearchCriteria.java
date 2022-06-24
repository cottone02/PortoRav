package it.rjcsoft.prv.repository.spec.bean;

import it.rjcsoft.prv.enums.SearchOperationEnum;

public class SearchCriteria {

	private String key;
	private SearchOperationEnum operation;
	private Object value;
	private boolean orPredicate;

	public SearchCriteria() {
	}

	public SearchCriteria(final String key, final SearchOperationEnum operation, final Object value) {
		super();
		this.key = key;
		this.operation = operation;
		this.value = value;
	}

	public SearchCriteria(final String orPredicate, final String key, final SearchOperationEnum operation,
			final Object value) {
		super();
		this.orPredicate = orPredicate != null && orPredicate.equals(SearchOperationEnum.OR_PREDICATE_FLAG);
		this.key = key;
		this.operation = operation;
		this.value = value;
	}

	public SearchCriteria(final String orPredicate, String key, String operation, String prefix, Object value,
			String suffix) {
		SearchOperationEnum op = SearchOperationEnum.getSimpleOperation(operation.charAt(0));
		if (op != null && op == SearchOperationEnum.EQUALITY) {
			final boolean startWithAsterisk = value.toString().startsWith(SearchOperationEnum.ZERO_OR_MORE_REGEX);
			final boolean endWithAsterisk = value.toString().endsWith(SearchOperationEnum.ZERO_OR_MORE_REGEX);
			if (startWithAsterisk && endWithAsterisk) {
				op = SearchOperationEnum.CONTAINS;
				value = value.toString().substring(SearchOperationEnum.ZERO_OR_MORE_REGEX.length());
				value = value.toString().substring(0,
						value.toString().length() - SearchOperationEnum.ZERO_OR_MORE_REGEX.length());
			} else if (startWithAsterisk) {
				op = SearchOperationEnum.ENDS_WITH;
				value = value.toString().substring(SearchOperationEnum.ZERO_OR_MORE_REGEX.length());
			} else if (endWithAsterisk) {
				op = SearchOperationEnum.STARTS_WITH;
				value = value.toString().substring(0,
						value.toString().length() - SearchOperationEnum.ZERO_OR_MORE_REGEX.length());
			} else {
				if ("true".equalsIgnoreCase(value.toString()) || "false".equalsIgnoreCase(value.toString())) {
					value = Boolean.valueOf(value.toString());
				}
			}
		}
		this.orPredicate = orPredicate != null && orPredicate.equals(SearchOperationEnum.OR_PREDICATE_FLAG);
		this.key = key;
		this.operation = op;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public SearchOperationEnum getOperation() {
		return operation;
	}

	public void setOperation(SearchOperationEnum operation) {
		this.operation = operation;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public boolean isOrPredicate() {
		return orPredicate;
	}

	public void setOrPredicate(boolean orPredicate) {
		this.orPredicate = orPredicate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((operation == null) ? 0 : operation.hashCode());
		result = prime * result + (orPredicate ? 1231 : 1237);
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchCriteria other = (SearchCriteria) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (operation != other.operation)
			return false;
		if (orPredicate != other.orPredicate)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SearchCriteria [key=");
		builder.append(key);
		builder.append(", operation=");
		builder.append(operation);
		builder.append(", value=");
		builder.append(value);
		builder.append(", orPredicate=");
		builder.append(orPredicate);
		builder.append("]");
		return builder.toString();
	}

}
