package it.clevercom.echo.rd.jpa.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import it.clevercom.echo.rd.model.entity.ModalityType;

public class ModalityTypeSpecification<T,S> implements Specification<T> {
	private String fieldName = "modalityType";
	private Long fieldValue = 0l;
	private String subEntity;
	
	/**
	 * @param fieldName
	 * @param fieldValue
	 */
	public ModalityTypeSpecification(String subEntity, String fieldName, Long fieldValue) {
		super();
		if (fieldName != null) {
			this.fieldName = fieldName;
		}
		this.fieldValue = fieldValue;
		this.subEntity = subEntity;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (subEntity==null) {
			return cb.equal(root.<ModalityType>get(fieldName).<Long>get("idmodalitytype"), fieldValue);
		} else {
			return cb.equal(root.<S>get(subEntity).<ModalityType>get(fieldName).<Long>get("idmodalitytype"), fieldValue);
		}
	}

}
