package it.clevercom.echo.rd.model.converter;

import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;

import it.clevercom.echo.rd.model.dto.BaseObjectDTO;
import it.clevercom.echo.rd.model.dto.OrderDTO;
import it.clevercom.echo.rd.model.dto.OrderLogDTO;
import it.clevercom.echo.rd.model.entity.Order;

public class Order2OrderDTO implements CustomConverter, MapperAware {
	@Autowired
	private Mapper rdDozerMapper;

	@Override
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,	Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		
		if (sourceFieldValue instanceof Order) {
			OrderDTO target = null; 
			Order source = (Order) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new OrderDTO();
			} else {
				target = (OrderDTO) destinationFieldValue;
			}
			
			target.setAcceptancedate((source.getAcceptancedate() != null) ? source.getAcceptancedate().getTime() : null);
			target.setAcquisitionchannel((source.getAcquisitionchannel() != null) ? source.getAcquisitionchannel() : null);
			target.setClinicalhistory((source.getClinicalhistory() != null) ? source.getClinicalhistory() : null);
			target.setCreationdate((source.getCreationdate() != null) ? source.getCreationdate().getTime() : null);
			target.setDuration((source.getDuration() != null) ? source.getDuration() : null);
			target.setIdorder((source.getIdorder() != null) ? source.getIdorder() : null);
			target.setNotes((source.getNotes() != null) ? source.getNotes() : null);			
			target.setOrderreason((source.getOrderreason() != null) ? source.getOrderreason() : null);			
			
			
			target.setRejectreason((source.getRejectreason() != null) ? source.getRejectreason() : null);
			target.setRequestingphysician(source.getRequestingphysician() != null ? source.getRequestingphysician() : null);
			target.setScheduleddate((source.getScheduleddate() != null) ? source.getScheduleddate().getTime() : null);
			
			// map related complex object

//			// iterate
//			target.setOrderLogs(rdDozerMapper.map(source.getOrderLogs(), OrderLogDTO.class));
//			
//			// origin org unit
//			if (source.getOrganizationUnitByOriginorganizationunitid() != null) {
//				target.setOriginOrganizationUnit(rdDozerMapper.map(source.getOrganizationUnitByOriginorganizationunitid(), BaseObjectDTO.class));
//			}
//			
//			// target org unit
//			if (source.getOrganizationUnitByTargetorganizationunitid() != null) {
//				target.setTargetOrganizationUnit(rdDozerMapper.map(source.getOrganizationUnitByTargetorganizationunitid(), BaseObjectDTO.class));
//			}
//			
//			// iterate
//			target.setServices(services);
//			
//			target.setWorkPriority(workPriority);
//			
//			if (source.getWorkSession() != null) {
//				target.setWorkSession(rdDozerMapper.map(source.getWorkSession(), WorkSessionDTO.class));
//			}
//			
//			if (source.getWorkStatus() != null) {
//				target.setWorkStatus(rdDozerMapper.map(source.getWorkStatus(), BaseObjectDTO.class));
//			}
			
			
			// inject tech fields
			target.setActive(source.getActive());
			target.setCreated(source.getCreated());
			target.setUpdated(source.getUpdated());
			target.setUserupdate(source.getUserupdate());
			
			// return adjusted OrderDTO
			return target;
		} else if (sourceFieldValue instanceof OrderDTO) {
			Order target = null; 
			OrderDTO source = (OrderDTO) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new Order();
			} else {
				target = (Order) destinationFieldValue;
			}
			
			
			return target;
		} else {
			throw new MappingException("Converter Order2OrderDTO " + "used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
		}
	}

	@Override
	public void setMapper(Mapper mapper) {
		rdDozerMapper = mapper;
	}
}
