package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.clevercom.echo.common.dto.AbstractEchoDTO;

@JsonIgnoreProperties({"created","updated","userupdate","active","idd"})
public class ModalityDTO extends AbstractEchoDTO implements Serializable {

	private Long idModality;
    private BaseObjectDTO modalityType;
    private BaseObjectDTO organizationUnit;
    private String name;
    private String description;
    private Integer dailyServiceCapacity;
    private Integer dailyPatientCapacity;
    //private Set<ModalityService> modalityServices = new HashSet<ModalityService>(0);
    //private Set<ModalityDailyAllocation> modalityDailyAllocations = new HashSet<ModalityDailyAllocation>(0);
    // private Set<WorkTask> workTasks = new HashSet<WorkTask>(0);
	
	@Override
	public Object getIdd() {
		return getIdModality();
	}

	/**
	 * @return the idModality
	 */
	public Long getIdModality() {
		return idModality;
	}

	/**
	 * @param idModality the idModality to set
	 */
	public void setIdModality(Long idModality) {
		this.idModality = idModality;
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
	 * @return the organizationUnit
	 */
	public BaseObjectDTO getOrganizationUnit() {
		return organizationUnit;
	}

	/**
	 * @param organizationUnit the organizationUnit to set
	 */
	public void setOrganizationUnit(BaseObjectDTO organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the dailyServiceCapacity
	 */
	public Integer getDailyServiceCapacity() {
		return dailyServiceCapacity;
	}

	/**
	 * @param dailyServiceCapacity the dailyServiceCapacity to set
	 */
	public void setDailyServiceCapacity(Integer dailyServiceCapacity) {
		this.dailyServiceCapacity = dailyServiceCapacity;
	}

	/**
	 * @return the dailyPatientCapacity
	 */
	public Integer getDailyPatientCapacity() {
		return dailyPatientCapacity;
	}

	/**
	 * @param dailyPatientCapacity the dailyPatientCapacity to set
	 */
	public void setDailyPatientCapacity(Integer dailyPatientCapacity) {
		this.dailyPatientCapacity = dailyPatientCapacity;
	}
}
