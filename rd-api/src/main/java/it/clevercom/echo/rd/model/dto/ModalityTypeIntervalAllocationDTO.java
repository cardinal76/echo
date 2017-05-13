package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Map;

import it.clevercom.echo.common.dto.AbstractEchoDTO;
import it.clevercom.echo.rd.model.entity.ModalityDailyAllocation;

public class ModalityTypeIntervalAllocationDTO extends AbstractEchoDTO implements Serializable {
	
	private static final long serialVersionUID = 5880101803484534823L;
	
	// selected interval
	private Long from;
	private Long to;
	
	// master modality type
	private BaseObjectDTO modalityType;
	
	// general allocation for modality type (key => day, value => allocation
	private Map<Long, ModalityTypeDailyAllocationDTO> modalityTypeAllocation;
	
	// allocation details for each modality (key => idModality, value => {key => day, value => allocation})
	private Map<Long, Map<Long, ModalityDailyAllocation>> modalityAllocation;
	
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
	 * @return the modalityTypeAllocation
	 */
	public Map<Long, ModalityTypeDailyAllocationDTO> getModalityTypeAllocation() {
		return modalityTypeAllocation;
	}

	/**
	 * @param modalityTypeAllocation the modalityTypeAllocation to set
	 */
	public void setModalityTypeAllocation(Map<Long, ModalityTypeDailyAllocationDTO> modalityTypeAllocation) {
		this.modalityTypeAllocation = modalityTypeAllocation;
	}

	/**
	 * @return the modalityAllocation
	 */
	public Map<Long, Map<Long, ModalityDailyAllocation>> getModalityAllocation() {
		return modalityAllocation;
	}

	/**
	 * @param modalityAllocation the modalityAllocation to set
	 */
	public void setModalityAllocation(Map<Long, Map<Long, ModalityDailyAllocation>> modalityAllocation) {
		this.modalityAllocation = modalityAllocation;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
