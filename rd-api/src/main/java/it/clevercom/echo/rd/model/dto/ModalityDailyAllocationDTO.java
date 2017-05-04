package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.clevercom.echo.common.dto.AbstractEchoDTO;

@JsonIgnoreProperties({"created","updated","userupdate","active","idd"})
public class ModalityDailyAllocationDTO extends AbstractEchoDTO implements Serializable {
	private static final long serialVersionUID = -2971414166225920555L;
	
	private Long idModalityDailyAllocation;
    private ModalityDTO modality;
    private Long day;
    private Integer serviceAllocation;
    private Integer serviceExcess;
    private Integer patientAllocation;
    private Integer patientExcess;
	
	@Override
	public Object getIdd() {
		return getIdModalityDailyAllocation();
	}

	/**
	 * @return the idModalityDailyAllocation
	 */
	public Long getIdModalityDailyAllocation() {
		return idModalityDailyAllocation;
	}

	/**
	 * @param idModalityDailyAllocation the idModalityDailyAllocation to set
	 */
	public void setIdModalityDailyAllocation(Long idModalityDailyAllocation) {
		this.idModalityDailyAllocation = idModalityDailyAllocation;
	}

	/**
	 * @return the modality
	 */
	public ModalityDTO getModality() {
		return modality;
	}

	/**
	 * @param modality the modality to set
	 */
	public void setModality(ModalityDTO modality) {
		this.modality = modality;
	}

	/**
	 * @return the day
	 */
	public Long getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(Long day) {
		this.day = day;
	}

	/**
	 * @return the serviceAllocation
	 */
	public Integer getServiceAllocation() {
		return serviceAllocation;
	}

	/**
	 * @param serviceAllocation the serviceAllocation to set
	 */
	public void setServiceAllocation(Integer serviceAllocation) {
		this.serviceAllocation = serviceAllocation;
	}

	/**
	 * @return the serviceExcess
	 */
	public Integer getServiceExcess() {
		return serviceExcess;
	}

	/**
	 * @param serviceExcess the serviceExcess to set
	 */
	public void setServiceExcess(Integer serviceExcess) {
		this.serviceExcess = serviceExcess;
	}

	/**
	 * @return the patientAllocation
	 */
	public Integer getPatientAllocation() {
		return patientAllocation;
	}

	/**
	 * @param patientAllocation the patientAllocation to set
	 */
	public void setPatientAllocation(Integer patientAllocation) {
		this.patientAllocation = patientAllocation;
	}

	/**
	 * @return the patientExcess
	 */
	public Integer getPatientExcess() {
		return patientExcess;
	}

	/**
	 * @param patientExcess the patientExcess to set
	 */
	public void setPatientExcess(Integer patientExcess) {
		this.patientExcess = patientExcess;
	}
}
