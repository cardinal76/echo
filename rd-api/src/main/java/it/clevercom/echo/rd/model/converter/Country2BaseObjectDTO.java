package it.clevercom.echo.rd.model.converter;

import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;

import it.clevercom.echo.rd.model.dto.BaseObjectDTO;
import it.clevercom.echo.rd.model.entity.Country;
@Deprecated
public class Country2BaseObjectDTO implements CustomConverter {
	@Autowired
	private Mapper rdDozerMapper;

	@Override
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,	Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		
		if (sourceFieldValue instanceof Country) {
			BaseObjectDTO target = null; 
			Country source = (Country) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new BaseObjectDTO();
			} else {
				target = (BaseObjectDTO) destinationFieldValue;
			}
			
			target.setId(source.getIdcountry().toString());
			target.setName(source.getCountrynicename());
			target.setCode(source.getCountryisonumcode().toString());
			
			return target;
		} else if (sourceFieldValue instanceof BaseObjectDTO) {
			Country target = null; 
			BaseObjectDTO source = (BaseObjectDTO) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new Country();
			} else {
				target = (Country) destinationFieldValue;
			}
			
			target.setIdcountry(Long.valueOf(source.getId()));
			target.setCountrynicename(source.getName());
			target.setCountryisonumcode(Long.valueOf(source.getCode()));
			
			return target;
		} else {
			throw new MappingException("Converter Country2BaseObjectDTO" + "used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
		}
	}
}
