package it.clevercom.echo.common.jpa.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

// S = entity
// T = entity specification query helper

public class SpecificationsBuilder<S, T extends SpecificationQueryHelper<S>> {
	private final List<SearchCriteria> params;
    
	public SpecificationsBuilder() {
		params = new ArrayList<SearchCriteria>();
	}

	public SpecificationsBuilder<S, T> with(String key, String operation, Object value) {
		params.add(new SearchCriteria(key, operation, value));
		return this;
	}

	public Specification<S> build() {
		if (params.size() == 0) {
			return null;
		}
 
		List<Specification<S>> specs = new ArrayList<Specification<S>>();
		for (SearchCriteria param : params) {
			specs.add(new SpecificationQueryHelper<S>(param));
		}
 
		Specification<S> result = specs.get(0);
		
		for (int i = 1; i < specs.size(); i++) {
			result = Specifications.where(result).and(specs.get(i));
		}
		return result;
	}
}