package it.clevercom.echo.tm.model.converter;

import org.dozer.CustomConverter;
import org.dozer.MappingException;

import it.clevercom.echo.tm.model.dto.gateway.WeightEventDTO;
import it.clevercom.echo.tm.model.entity.gateway.GatewayEventWeight;

public class GatewayEventWeight2WeightEventDTO implements CustomConverter {

	@Override
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		
		if (sourceFieldValue instanceof GatewayEventWeight) {
			WeightEventDTO target = null; 
			GatewayEventWeight source = (GatewayEventWeight) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new WeightEventDTO();
			} else {
				target = (WeightEventDTO) destinationFieldValue;
			}
			
			target.setBmi((source.getBmi()==null) ? null : source.getBmi());
			target.setHeight(source.getHeight());
			target.setIdweight((source.getIdweight()==null) ? null : source.getIdweight());
			target.setUom((source.getUom()==null) ? null : source.getUom());
			target.setWeight(source.getWeight());
			
			if (source.getGatewayEvent()!=null) {
				//target.setGatewaySerialNumber(gatewaySerialNumber);
				//target.setId(id);
				//target.setPheripheral(pheripheral);
				//target.setReceivedDate(receivedDate);
				//target.setTimeStamp(timeStamp);
				target.setId((source.getGatewayEvent().getIdevent()));
				target.setPheripheral(source.getGatewayEvent().getPheripheralmac());
				target.setReceivedDate(source.getGatewayEvent().getReceiveddate().getTime());
				target.setTimeStamp(source.getGatewayEvent().getTimestamp().getTime());
				target.setGatewaySerialNumber(source.getGatewayEvent().getGatewayserialnumber());
			}
			
			return target;
		} else if (sourceFieldValue instanceof WeightEventDTO) {
			GatewayEventWeight target = null; 
			WeightEventDTO source = (WeightEventDTO) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new GatewayEventWeight();
			} else {
				target = (GatewayEventWeight) destinationFieldValue;
			}
			
			target.setActive(source.getActive());
			target.setBmi((source.getBmi()==null) ? null : source.getBmi());
			target.setCreated(source.getCreated());
			//target.setGatewayEvent(gatewayEvent);
			target.setHeight((source.getHeight()==null) ? null : source.getHeight());
			target.setIdweight((source.getIdweight()==null) ? null : source.getIdweight());
			target.setUom((source.getUom()==null) ? null : source.getUom());
			target.setUpdated(source.getUpdated());
			target.setUserupdate(source.getUserupdate());
			target.setWeight((source.getWeight()==null) ? null : source.getWeight());			
			
			return target;
		} else {
			throw new MappingException("Converter GatewayEventBloodPressure2BloodPressureEventDTO " + "used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
		}
	}
}
