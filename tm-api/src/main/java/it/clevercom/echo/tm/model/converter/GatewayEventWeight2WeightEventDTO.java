package it.clevercom.echo.tm.model.converter;

import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;

public class GatewayEventWeight2WeightEventDTO implements CustomConverter, MapperAware {
	@Autowired
	private Mapper tmDozerMapper;
	
	@Override
	public void setMapper(Mapper mapper) {
		// TODO Auto-generated method stub
		this.tmDozerMapper = mapper;
	}

	@Override
	public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
		// TODO Auto-generated method stub
		return null;
	}

}
