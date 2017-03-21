package it.clevercom.echo.rd.model.converter;

import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;

import it.clevercom.echo.rd.model.dto.BaseObjectDTO;
import it.clevercom.echo.rd.model.entity.Maritalstatus;
@Deprecated
public class MaritalStatus2BaseObjectDTO implements CustomConverter {
	@Autowired
	private Mapper rdDozerMapper;

	@Override
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,	Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		
		if (sourceFieldValue instanceof Maritalstatus) {
			BaseObjectDTO target = null; 
			Maritalstatus source = (Maritalstatus) sourceFieldValue;
			
			// check to see if the object alreatargetdy exists
			if (destinationFieldValue == null) {
				target = new BaseObjectDTO();
			} else {
				target = (BaseObjectDTO) destinationFieldValue;
			}
			
			target.setId(source.getIdmaritalstatus().toString());
			target.setName(source.getDescription());
			target.setCode(source.getHl7code());
			
			return target;
		} else if (sourceFieldValue instanceof BaseObjectDTO) {
			Maritalstatus target = null; 
			BaseObjectDTO source = (BaseObjectDTO) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new Maritalstatus();
			} else {
				target = (Maritalstatus) destinationFieldValue;
			}
			
			target.setIdmaritalstatus(Long.valueOf(source.getId()));
			target.setDescription(source.getName());
			target.setHl7code(source.getCode());
			
			return target;
		} else {
			throw new MappingException("Converter MaritalStatus2BaseObjectDTO" + "used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
		}
	}
}
