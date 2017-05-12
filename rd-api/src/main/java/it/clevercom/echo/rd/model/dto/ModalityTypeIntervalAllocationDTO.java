package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.List;

import it.clevercom.echo.common.dto.AbstractEchoDTO;
import it.clevercom.echo.rd.model.entity.ModalityDailyAllocation;

public class ModalityTypeIntervalAllocationDTO extends AbstractEchoDTO implements Serializable {
	
	private static final long serialVersionUID = 5880101803484534823L;
	
	private Long from;
	private Long to;
	private BaseObjectDTO modalityType;
	private Integer serviceallocation;
    private Integer serviceexcess;
    private Integer patientallocation;
    private Integer patientexcess;
	
	private List<ModalityDailyAllocation> allocations;
	
	@Override
	public Object getIdd() {
		return from.toString() + "-" + to.toString();
	}

	/**
	 * @return the from
	 */
	public Long getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(Long from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public Long getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(Long to) {
		this.to = to;
	}

	/**
	 * @return the modalityType
	 */
	public BaseObjectDTO getModalityType() {
		return modalityType;
	}

	/**
	 * @param modalityType the modalityType to set
	 */
	public void setModalityType(BaseObjectDTO modalityType) {
		this.modalityType = modalityType;
	}

	/**
	 * @return the serviceallocation
	 */
	public Integer getServiceallocation() {
		return serviceallocation;
	}

	/**
	 * @param serviceallocation the serviceallocation to set
	 */
	public void setServiceallocation(Integer serviceallocation) {
		this.serviceallocation = serviceallocation;
	}

	/**
	 * @return the serviceexcess
	 */
	public Integer getServiceexcess() {
		return serviceexcess;
	}

	/**
	 * @param serviceexcess the serviceexcess to set
	 */
	public void setServiceexcess(Integer serviceexcess) {
		this.serviceexcess = serviceexcess;
	}

	/**
	 * @return the patientallocation
	 */
	public Integer getPatientallocation() {
		return patientallocation;
	}

	/**
	 * @param patientallocation the patientallocation to set
	 */
	public void setPatientallocation(Integer patientallocation) {
		this.patientallocation = patientallocation;
	}

	/**
	 * @return the patientexcess
	 */
	public Integer getPatientexcess() {
		return patientexcess;
	}

	/**
	 * @param patientexcess the patientexcess to set
	 */
	public void setPatientexcess(Integer patientexcess) {
		this.patientexcess = patientexcess;
	}

	/**
	 * @return the allocations
	 */
	public List<ModalityDailyAllocation> getAllocations() {
		return allocations;
	}

	/**
	 * @param allocations the allocations to set
	 */
	public void setAllocations(List<ModalityDailyAllocation> allocations) {
		this.allocations = allocations;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
