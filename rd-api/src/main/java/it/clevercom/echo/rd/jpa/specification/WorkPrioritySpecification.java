package it.clevercom.echo.rd.jpa.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import it.clevercom.echo.rd.model.entity.WorkPriority;

public class WorkPrioritySpecification<T> implements Specification<T> {
	private long priorityId;
	
	/**
	 * @param priorityId
	 */
	public WorkPrioritySpecification(long priorityId) {
		super();
		this.priorityId = priorityId;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		return cb.equal(root.<WorkPriority>get("workPriority").<Long>get("idworkpriority"), priorityId);
	}
}
