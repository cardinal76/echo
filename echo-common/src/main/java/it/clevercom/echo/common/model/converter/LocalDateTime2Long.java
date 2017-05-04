package it.clevercom.echo.common.model.converter;

import java.time.LocalDateTime;

import org.dozer.CustomConverter;
import org.dozer.MappingException;

import it.clevercom.echo.common.util.DateUtil;

public class LocalDateTime2Long implements CustomConverter {
	@Override
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,	Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		
		if (sourceFieldValue instanceof Long) {
			LocalDateTime target = null; 
			Long source = (Long) sourceFieldValue;
			target = DateUtil.longToLocalDateTime(source);
			return target;
		} else if (sourceFieldValue instanceof LocalDateTime) {
			Long target = null; 
			LocalDateTime source = (LocalDateTime) sourceFieldValue;	
			target = DateUtil.localDateTimeToLong(source);
			return target;
		} else {
			throw new MappingException("Converter LocalDateTime2Long " + "used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
		}
	}
}
