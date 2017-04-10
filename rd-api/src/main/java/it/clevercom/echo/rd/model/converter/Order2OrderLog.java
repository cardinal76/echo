package it.clevercom.echo.rd.model.converter;

import java.util.Date;

import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;

import it.clevercom.echo.rd.model.dto.OrderDTO;
import it.clevercom.echo.rd.model.entity.Order;
import it.clevercom.echo.rd.model.entity.OrderLog;

public class Order2OrderLog implements CustomConverter, MapperAware {
	@Autowired
	private Mapper rdDozerMapper;

	@Override
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,	Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		
		if (sourceFieldValue instanceof Order) {
			OrderLog target = null; 
			Order source = (Order) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new OrderLog();
			} else {
				target = (OrderLog) destinationFieldValue;
			}
			
			target.setAcceptancedate((source.getAcceptancedate() != null) ? source.getAcceptancedate() : null);
			target.setAcquisitionchannel((source.getAcquisitionchannel() != null) ? source.getAcquisitionchannel() : null);
			target.setActive(true);
			target.setClinicalhistory((source.getAnamnesys() != null) ? source.getAnamnesys() : null);
			target.setCreated(new Date());
			target.setCreationdate((source.getCreationdate() != null) ? source.getCreationdate() : null);
			target.setDuration((source.getDuration() != null) ? source.getDuration() : null);
			target.setIdorderlog(null);
			target.setIdworksession(((source.getWorkSession() != null) && (source.getWorkSession().getIdworksession() != null)) ? source.getWorkSession().getIdworksession() : null);
			target.setIdworkstatus(((source.getWorkStatus() != null) && (source.getWorkStatus().getIdworkstatus() != null)) ? source.getWorkStatus().getIdworkstatus() : null);
			target.setNotes((source.getNotes() != null) ? source.getNotes() : null);
			target.setOrder(source);
			target.setOrderreason((source.getClinicalquestion() != null) ? source.getClinicalquestion() : null);
			target.setOriginorganizationunitid(((source.getOrganizationUnitByOriginorganizationunitid() != null) && (source.getOrganizationUnitByOriginorganizationunitid().getIdorganizationunit() != null)) ? source.getOrganizationUnitByOriginorganizationunitid().getIdorganizationunit() : null);
			target.setIdworkpriority(((source.getWorkPriority() != null) && (source.getWorkPriority().getIdworkpriority() != null)) ? source.getWorkPriority().getIdworkpriority() : null);
			target.setRejectreason((source.getRejectreason() != null) ? source.getRejectreason() : null);
			target.setRequestingphysician((source.getRequestingphysician() != null) ? source.getRequestingphysician() : null);
			target.setScheduleddate((source.getScheduleddate() != null) ? source.getScheduleddate() : null);
			target.setTargetorganizationunitid(((source.getOrganizationUnitByTargetorganizationunitid() != null) && (source.getOrganizationUnitByTargetorganizationunitid().getIdorganizationunit() != null)) ? source.getOrganizationUnitByTargetorganizationunitid().getIdorganizationunit() : null);
			target.setUpdated(new Date());
			target.setUserupdate("SYSTEM");
			
			// return adjusted OrderDTO
			return target;
		} else if (sourceFieldValue instanceof OrderDTO) {
			throw new MappingException("Converter Order2OrderLog used incorrectly. Only one-way mapping from Order to OrderLog is possible");
		} else {
			throw new MappingException("Converter Order2OrderLog " + "used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
		}
	}

	@Override
	public void setMapper(Mapper mapper) {
		// TODO Auto-generated method stub
		rdDozerMapper = mapper;
	}
}
