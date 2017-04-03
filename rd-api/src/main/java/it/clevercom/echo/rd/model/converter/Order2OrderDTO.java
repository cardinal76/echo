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
import it.clevercom.echo.rd.model.dto.PatientSmartDTO;
import it.clevercom.echo.rd.model.dto.WorkSessionDTO;
import it.clevercom.echo.rd.model.entity.Order;
import it.clevercom.echo.rd.model.entity.OrderLog;
import it.clevercom.echo.rd.model.entity.OrderService;
import it.clevercom.echo.rd.model.entity.OrganizationUnit;
import it.clevercom.echo.rd.model.entity.Patient;
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
			
			target.setAcceptanceDate((source.getAcceptancedate() != null) ? source.getAcceptancedate().getTime() : null);
			target.setAcquisitionChannel((source.getAcquisitionchannel() != null) ? source.getAcquisitionchannel() : null);
			target.setClinicalHistory((source.getClinicalhistory() != null) ? source.getClinicalhistory() : null);
			target.setCreationDate((source.getCreationdate() != null) ? source.getCreationdate().getTime() : null);
			target.setDuration((source.getDuration() != null) ? source.getDuration() : null);
			target.setIdOrder((source.getIdorder() != null) ? source.getIdorder() : null);
			target.setNotes((source.getNotes() != null) ? source.getNotes() : null);			
			target.setOrderReason((source.getOrderreason() != null) ? source.getOrderreason() : null);
			target.setRejectReason((source.getRejectreason() != null) ? source.getRejectreason() : null);
			target.setRequestingPhysician(source.getRequestingphysician() != null ? source.getRequestingphysician() : null);
			target.setScheduledDate((source.getScheduleddate() != null) ? source.getScheduleddate().getTime() : null);
			
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
			
			// patient
			if (source.getPatient() != null) {
				target.setPatient(rdDozerMapper.map(source.getPatient(), PatientSmartDTO.class).buildExtendedObject());
			}
			
			// iterate
			Set<OrderService> orderServices = source.getOrderServices();		
			if (!(orderServices.isEmpty())) {
				target.setServices(new HashSet<BaseObjectDTO>());
				target.setCanceledServices(new HashSet<BaseObjectDTO>());
				for (OrderService orderService : orderServices) {
					if (orderService.getActive().equals(Boolean.TRUE)) {
						target.getServices().add(rdDozerMapper.map(orderService.getService(), BaseObjectDTO.class));
					} else {
						target.getCanceledServices().add(rdDozerMapper.map(orderService.getService(), BaseObjectDTO.class));
					}
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
			target.setUserUpdate(source.getUserupdate());
			
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
			
			target.setAcceptancedate((source.getAcceptanceDate() != null) ? new Date(source.getAcceptanceDate()) : null);
			target.setAcquisitionchannel((source.getAcquisitionChannel() != null) ? source.getAcquisitionChannel() : null);
			target.setClinicalhistory((source.getClinicalHistory() != null) ? source.getClinicalHistory() : null);
			target.setCreationdate((source.getCreationDate() != null) ? new Date(source.getCreationDate()) : null);
			target.setDuration((source.getDuration() != null) ? source.getDuration() : null);
			target.setIdorder((source.getIdOrder() != null) ? source.getIdOrder() : null);
			target.setNotes((source.getNotes() != null) ? source.getNotes() : null);			
			target.setOrderreason((source.getOrderReason() != null) ? source.getOrderReason() : null);
			target.setRejectreason((source.getRejectReason() != null) ? source.getRejectReason() : null);
			target.setRequestingphysician(source.getRequestingPhysician() != null ? source.getRequestingPhysician() : null);
			target.setScheduleddate((source.getScheduledDate() != null) ? new Date(source.getScheduledDate()) : null);
			
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
			
			// patient
			if (source.getPatient() != null) {
				target.setPatient(rdDozerMapper.map(source.getPatient(), Patient.class));
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
			target.setUserupdate(source.getUserUpdate());
			
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
