package it.clevercom.echo.common.jpa.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class BooleanSpecification<T,S> implements Specification<T> {
	String fieldName;
	Boolean fieldValue;
	String subEntity;
	
	/**
	 * 
	 * @param subEntity
	 * @param fieldName
	 * @param fieldValue
	 */
	public BooleanSpecification(String subEntity, String fieldName, Boolean fieldValue) {
		super();
		this.subEntity = subEntity;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate p = null;
		if (subEntity==null) {
			if (fieldValue.booleanValue() == true) {
				p = cb.isTrue(root.<Boolean>get(fieldName));
			} else {
				p = cb.isFalse(root.<Boolean>get(fieldName));
			}
		} else {
			if (fieldValue.booleanValue() == true) {
				p = cb.isTrue(root.<S>get(subEntity).<Boolean>get(fieldName));
			} else {
				p = cb.isFalse(root.<S>get(subEntity).<Boolean>get(fieldName));
			}
		}
		return p;
	}
}
