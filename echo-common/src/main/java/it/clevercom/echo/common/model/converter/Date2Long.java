package it.clevercom.echo.common.model.converter;

import java.sql.Date;

import org.dozer.CustomConverter;
import org.dozer.MappingException;

public class Date2Long implements CustomConverter {
	@Override
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,	Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		
		if (sourceFieldValue instanceof Long) {
			Date target = null; 
			Long source = (Long) sourceFieldValue;
			target = new Date(source);			
			return target;
		} else if (sourceFieldValue instanceof Date) {
			Long target = null; 
			Date source = (Date) sourceFieldValue;
			target = source.getTime();
			
			return target;
		} else {
			throw new MappingException("Converter Date2Long " + "used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
		}
	}
}
