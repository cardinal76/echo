package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"created","updated","userupdate","active"})
public class PatientCodingActorDTO implements Serializable {
	private static final long serialVersionUID = 8406499008795319267L;

	private PatientDTO patient;
	private BaseObjectDTO codingActor;
	
	public PatientCodingActorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PatientCodingActorDTO(PatientDTO patient, BaseObjectDTO codingActor) {
		super();
		this.patient = patient;
		this.codingActor = codingActor;
	}

	/**
	 * @return the patient
	 */
	public PatientDTO getPatient() {
		return patient;
	}
	
	/**
	 * @param patient the patient to set
	 */
	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}
	
	/**
	 * @return the codingActor
	 */
	public BaseObjectDTO getCodingActor() {
		return codingActor;
	}
	
	/**
	 * @param codingActor the codingActor to set
	 */
	public void setCodingActor(BaseObjectDTO codingActor) {
		this.codingActor = codingActor;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
