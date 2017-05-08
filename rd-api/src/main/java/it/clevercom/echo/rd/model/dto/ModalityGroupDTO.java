package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"created","updated","userupdate","active","idd"})
public class ModalityGroupDTO extends ModalityTypeDTO implements Serializable {
	private static final long serialVersionUID = 3406464325790754778L;
	
	private Set<ModalityDTO> modalities;

	/**
	 * 
	 */
	public ModalityGroupDTO() {
		super();
	}

	/**
	 * 
	 * @param modalities
	 */
	public ModalityGroupDTO(Set<ModalityDTO> modalities) {
		super();
		this.modalities = modalities;
	}

	/**
	 * 
	 * @param idModalityType
	 * @param type
	 * @param created
	 * @param updated
	 * @param userupdate
	 * @param active
	 * @param modalities
	 */
	public ModalityGroupDTO(Long idModalityType, String type, Date created, Date updated, String userupdate, boolean active, Set<ModalityDTO> modalities) {
		super(idModalityType, type, created, updated, userupdate, active);
		this.modalities = modalities;
	}
	
	/**
	 * 
	 * @param idModalityType
	 * @param type
	 * @param modalities
	 */
	public ModalityGroupDTO(Long idModalityType, String type, Set<ModalityDTO> modalities) {
		super();
		super.setIdModalityType(idModalityType);
		super.setType(type);
		this.modalities = modalities;
	}

	/**
	 * @return the modalities
	 */
	public Set<ModalityDTO> getModalities() {
		return modalities;
	}

	/**
	 * @param modalities the modalities to set
	 */
	public void setModalities(Set<ModalityDTO> modalities) {
		this.modalities = modalities;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
