package it.clevercom.echo.rd.jpa.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import it.clevercom.echo.rd.model.entity.User;

public class UserSpecification<T> implements Specification<T> {
	private String fieldName = "user";
	private String fieldValue = "SYSTEM";
	
	public UserSpecification(String fieldName, String fieldValue) {
		super();
		if (fieldName!=null) {
			this.fieldName = fieldName;
		}
		this.fieldValue = fieldValue;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		return cb.equal(root.<User>get(fieldName).<Long>get("username"), fieldValue);
	}

}