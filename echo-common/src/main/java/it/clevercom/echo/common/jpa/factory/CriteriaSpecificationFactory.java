package it.clevercom.echo.common.jpa.factory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.jpa.domain.Specification;

import it.clevercom.echo.common.jpa.helper.SearchCriteria;
import it.clevercom.echo.common.jpa.helper.SpecificationQueryHelper;
import it.clevercom.echo.common.jpa.helper.SpecificationsBuilder;

public class CriteriaSpecificationFactory {

	public static <T> Specification<T> getCriteriaSpecification(String criteria) {
		Specification<T> spec = null;
		
		// create table predicate if criteria param is not null		
		if (!criteria.equals("null")) {
			SpecificationsBuilder<T, SpecificationQueryHelper<T>> builder = new SpecificationsBuilder<T, SpecificationQueryHelper<T>>();
			Pattern pattern = Pattern.compile(SearchCriteria.pattern);
			Matcher matcher = pattern.matcher(criteria + ",");
			while (matcher.find()) {
				builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
			}
			spec = builder.build();
		}
		
		return spec;
	}
}
