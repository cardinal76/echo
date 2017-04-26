package it.clevercom.echo.common.jpa.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class StringSpecification<T,S> implements Specification<T>{
	String fieldName;
	String fieldValue;
	String subEntity;
	
	/**
	 * 
	 * @param subEntity
	 * @param fieldName
	 * @param fieldValue
	 */
	public StringSpecification(String subEntity, String fieldName, String fieldValue) {
		super();
		this.subEntity = subEntity;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate p = null;
		if (subEntity==null) {
			p = cb.equal(cb.lower(root.<String>get(fieldName)), fieldValue.toLowerCase());
		} else {
			p = cb.equal(cb.lower(root.<S>get(subEntity).<String>get(fieldName)), fieldValue.toLowerCase());
		}
		return p;
	}

}
