package it.clevercom.echo.rd.jpa.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import it.clevercom.echo.rd.model.entity.Modality;

public class ModalitySpecification<T,S> implements Specification<T> {
	private String fieldName = "modality";
	private Long fieldValue = 0l;
	private String subEntity;
	
	/**
	 * @param fieldName
	 * @param fieldValue
	 */
	public ModalitySpecification(String subEntity, String fieldName, Long fieldValue) {
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
			return cb.equal(root.<Modality>get(fieldName).<Long>get("idmodality"), fieldValue);
		} else {
			return cb.equal(root.<S>get(subEntity).<Modality>get(fieldName).<Long>get("idmodality"), fieldValue);
		}
	}

}
