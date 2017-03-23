package it.clevercom.echo.rd.model.jpa.helper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationQueryHelper<T> implements Specification<T> {
	private SearchCriteria criteria;

	public SpecificationQueryHelper(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (criteria.getOperation().equalsIgnoreCase(">")) {
			return cb.greaterThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase("<")) {
			return cb.lessThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase(":")) {
			// middle like
			if (root.get(criteria.getKey()).getJavaType() == String.class) {
				return cb.like(cb.lower(root.<String>get(criteria.getKey())),
						"%" + criteria.getValue().toString().toLowerCase() + "%");
			} else {
				return cb.equal(root.get(criteria.getKey()), criteria.getValue());
			}
		} else if (criteria.getOperation().equalsIgnoreCase(":>")) {
			// left fixed like
			if (root.get(criteria.getKey()).getJavaType() == String.class) {
				return cb.like(cb.lower(root.<String>get(criteria.getKey())),
						criteria.getValue().toString().toLowerCase() + "%");
			} else {
				return cb.equal(root.get(criteria.getKey()), criteria.getValue());
			}
		} else if (criteria.getOperation().equalsIgnoreCase("<:")) {
			// right fixed like
			if (root.get(criteria.getKey()).getJavaType() == String.class) {
				return cb.like(cb.lower(root.<String>get(criteria.getKey())),
						"%" + criteria.getValue().toString().toLowerCase());
			} else {
				return cb.equal(root.get(criteria.getKey()), criteria.getValue());
			}
		} else if (criteria.getOperation().equalsIgnoreCase("!")) {
			return cb.equal(root.get(criteria.getKey()), criteria.getValue());
		}
		return null;
	}
}