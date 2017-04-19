package it.clevercom.echo.rd.component;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import it.clevercom.echo.common.exception.model.ValidationException;
import it.clevercom.echo.common.model.dto.response.ValidationExceptionDTO;
import it.clevercom.echo.common.util.StringUtils;
import it.clevercom.echo.rd.controller.Order_rd_Controller;
import it.clevercom.echo.rd.enums.OrganizationUnitTypeEnum;
import it.clevercom.echo.rd.enums.WorkPriorityEnum;
import it.clevercom.echo.rd.enums.WorkStatusEnum;
import it.clevercom.echo.rd.model.dto.BaseObjectDTO;
import it.clevercom.echo.rd.model.dto.OrderDTO;
import it.clevercom.echo.rd.model.dto.OrderedServiceDTO;
import it.clevercom.echo.rd.model.entity.Order;
import it.clevercom.echo.rd.model.entity.OrderService;
import it.clevercom.echo.rd.model.entity.OrganizationUnit;
import it.clevercom.echo.rd.model.entity.Service;
import it.clevercom.echo.rd.repository.IOrderLog_rd_Repository;
import it.clevercom.echo.rd.repository.IOrderService_rd_Repository;
import it.clevercom.echo.rd.repository.IOrder_rd_Repository;
import it.clevercom.echo.rd.repository.IOrganizationUnit_rd_Repository;
import it.clevercom.echo.rd.repository.IService_rd_Repository;
import it.clevercom.echo.rd.repository.IWorkPriority_rd_Repository;
import it.clevercom.echo.rd.repository.IWorkStatus_rd_Repository;

@Component
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

public class OrderValidator {
	@Autowired
	private Environment env;
	
	@Autowired
	private IOrder_rd_Repository repo;
	
	@Autowired
	private IOrderService_rd_Repository repo_os;
	
	@Autowired
	private IOrderLog_rd_Repository repo_ol;
	
	@Autowired
	private IOrganizationUnit_rd_Repository repo_ou;
	
	@Autowired
	private IWorkStatus_rd_Repository repo_ws;

	@Autowired
	private IWorkPriority_rd_Repository repo_wp;
	
	@Autowired
	private IService_rd_Repository repo_s;

	// used to bind it in exception message
	private static String entity_name = Order_rd_Controller.entity_name;
	private static String entity_id = Order_rd_Controller.entity_id;
	private static String entity_cd1 = Order_rd_Controller.entity_cd1;
	private static String entity_s_name = Order_rd_Controller.entity_s_name;
	private static String entity_os_name = Order_rd_Controller.entity_os_name;
	private static String entity_o_name = Order_rd_Controller.entity_o_name;
	
	/*-----------------------------*/
	/* business validation methods */
	/*-----------------------------*/

	/**
	 * Validate a delete order request
	 * @author luca
	 * @category business request validation
	 * @param entity
	 * @param rejectReason
	 * @param cancelReason
	 * @since 1.2.0
	 */
	public void validateDeleteRequest(Order entity, String rejectReason, String cancelReason) throws ValidationException {
		ValidationExceptionDTO exceptions = new ValidationExceptionDTO();
		
		// check that only a reason has been provided by the client		
		if (!((StringUtils.isNotNullNotEmptyNotWhiteSpaceOnly(rejectReason)) ^ (StringUtils.isNotNullNotEmptyNotWhiteSpaceOnly(cancelReason)))) {
			if ((StringUtils.isNullEmptyWhiteSpaceOnly(rejectReason)) && (StringUtils.isNullEmptyWhiteSpaceOnly(cancelReason))) {
				// 0:0
				exceptions.addFieldError(env.getProperty("echo.api.crud.fields.cancelreason") + "or" + env.getProperty("echo.api.crud.fields.rejectreason"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.mustprovideatleastonefield"), 
								env.getProperty("echo.api.crud.fields.cancelreason"), 
								env.getProperty("echo.api.crud.fields.rejectreason")));
			} else if ((StringUtils.isNotNullNotEmptyNotWhiteSpaceOnly(rejectReason)) && (StringUtils.isNotNullNotEmptyNotWhiteSpaceOnly(cancelReason))) {
				// 1:1
				exceptions.addFieldError(env.getProperty("echo.api.crud.fields.cancelreason") + "or" + env.getProperty("echo.api.crud.fields.rejectreason"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.mustprovideatmaximumonefield"), 
								env.getProperty("echo.api.crud.fields.cancelreason"), 
								env.getProperty("echo.api.crud.fields.rejectreason")));
			}
		}
		
		if (exceptions.getFieldErrors().size() > 0) {
			throw new ValidationException(env.getProperty("echo.api.crud.validation.genericmessage"), exceptions);
		} else {
			exceptions = null;
		}
	}
	
	/**
	 * Validate a create order request
	 * @author luca
	 * @category business request validation
	 * @param order passed to the create method
	 * @since 1.2.0
	 * @throws ValidationException
	 */
	public void validateCreateRequest(OrderDTO order) throws ValidationException {
		// these fields must not be inserted
		// in the create request and must be empty or null
		ValidationExceptionDTO exceptions = new ValidationExceptionDTO();

		// idOrder must not be present here
		if (order.getIdOrder() != null) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.idorder"),
					env.getProperty("echo.api.crud.validation.mustbeempty"));
		}
		
		// work session must not be present here
		if ((order.getWorkSession() != null)) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.worksession"),
					env.getProperty("echo.api.crud.validation.mustbeempty"));
		}
		
		// work status must be equal to requested code
		if ((!order.getWorkStatus().getCode().equals(WorkStatusEnum.REQUESTED.code()))) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.workstatus"),
					MessageFormat.format(env.getProperty("echo.api.crud.validation.mustbe"),
							env.getProperty("echo.api.crud.fields.workstatus"), WorkStatusEnum.REQUESTED.code()));
		}
		
		// work priority must not be null and must correspond to a valid enum code
		if ((order.getWorkPriority()==null) || (StringUtils.isNullEmptyWhiteSpaceOnly(order.getWorkPriority().getCode())) || (WorkPriorityEnum.getInstanceFromCodeValue(order.getWorkPriority().getCode()) == null)) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.workpriority"),
					MessageFormat.format(env.getProperty("echo.api.exception.search.params.wrongparam"),
							env.getProperty("echo.api.crud.fields.workpriority"), WorkPriorityEnum.enumValuesToString()));
		}
			
		// acquisition channel must contains a non empty string
		if (StringUtils.isNullEmptyWhiteSpaceOnly(order.getAcquisitionChannel())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.acquisitionchannel"),
					env.getProperty("echo.api.crud.validation.mustnotbeempty"));
		}

		// creation date must be == today
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(new Date(order.getCreationDate()));
		cal2.setTime(new Date());
		boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
		if(!sameDay) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.creationdate"), 
					MessageFormat.format(env.getProperty("echo.api.crud.validation.mustbetoday"), 
							env.getProperty("echo.api.crud.fields.creationdate"), 
							new Date().toString()));
		}
		
		// schedule date must not be present here
		if (order.getScheduledDate() != null) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.scheduledate"),
					env.getProperty("echo.api.crud.validation.mustbeempty"));
		}

		// acceptance date should not be present here
		if (order.getAcceptanceDate() != null) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.acceptancedate"),
					env.getProperty("echo.api.crud.validation.mustbeempty"));
		}

		// check that target organization unit is a valid operation unit
		if ((order.getTargetOrganizationUnit() == null) || (StringUtils.isNullEmptyWhiteSpaceOnly(order.getTargetOrganizationUnit().getId()))) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.targetorgunit"), 
					env.getProperty("echo.api.crud.validation.invalidelement"));
		} else {
			OrganizationUnit ou = repo_ou.findOne(Long.valueOf(order.getTargetOrganizationUnit().getId()));
		
			if (ou == null) {
				exceptions.addFieldError(env.getProperty("echo.api.crud.fields.targetorgunit"), 
						env.getProperty("echo.api.crud.validation.invalidelement"));
			} else if (!OrganizationUnitTypeEnum.getInstanceFromCodeValue(ou.getType()).equals(OrganizationUnitTypeEnum.OPERATION_UNIT)) {
				exceptions.addFieldError(env.getProperty("echo.api.crud.fields.targetorgunit"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.mustbeoftype"), 
								entity_o_name,
								ou.getIdorganizationunit(),
								OrganizationUnitTypeEnum.OPERATION_UNIT.toString()
								));
			}
		}
		
		// check that clinical question is not null
		if (StringUtils.isNullEmptyWhiteSpaceOnly(order.getClinicalQuestion())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.clinicalquestion"), 
					env.getProperty("echo.api.crud.validation.mustnotbeempty"));
		}
		
		// reject reason should not be present here
		if (order.getRejectReason() != null) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.rejectreason"),
					env.getProperty("echo.api.crud.validation.mustbeempty"));
		}
		
		// anamnesys must be not null here
		if (StringUtils.isNullEmptyWhiteSpaceOnly(order.getAnamnesys())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.anamnesys"),
					env.getProperty("echo.api.crud.validation.mustnotbeempty"));
		}
		
		// cancel reason must not be present here
		if (StringUtils.isNotNullNotEmptyNotWhiteSpaceOnly(order.getCancelReason())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.cancelreason"),
					env.getProperty("echo.api.crud.validation.mustbeempty"));
		}
		
		// identification document must not be present here
		if (StringUtils.isNotNullNotEmptyNotWhiteSpaceOnly(order.getIdentificationdocument())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.identificationdocument"), 
					env.getProperty("echo.api.crud.validation.mustbeempty"));
		}
		
		// check if some services has been selected
		if (order.getServices().size() == 0) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.service"),
					MessageFormat.format(env.getProperty("echo.api.crud.validation.emptylist"),
							env.getProperty("echo.api.crud.fields.service")));
		} else if (order.getServices().size() > 0) {
			// check that services belongs to same modality
			Long masterModalityType = 0l;
			String masterModalityName = null;
			int i = 0;
			for (OrderedServiceDTO element : order.getServices()) {
				Service current = repo_s.findOne(Long.valueOf(element.getId()));
				if (i==0) {
					masterModalityType = current.getModalityType().getIdmodalitytype();
					masterModalityName = current.getModalityType().getType();
				}
				if ((i>0) && (!masterModalityType.equals(current.getModalityType().getIdmodalitytype()))) {
					exceptions.addFieldError(env.getProperty("echo.api.crud.fields.orderedservice"), MessageFormat.format(env.getProperty("echo.api.crud.validation.differentkind"), 
							entity_os_name, 
							masterModalityName, 
							masterModalityType));
					break;
				}
				if (StringUtils.isNotNullNotEmptyNotWhiteSpaceOnly(element.getCancelReason())) {
					exceptions.addFieldError(env.getProperty("echo.api.crud.fields.cancelreason"), MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdate"), 
							entity_os_name, 
							env.getProperty("echo.api.crud.fields.cancelreason")));
					break;
				}
				if (StringUtils.isNullEmptyWhiteSpaceOnly(element.getAddedReason())) {
					exceptions.addFieldError(env.getProperty("echo.api.crud.fields.cancelreason"), MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdate.missingfield"), 
							entity_os_name,
							env.getProperty("echo.api.crud.fields.cancelreason")));
					break;
				}
				i++;
			}
		}

		// check if a patient has been selected
		if (!((order.getPatient() != null) && (order.getPatient().getIdPatient() != null))) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.patient"),
					env.getProperty("echo.api.crud.validation.mustnotbeempty"));
		}

		if (exceptions.getFieldErrors().size() > 0) {
			throw new ValidationException(env.getProperty("echo.api.crud.validation.genericmessage"), exceptions);
		} else {
			exceptions = null;
		}
	}

	/**
	 * Validate an update order request
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @throws ValidationException
	 */
	public void validateUpdateRequest(OrderDTO updatedOrder, Order orderToUpdate) throws ValidationException {
		// create exception dto
		ValidationExceptionDTO exceptions = new ValidationExceptionDTO();
		
		// --------------------------------------------------------
		// generic constraints valid for all update operation
		// all the following update are not permitted at this stage
		// --------------------------------------------------------
		
		// acquisition channel cannot never be updated
		if (!updatedOrder.getAcquisitionChannel().equals(orderToUpdate.getAcquisitionchannel())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.acquisitionchannel"), 
					MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdate"), 
							entity_name,
							env.getProperty("echo.api.crud.fields.acquisitionchannel")));
		}
		
		// creation date cannot never be updated
		if (!updatedOrder.getCreationDate().equals((Long)orderToUpdate.getCreationdate().getTime())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.creationdate"),
					MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdate"), 
							entity_name,
							env.getProperty("echo.api.crud.fields.creationdate")));
		}
		
		// requesting physician cannot never be updated
		if ((orderToUpdate.getRequestingphysician() != null) &&  (!updatedOrder.getRequestingPhysician().equals(orderToUpdate.getRequestingphysician()))) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.requestingphysician"),
					MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdate"), 
							entity_name,
							env.getProperty("echo.api.crud.fields.requestingphysician")));
		}
		
		// clinical question cannot never be updated
		if (!updatedOrder.getClinicalQuestion().equals(orderToUpdate.getClinicalquestion())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.clinicalquestion"),
					MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdate"), 
							entity_name,
							env.getProperty("echo.api.crud.fields.clinicalquestion")));
		}
		
		// anamnesys cannot never be updated
		if (!updatedOrder.getAnamnesys().equals(orderToUpdate.getAnamnesys())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.anamnesys"),
					MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdate"), 
							entity_name,
							env.getProperty("echo.api.crud.fields.anamnesys")));
		}
		
		// patient cannot never be updated
		if (!updatedOrder.getPatient().getIdPatient().equals(orderToUpdate.getPatient().getIdpatient())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.patient"),
					MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdate"), 
							entity_name,
							env.getProperty("echo.api.crud.fields.patient")));
		}
		
		// --------------------------
		// generic service validation
		// --------------------------
		
		// services must not be empty
		if (updatedOrder.getServices().size() == 0) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.service"),
					MessageFormat.format(env.getProperty("echo.api.crud.validation.emptylist"),
							env.getProperty("echo.api.crud.fields.service")));
		} else if (updatedOrder.getServices().size() > 0) {
			// TODO add fix to RD_005 (L'elenco degli esami può essere modificato fintanto che l'ordine non transiterà nello stato di esecuzione)
			
			// get old service modality type (check only the first element)
			Long oldModalityType = 0l;
			String oldModalityName = null;
			for (OrderService current : orderToUpdate.getOrderServices()) {
				oldModalityType = current.getService().getModalityType().getIdmodalitytype();
				oldModalityName = current.getService().getModalityType().getType();
				break;
			}
			
			// check that updated services belongs to same modality
			Long masterModalityType = 0l;
			String masterModalityName = null;
			int i = 0;
			for (BaseObjectDTO element : updatedOrder.getServices()) {
				Service current = repo_s.findOne(Long.valueOf(element.getId()));
				if (i==0) {
					masterModalityType = current.getModalityType().getIdmodalitytype();
					masterModalityName = current.getModalityType().getType();
				}
				if ((i>0) && (!masterModalityType.equals(current.getModalityType().getIdmodalitytype()))) {
					exceptions.addFieldError(env.getProperty("echo.api.crud.fields.service"), MessageFormat.format(env.getProperty("echo.api.crud.validation.differentkind"), entity_s_name, masterModalityName, masterModalityType));
					break;
				}
				i++;
			}
			
			if (!oldModalityType.equals(masterModalityType)) {
				exceptions.addFieldError(env.getProperty("echo.api.crud.fields.service"), MessageFormat.format(env.getProperty("echo.api.crud.validation.differentkind"), entity_s_name, oldModalityName, oldModalityType));
			}
		}
		
		// ------------------------
		// status switch validation
		// ------------------------
		
		// check order update ratio from source (orderToUpdate) to destination (updateOrder)
		switch (WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode())) {
			// validate a switch from REQUESTED status
			case REQUESTED: {
				exceptions.addFieldErrorList(this.validateFromRequestedStatus(updatedOrder, orderToUpdate));
				break;
			}
			// validate a switch from SCHEDULED status
			case SCHEDULED: {
				exceptions.addFieldErrorList(this.validateFromScheduledStatus(updatedOrder, orderToUpdate));
				break;
			}
			// validate a switch from ACCEPTED status
			case ACCEPTED: {
				exceptions.addFieldErrorList(this.validateFromAcceptedStatus(updatedOrder, orderToUpdate));
				break;
			}
			// validate a switch from EXECUTED status
			case EXECUTING: {
				exceptions.addFieldErrorList(this.validateFromExecutingStatus(updatedOrder, orderToUpdate));
				break;
			}
			// validate a switch from EXECUTED status
			case EXECUTED: {
				exceptions.addFieldErrorList(this.validateFromExecutedStatus(updatedOrder, orderToUpdate));
				break;
			}
			// validate a switch from REPORTED status
			case REPORTING: {
				exceptions.addFieldErrorList(this.validateFromReportingStatus(updatedOrder, orderToUpdate));
				break;
			}
			// validate a switch from REPORTED status
			case REPORTED: {
				exceptions.addFieldErrorList(this.validateFromReportedStatus(updatedOrder, orderToUpdate));
				break;
			}
			// validate a switch from SIGNED status
			case SIGNED: {
				exceptions.addFieldErrorList(this.validateFromSignedStatus(updatedOrder, orderToUpdate));
				break;
			}
			// validate a switch from DELIVERED status
			case DELIVERED: {
				exceptions.addFieldErrorList(this.validateFromDeliveredStatus(updatedOrder, orderToUpdate));
				break;
			}
			// validate a switch from ARCHIVED status
			case ARCHIVED: {
				exceptions.addFieldErrorList(this.validateFromArchivedStatus(updatedOrder, orderToUpdate));
				break;
			}
			// validate a switch from CANCELED status
			case CANCELED: {
				exceptions.addFieldErrorList(this.validateFromCanceledStatus(updatedOrder, orderToUpdate));
				break;
			}
			default: {
				break;
			}
		}
		
		// throws an exception if field error are > 0
		if (exceptions.getFieldErrors().size() > 0) {
			throw new ValidationException(env.getProperty("echo.api.crud.validation.genericmessage"), exceptions);
		} else {
			exceptions = null;
		}
	}

	/**
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @return
	 */
	private Map<String, String> validateFromReportingStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @return
	 */
	private Map<String, String> validateFromExecutingStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @return
	 */
	private Map<String, String> validateFromCanceledStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		Map<String, String> out = new HashMap<String, String>();
		// validate status switch 				
		if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.CANCELED) {
			out.put(env.getProperty("echo.api.crud.fields.workstatus"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdatestatus"), 
								entity_name,
								WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).name(),
								WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()).name())
				);		
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.CANCELED) {
			
		}
		return out;
	}

	/**
	 * 
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @return
	 */
	private Map<String, String> validateFromArchivedStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		Map<String, String> out = new HashMap<String, String>();
		// validate status switch 				
		if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.ARCHIVED) {
				out.put(env.getProperty("echo.api.crud.fields.workstatus"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdatestatus"), 
								entity_name,
								WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).name(),
								WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()).name())
				);
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.ARCHIVED) {

		}
		return out;
	}

	/**
	 * 
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @return
	 */
	private Map<String, String> validateFromDeliveredStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		Map<String, String> out = new HashMap<String, String>();
		// validate status switch 				
		if ((WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.DELIVERED) &&  
		    (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.ARCHIVED)) {
				out.put(env.getProperty("echo.api.crud.fields.workstatus"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdatestatus"), 
								entity_name,
								WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).name(),
								WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()).name())
				);
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.DELIVERED) {

		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.ARCHIVED) {

		}
		return out;
	}

	/**
	 * 
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @return
	 */
	private Map<String, String> validateFromSignedStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		Map<String, String> out = new HashMap<String, String>();
		// validate status switch 				
		if ((WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.SIGNED) &&  
		    (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.DELIVERED)) {
				out.put(env.getProperty("echo.api.crud.fields.workstatus"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdatestatus"), 
								entity_name,
								WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).name(),
								WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()).name())
				);
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.SIGNED) {

		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.DELIVERED) {

		}
		return out;
	}

	/**
	 * 
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @return
	 */
	private Map<String, String> validateFromValidatedStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		Map<String, String> out = new HashMap<String, String>();
		// validate status switch 				
		if (((WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.SIGNED))) {
				out.put(env.getProperty("echo.api.crud.fields.workstatus"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdatestatus"), 
								entity_name,
								WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).name(),
								WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()).name())
				);
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.SIGNED) {

		}
		return out;
	}

	/**
	 * 
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @return
	 */
	private Map<String, String> validateFromReportedStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		Map<String, String> out = new HashMap<String, String>();
		// validate status switch 				
		if ((WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.REPORTED)) {
				out.put(env.getProperty("echo.api.crud.fields.workstatus"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdatestatus"), 
								entity_name,
								WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).name(),
								WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()).name())
				);
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.REPORTED) {

		}
		
		return out;
	}

	/**
	 * 
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @return
	 */
	private Map<String, String> validateFromExecutedStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		Map<String, String> out = new HashMap<String, String>();
		// validate status switch 				
		if ((WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.EXECUTED) &&  
		    (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.REPORTED)) {
				out.put(env.getProperty("echo.api.crud.fields.workstatus"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdatestatus"), 
								entity_name,
								WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).name(),
								WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()).name())
				);
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.EXECUTED) {

		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.REPORTED) {

		}
		return out;
	}

	/**
	 * 
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @return
	 */
	private Map<String, String> validateFromAcceptedStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		Map<String, String> out = new HashMap<String, String>();
		// validate status switch 				
		if ((WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.ACCEPTED) &&  
		    (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.EXECUTED) &&
		    (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.CANCELED)) {
				out.put(env.getProperty("echo.api.crud.fields.workstatus"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdatestatus"), 
								entity_name,
								WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).name(),
								WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()).name())
				);
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.ACCEPTED) {

		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.EXECUTED) {

		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.CANCELED) {

		}
		return out;
	}

	/**
	 * 
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @return
	 */
	private Map<String, String> validateFromScheduledStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		Map<String, String> out = new HashMap<String, String>();
		// validate status switch 				
		if ((WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.SCHEDULED) &&  
		    (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.ACCEPTED) &&
		    (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.CANCELED)) {
				out.put(env.getProperty("echo.api.crud.fields.workstatus"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdatestatus"), 
								entity_name,
								WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).name(),
								WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()).name())
				);
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.SCHEDULED) {

		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.ACCEPTED) {

		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.CANCELED) {

		}
		return out;
	}

	/**
	 * 
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @return
	 */
	private Map<String, String> validateFromRequestedStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		Map<String, String> out = new HashMap<String, String>();
		// work session must be null
		if (updatedOrder.getWorkSession() != null) {
			out.put(env.getProperty("echo.api.crud.fields.worksession"),
					MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdate"), 
							entity_name,
							env.getProperty("echo.api.crud.fields.worksession")));
		}
		
		// validate status switch
		if ((WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.REQUESTED) &&  
		    (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.SCHEDULED) &&
		    (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.CANCELED)) {
				out.put(env.getProperty("echo.api.crud.fields.workstatus"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdatestatus"), 
								entity_name,
								WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).name(),
								WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()).name())
				);
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.REQUESTED) {
			
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.SCHEDULED) {

		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.CANCELED) {

		}
		return out;
	}
}