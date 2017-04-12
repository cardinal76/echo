package it.clevercom.echo.common.model.jpa.helper;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class DateIntervalSpecification<T> implements Specification<T>{
	private Date t1;
	private Date t2;
	private String fieldname;
	
	public DateIntervalSpecification(Date t1, Date t2, String fieldname) {
		super();
		this.t1 = t1;
		this.t2 = t2;
		this.fieldname = fieldname;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {		
		Predicate p1 = cb.greaterThanOrEqualTo(root.<Date>get(fieldname), t1);
		Predicate p2 = cb.lessThanOrEqualTo(root.<Date>get(fieldname), t2);
		return cb.and(p1, p2);
	}

}
