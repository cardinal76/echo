package it.clevercom.echo.rd.jpa.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import it.clevercom.echo.rd.model.entity.WorkStatus;

public class WorkStatusSpecification<T> implements Specification<T> {
	private long statusId;
	
	/**
	 * @param statusId
	 */
	public WorkStatusSpecification(long statusId) {
		super();
		this.statusId = statusId;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		return cb.equal(root.<WorkStatus>get("workStatus").<Long>get("idworkstatus"), statusId);
	}

}
