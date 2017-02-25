package it.clevercom.echo.rd.model.jpa.helper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import it.clevercom.echo.rd.model.entity.Service;

public class ServiceSpecificationQueryHelper implements Specification<Service> {
    private SearchCriteria criteria;
    
	public ServiceSpecificationQueryHelper(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<Service> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (criteria.getOperation().equalsIgnoreCase(">")) {
            return cb.greaterThanOrEqualTo(
              root.<String> get(criteria.getKey()), criteria.getValue().toString());
        } 
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return cb.lessThanOrEqualTo(
              root.<String> get(criteria.getKey()), criteria.getValue().toString());
        } 
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return cb.like(cb.lower(root.<String> get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%");
            } else {
                return cb.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
	}
}