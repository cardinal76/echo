package it.clevercom.echo.tm.model.converter;

import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;

import it.clevercom.echo.tm.model.dto.gateway.BloodPressureEventDTO;
import it.clevercom.echo.tm.model.entity.gateway.GatewayEvent;
import it.clevercom.echo.tm.model.entity.gateway.GatewayEventBloodPressure;

public class GatewayEventBloodPressure2BloodPressureEventDTO implements CustomConverter {
	@Override
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		
		if (sourceFieldValue instanceof GatewayEventBloodPressure) {
			BloodPressureEventDTO target = null; 
			GatewayEventBloodPressure source = (GatewayEventBloodPressure) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new BloodPressureEventDTO();
			} else {
				target = (BloodPressureEventDTO) destinationFieldValue;
			}
			
			target.setArterialPressure((source.getArterialpressure()==null) ? null : source.getArterialpressure());
			target.setDiastolicPressure((source.getDiastolicpressure()==null) ? null : source.getDiastolicpressure());
			target.setIdbloodpressure((source.getIdbloodpressure()==null) ? null : source.getIdbloodpressure());
			target.setMeasureStatus((source.getMeasurestatus()==null) ? null : source.getMeasurestatus());
			target.setPulseRate((source.getPulserate()==null) ? null : source.getPulserate());
			target.setSystolicPressure((source.getSystolicpressure()==null) ? null : source.getSystolicpressure());
			target.setUom((source.getUom()==null) ? null : source.getUom());
			
			if (source.getGatewayEvent()!=null) {
				target.setId((source.getGatewayEvent().getIdevent()));
				target.setPheripheral(source.getGatewayEvent().getPheripheralmac());
				target.setReceivedDate(source.getGatewayEvent().getReceiveddate().getTime());
				target.setTimeStamp(source.getGatewayEvent().getTimestamp().getTime());
				target.setGatewaySerialNumber(source.getGatewayEvent().getGatewayserialnumber());
			}
			
			return target;
		} else if (sourceFieldValue instanceof BloodPressureEventDTO) {
			GatewayEventBloodPressure target = null; 
			BloodPressureEventDTO source = (BloodPressureEventDTO) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new GatewayEventBloodPressure();
			} else {
				target = (GatewayEventBloodPressure) destinationFieldValue;
			}
			
			target.setIdbloodpressure((source.getIdbloodpressure()==null) ? null : source.getIdbloodpressure());
			target.setActive(source.getActive());
			target.setArterialpressure((source.getArterialPressure()==null) ? null : source.getArterialPressure());
			target.setCreated(source.getCreated());
			target.setDiastolicpressure((source.getDiastolicPressure()==null) ? null : source.getDiastolicPressure());
			target.setIdbloodpressure((source.getIdbloodpressure()==null) ? null : source.getIdbloodpressure());
			target.setMeasurestatus((source.getMeasureStatus()==null) ? null : source.getMeasureStatus());
			target.setPulserate((source.getPulseRate()==null) ? null : source.getPulseRate());
			target.setSystolicpressure((source.getSystolicPressure()==null) ? null : source.getSystolicPressure());
			target.setUom((source.getUom()==null) ? null : source.getUom());
			target.setUpdated(source.getUpdated());
			target.setUserupdate(source.getUserupdate());
			
			return target;
		} else {
			throw new MappingException("Converter GatewayEventBloodPressure2BloodPressureEventDTO " + "used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
		}
	}

}
