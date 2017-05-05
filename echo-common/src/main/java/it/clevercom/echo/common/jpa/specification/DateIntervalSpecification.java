package it.clevercom.echo.common.jpa.specification;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class DateIntervalSpecification<T,S> implements Specification<T>{
	private Date t1;
	private Date t2;
	private String fieldname;
	private String subEntity;
	
	public DateIntervalSpecification(String subEntity, Date t1, Date t2, String fieldname) {
		super();
		this.t1 = t1;
		this.t2 = t2;
		this.fieldname = fieldname;
		this.subEntity = subEntity;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (subEntity==null) {
			Predicate p1 = cb.greaterThanOrEqualTo(root.<Date>get(fieldname), t1);
			Predicate p2 = cb.lessThanOrEqualTo(root.<Date>get(fieldname), t2);
			return cb.and(p1, p2);
		} else {
			Predicate p1 = cb.greaterThanOrEqualTo(root.<S>get(subEntity).<Date>get(fieldname), t1);
			Predicate p2 = cb.lessThanOrEqualTo(root.<S>get(subEntity).<Date>get(fieldname), t2);
			return cb.and(p1, p2);
		}
	}

}
