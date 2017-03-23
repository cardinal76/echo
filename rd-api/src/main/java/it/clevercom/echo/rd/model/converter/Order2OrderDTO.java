package it.clevercom.echo.rd.model.converter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;

import it.clevercom.echo.rd.model.dto.BaseObjectDTO;
import it.clevercom.echo.rd.model.dto.OrderDTO;
import it.clevercom.echo.rd.model.dto.OrderLogDTO;
import it.clevercom.echo.rd.model.dto.WorkSessionDTO;
import it.clevercom.echo.rd.model.entity.Order;
import it.clevercom.echo.rd.model.entity.OrderLog;
import it.clevercom.echo.rd.model.entity.OrderService;
import it.clevercom.echo.rd.model.entity.OrganizationUnit;
import it.clevercom.echo.rd.model.entity.Service;
import it.clevercom.echo.rd.model.entity.WorkPriority;
import it.clevercom.echo.rd.model.entity.WorkSession;
import it.clevercom.echo.rd.model.entity.WorkStatus;

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
			
			// origin org unit
			if (source.getOrganizationUnitByOriginorganizationunitid() != null) {
				target.setOriginOrganizationUnit(rdDozerMapper.map(source.getOrganizationUnitByOriginorganizationunitid(), BaseObjectDTO.class));
			}
			
			// target org unit
			if (source.getOrganizationUnitByTargetorganizationunitid() != null) {
				target.setTargetOrganizationUnit(rdDozerMapper.map(source.getOrganizationUnitByTargetorganizationunitid(), BaseObjectDTO.class));
			}
			
			// workpriority
			if (source.getWorkPriority() != null)  {
				target.setWorkPriority(rdDozerMapper.map(source.getWorkPriority(), BaseObjectDTO.class));
			}
			
			// worksession
			if (source.getWorkSession() != null) {
				target.setWorkSession(rdDozerMapper.map(source.getWorkSession(), WorkSessionDTO.class));
			}

			// workstatus
			if (source.getWorkStatus() != null) {
				target.setWorkStatus(rdDozerMapper.map(source.getWorkStatus(), BaseObjectDTO.class));
			}
			
			// iterate
			Set<OrderService> orderServices = source.getOrderServices();			
			if (!(orderServices.isEmpty())) {
				target.setServices(new HashSet<BaseObjectDTO>());
				for (OrderService orderService : orderServices) {
					target.getServices().add(rdDozerMapper.map(orderService.getService(), BaseObjectDTO.class));
				}
			}
						
			// iterate
			Set<OrderLog> orderLogs = source.getOrderLogs();
			if ((!(orderLogs.isEmpty()))) {
				target.setOrderLogs(new HashSet<OrderLogDTO>());
				for (OrderLog orderLog : orderLogs) {
					target.getOrderLogs().add(rdDozerMapper.map(orderLog, OrderLogDTO.class));
				}
			}
			
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
			
			target.setAcceptancedate((source.getAcceptancedate() != null) ? new Date(source.getAcceptancedate()) : null);
			target.setAcquisitionchannel((source.getAcquisitionchannel() != null) ? source.getAcquisitionchannel() : null);
			target.setClinicalhistory((source.getClinicalhistory() != null) ? source.getClinicalhistory() : null);
			target.setCreationdate((source.getCreationdate() != null) ? new Date(source.getCreationdate()) : null);
			target.setDuration((source.getDuration() != null) ? source.getDuration() : null);
			target.setIdorder((source.getIdorder() != null) ? source.getIdorder() : null);
			target.setNotes((source.getNotes() != null) ? source.getNotes() : null);			
			target.setOrderreason((source.getOrderreason() != null) ? source.getOrderreason() : null);
			target.setRejectreason((source.getRejectreason() != null) ? source.getRejectreason() : null);
			target.setRequestingphysician(source.getRequestingphysician() != null ? source.getRequestingphysician() : null);
			target.setScheduleddate((source.getScheduleddate() != null) ? new Date(source.getScheduleddate()) : null);
			
			// map related complex object
			
			// origin org unit
			if (source.getOriginOrganizationUnit() != null) {
				target.setOrganizationUnitByOriginorganizationunitid(rdDozerMapper.map(source.getOriginOrganizationUnit(), OrganizationUnit.class));
			}
			
			// target org unit
			if (source.getTargetOrganizationUnit() != null) {
				target.setOrganizationUnitByTargetorganizationunitid(rdDozerMapper.map(source.getTargetOrganizationUnit(), OrganizationUnit.class));
			}
			
			// workpriority
			if (source.getWorkPriority() != null)  {
				target.setWorkPriority(rdDozerMapper.map(source.getWorkPriority(), WorkPriority.class));
			}
			
			// worksession
			if (source.getWorkSession() != null) {
				target.setWorkSession(rdDozerMapper.map(source.getWorkSession(), WorkSession.class));
			}

			// workstatus
			if (source.getWorkStatus() != null) {
				target.setWorkStatus(rdDozerMapper.map(source.getWorkStatus(), WorkStatus.class));
			}
			
			// iterate
			Set<BaseObjectDTO> services = source.getServices();			
			if (!(services.isEmpty())) {
				target.setOrderServices(new HashSet<OrderService>());
				for (BaseObjectDTO srv : services) {
					Service service = rdDozerMapper.map(srv, Service.class);
					OrderService orderService = new OrderService();
					orderService.setService(service);
					orderService.setOrder(target);
					target.getOrderServices().add(orderService);
				}
			}
						
			// iterate
			Set<OrderLogDTO> orderLogs = source.getOrderLogs();
			if ((!(orderLogs.isEmpty()))) {
				target.setOrderLogs(new HashSet<OrderLog>());
				for (OrderLogDTO orderLog : orderLogs) {
					target.getOrderLogs().add(rdDozerMapper.map(orderLog, OrderLog.class));
				}
			}
			
			// inject tech fields
			target.setActive(source.getActive());
			target.setCreated(source.getCreated());
			target.setUpdated(source.getUpdated());
			target.setUserupdate(source.getUserupdate());
			
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
