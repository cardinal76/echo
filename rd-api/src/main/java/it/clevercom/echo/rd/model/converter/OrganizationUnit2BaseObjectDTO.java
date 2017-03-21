package it.clevercom.echo.rd.model.converter;

import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;

import it.clevercom.echo.rd.model.dto.BaseObjectDTO;
import it.clevercom.echo.rd.model.entity.OrganizationUnit;
@Deprecated
public class OrganizationUnit2BaseObjectDTO implements CustomConverter {
	@Autowired
	private Mapper rdDozerMapper;

	@Override
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,	Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		
		if (sourceFieldValue instanceof OrganizationUnit) {
			BaseObjectDTO target = null; 
			OrganizationUnit source = (OrganizationUnit) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new BaseObjectDTO();
			} else {
				target = (BaseObjectDTO) destinationFieldValue;
			}
			
			target.setId(source.getIdorganizationunit().toString());
			target.setCode(source.getCode());
			target.setName(source.getName());
			
			return target;
		} else if (sourceFieldValue instanceof BaseObjectDTO) {
			OrganizationUnit target = null; 
			BaseObjectDTO source = (BaseObjectDTO) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new OrganizationUnit();
			} else {
				target = (OrganizationUnit) destinationFieldValue;
			}
			
			target.setIdorganizationunit(Long.valueOf(source.getId()));
			target.setCode(source.getCode());
			target.setName(source.getName());
			
			return target;
		} else {
			throw new MappingException("Converter OrganizationUnit2BaseObjectDTO " + "used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
		}
	}
}
