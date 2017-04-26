package it.clevercom.echo.rd.jpa.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import it.clevercom.echo.rd.model.entity.ModalityType;

public class ModalityTypeSpecification<T> implements Specification<T> {
	private String fieldName = "modalityType";
	private Long fieldValue = 0l;
	
	/**
	 * @param fieldName
	 * @param fieldValue
	 */
	public ModalityTypeSpecification(String fieldName, Long fieldValue) {
		super();
		if (fieldName != null) {
			this.fieldName = fieldName;
		}
		this.fieldValue = fieldValue;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		return cb.equal(root.<ModalityType>get(fieldName).<Long>get("idmodalitytype"), fieldValue);
	}

}
