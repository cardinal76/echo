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
import it.clevercom.echo.rd.model.dto.OrderedServiceDTO;
import it.clevercom.echo.rd.model.dto.PatientSmartDTO;
import it.clevercom.echo.rd.model.dto.WorkSessionDTO;
import it.clevercom.echo.rd.model.entity.Order;
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
			target.setAnamnesys((source.getAnamnesys() != null) ? source.getAnamnesys() : null);
			target.setCreationDate((source.getCreationdate() != null) ? source.getCreationdate().getTime() : null);
			target.setDuration((source.getDuration() != null) ? source.getDuration() : null);
			target.setIdOrder((source.getIdorder() != null) ? source.getIdorder() : null);
			target.setNotes((source.getNotes() != null) ? source.getNotes() : null);			
			target.setClinicalQuestion((source.getClinicalquestion() != null) ? source.getClinicalquestion() : null);
			target.setRejectReason((source.getRejectreason() != null) ? source.getRejectreason() : null);
			target.setRequestingPhysician(source.getRequestingphysician() != null ? source.getRequestingphysician() : null);
			target.setScheduledDate((source.getScheduleddate() != null) ? source.getScheduleddate().getTime() : null);
			target.setCancelReason((source.getCancelreason() != null) ? source.getCancelreason() : null);
			target.setIdentificationDocument((source.getIdentificationdocument() != null) ? source.getIdentificationdocument() : null);
			
			target.setExecutingDate((source.getExecutingdate() != null) ? source.getExecutingdate().getTime() : null);
			target.setExecutedDate((source.getExecuteddate() != null) ? source.getExecuteddate().getTime() : null);
			target.setReportingDate((source.getReportingdate() != null) ? source.getReportingdate().getTime() : null);
			target.setReportedDate((source.getReporteddate() != null) ? source.getReporteddate().getTime() : null);
			target.setSignedDate((source.getSigneddate() != null) ? source.getSigneddate().getTime() : null);
			target.setDeliveredDate((source.getDelivereddate() != null) ? source.getDelivereddate().getTime() : null);
			target.setArchivedDate((source.getArchiveddate() != null) ? source.getArchiveddate().getTime() : null);
			target.setCanceledDate((source.getCanceleddate() != null) ? source.getCanceleddate().getTime() : null);
			
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
				target.setServices(new HashSet<OrderedServiceDTO>());
				target.setCanceledServices(new HashSet<OrderedServiceDTO>());
				
				int i = 0;
				for (OrderService orderService : orderServices) {
					if (i==0)
						target.setMasterModalityType(rdDozerMapper.map(orderService.getService().getModalityType(), BaseObjectDTO.class));
					
					if (orderService.getActive().equals(Boolean.TRUE)) {
						BaseObjectDTO dto_s = rdDozerMapper.map(orderService.getService(), BaseObjectDTO.class);
						OrderedServiceDTO dto = rdDozerMapper.map(dto_s, OrderedServiceDTO.class);
						dto.setAddedReason(orderService.getAddedreason() != null ? orderService.getAddedreason() : null);
						dto.setCancelReason(orderService.getCanceledreason() != null ? orderService.getCanceledreason() : null);
						
						target.getServices().add(dto);
					} else {
						BaseObjectDTO dto_s = rdDozerMapper.map(orderService.getService(), BaseObjectDTO.class);
						OrderedServiceDTO dto = rdDozerMapper.map(dto_s, OrderedServiceDTO.class);
						dto.setAddedReason(orderService.getAddedreason() != null ? orderService.getAddedreason() : null);
						dto.setCancelReason(orderService.getCanceledreason() != null ? orderService.getCanceledreason() : null);
						
						target.getCanceledServices().add(dto);
					}
					i++;
				}
			}
			
			// inject tech fields
			target.setActive((source.getActive()!=null) ? source.getActive() : target.getActive());
			target.setCreated((source.getCreated()!=null) ? source.getCreated() : target.getCreated());
			target.setUpdated((source.getUpdated()!=null) ? source.getUpdated() : target.getUpdated());
			target.setUserUpdate((source.getUserupdate()!=null) ? source.getUserupdate() : target.getUserUpdate());
			
			
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
			
			target.setAcceptancedate((source.getAcceptanceDate() != null) ? new Date(source.getAcceptanceDate()) : target.getAcceptancedate());
			target.setAcquisitionchannel((source.getAcquisitionChannel() != null) ? source.getAcquisitionChannel() : target.getAcquisitionchannel());
			target.setAnamnesys((source.getAnamnesys() != null) ? source.getAnamnesys() : target.getAnamnesys());
			target.setCreationdate((source.getCreationDate() != null) ? new Date(source.getCreationDate()) : target.getCreationdate());
			target.setDuration((source.getDuration() != null) ? source.getDuration() : target.getDuration());
			target.setIdorder((source.getIdOrder() != null) ? source.getIdOrder() : target.getIdorder());
			target.setNotes((source.getNotes() != null) ? source.getNotes() : target.getNotes());			
			target.setClinicalquestion((source.getClinicalQuestion() != null) ? source.getClinicalQuestion() : target.getClinicalquestion());
			target.setRejectreason((source.getRejectReason() != null) ? source.getRejectReason() : target.getRejectreason());
			target.setRequestingphysician(source.getRequestingPhysician() != null ? source.getRequestingPhysician() : target.getRequestingphysician());
			target.setScheduleddate((source.getScheduledDate() != null) ? new Date(source.getScheduledDate()) : target.getScheduleddate());
			target.setCancelreason((source.getCancelReason() != null) ? source.getCancelReason() : target.getCancelreason());
			target.setIdentificationdocument((source.getIdentificationDocument() != null) ? source.getIdentificationDocument() : target.getIdentificationdocument());
			
			target.setExecutingdate((source.getExecutingDate() != null) ? new Date(source.getExecutingDate()) : target.getExecutingdate());
			target.setExecuteddate((source.getExecutedDate() != null) ? new Date(source.getExecutedDate()) : target.getExecuteddate());
			target.setReportingdate((source.getReportingDate() != null) ? new Date(source.getReportingDate()) : target.getReportingdate());
			target.setReporteddate((source.getReportedDate() != null) ? new Date(source.getReportedDate()) : target.getReporteddate());
			target.setSigneddate((source.getSignedDate() != null) ? new Date(source.getSignedDate()) : target.getSigneddate());
			target.setDelivereddate((source.getDeliveredDate() != null) ? new Date(source.getDeliveredDate()) : target.getDelivereddate());
			target.setArchiveddate((source.getArchivedDate() != null) ? new Date(source.getArchivedDate()) : target.getArchiveddate());
			target.setCanceleddate((source.getCanceledDate() != null) ? new Date(source.getCanceledDate()) : target.getCanceleddate());
		    
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
			Set<OrderedServiceDTO> services = source.getServices();			
			if ((!(services.isEmpty())) && ((target.getOrderServices()==null) || (target.getOrderServices().size()==0))) {
				target.setOrderServices(new HashSet<OrderService>());
				for (OrderedServiceDTO srv : services) {
					Service service = rdDozerMapper.map(srv, Service.class);
					OrderService orderService = new OrderService();
					orderService.setService(service);
					orderService.setOrder(target);
					orderService.setAddedreason((srv.getAddedReason() != null) ? srv.getAddedReason() : null);
					orderService.setCanceledreason((srv.getCancelReason() != null) ? srv.getCancelReason() : null);
					target.getOrderServices().add(orderService);
				}
			}
			
			// inject tech fields
			target.setActive((source.getActive()!=null) ? source.getActive() : target.getActive());
			target.setCreated((source.getCreated()!=null) ? source.getCreated() : target.getCreated());
			target.setUpdated((source.getUpdated()!=null) ? source.getUpdated() : target.getUpdated());
			target.setUserupdate((source.getUserUpdate()!=null) ? source.getUserUpdate() : target.getUserupdate());
			
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
