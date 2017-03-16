package it.clevercom.echo.rd.model.converter;

import org.dozer.CustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;

import it.clevercom.echo.rd.model.dto.MunicipalityDTO;
import it.clevercom.echo.rd.model.entity.Municipality;

public class Municipality2MunicipalityDTO_PlainConverter implements CustomConverter {
	@Autowired
	private Mapper rdDozerMapper;

	@Override
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,	Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		
		if (sourceFieldValue instanceof Municipality) {
			MunicipalityDTO target = null; 
			Municipality source = (Municipality) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new MunicipalityDTO();
			} else {
				target = (MunicipalityDTO) destinationFieldValue;
			}
			
			target.setIdmunicipality(source.getIdmunicipality());
			target.setMunicipalityname(source.getMunicipalityname());
			target.setMunicipalitystdcode(source.getMunicipalitystdcode());
			target.setPostalcode(source.getPostalcode());
			
			return target;
		} else if (sourceFieldValue instanceof MunicipalityDTO) {
			Municipality target = null; 
			MunicipalityDTO source = (MunicipalityDTO) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new Municipality();
			} else {
				target = (Municipality) destinationFieldValue;
			}
			
			target.setIdmunicipality(source.getIdmunicipality());
			target.setMunicipalityname(source.getMunicipalityname());
			target.setMunicipalitystdcode(source.getMunicipalitystdcode());
			target.setPostalcode(source.getPostalcode());
			
			return target;
		} else {
			throw new MappingException("Converter Municipality2MunicipalityDTO_PlainConverter " + "used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
		}
	}
}
