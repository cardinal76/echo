package it.clevercom.echo.rd.model.converter;

import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;

import it.clevercom.echo.rd.model.dto.BaseObjectDTO;
import it.clevercom.echo.rd.model.entity.Citizenship;
@Deprecated
public class Citizenship2BaseObjectDTO implements CustomConverter {
	@Autowired
	private Mapper rdDozerMapper;

	@Override
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,	Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		
		if (sourceFieldValue instanceof Citizenship) {
			BaseObjectDTO target = null; 
			Citizenship source = (Citizenship) sourceFieldValue;
			
			// c@Deprecatedheck to see if the object already exists
			if (destinationFieldValue == null) {
				target = new BaseObjectDTO();
			} else {
				target = (BaseObjectDTO) destinationFieldValue;
			}
			
			target.setId(source.getIdcitizenship().toString());
			target.setName(source.getDescription());
			target.setCode(null);
			
			return target;
		} else if (sourceFieldValue instanceof BaseObjectDTO) {
			Citizenship target = null; 
			BaseObjectDTO source = (BaseObjectDTO) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new Citizenship();
			} else {
				target = (Citizenship) destinationFieldValue;
			}
			
			target.setIdcitizenship(Long.valueOf(source.getId()));
			target.setDescription(source.getName());
			
			return target;
		} else {
			throw new MappingException("Converter Country2BaseObjectDTO" + "used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
		}
	}
}
