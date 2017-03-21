package it.clevercom.echo.rd.model.converter;

import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;

import it.clevercom.echo.rd.model.dto.BaseObjectDTO;
import it.clevercom.echo.rd.model.entity.Municipality;
@Deprecated
public class Municipality2BaseObjectDTO implements CustomConverter {
	@Autowired
	private Mapper rdDozerMapper;

	@Override
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,	Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		
		if (sourceFieldValue instanceof Municipality) {
			BaseObjectDTO target = null; 
			Municipality source = (Municipality) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new BaseObjectDTO();
			} else {
				target = (BaseObjectDTO) destinationFieldValue;
			}
			
			target.setId(source.getIdmunicipality().toString());
			target.setName(source.getMunicipalityname());
			target.setCode(source.getMunicipalitystdcode());
			
			return target;
		} else if (sourceFieldValue instanceof BaseObjectDTO) {
			Municipality target = null; 
			BaseObjectDTO source = (BaseObjectDTO) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new Municipality();
			} else {
				target = (Municipality) destinationFieldValue;
			}
			
			target.setIdmunicipality(Long.valueOf(source.getId()));
			target.setMunicipalityname(source.getName());
			target.setMunicipalitystdcode(source.getCode());
			
			return target;
		} else {
			throw new MappingException("Converter Municipality2BaseObjectDTO " + "used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
		}
	}
}
