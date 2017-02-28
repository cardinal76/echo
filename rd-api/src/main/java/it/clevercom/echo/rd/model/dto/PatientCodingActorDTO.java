package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"created","updated","userupdate","active"})
public class PatientCodingActorDTO implements Serializable {
	private static final long serialVersionUID = 1027267777217369117L;
	
	private PatientCodingActorIdDTO id;
	private CodingActorDTO codingActor;
	private PatientDTO patient;

	public PatientCodingActorDTO() {
	}

	public PatientCodingActorDTO(PatientCodingActorIdDTO id) {
		this.id = id;
	}

	public PatientCodingActorDTO(PatientCodingActorIdDTO id, CodingActorDTO codingActor, PatientDTO patient) {
		this.id = id;
		this.codingActor = codingActor;
		this.patient = patient;
	}

	/**
	 * @return the id
	 */
	public PatientCodingActorIdDTO getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(PatientCodingActorIdDTO id) {
		this.id = id;
	}

	/**
	 * @return the codingActor
	 */
	public CodingActorDTO getCodingActor() {
		return codingActor;
	}

	/**
	 * @param codingActor the codingActor to set
	 */
	public void setCodingActor(CodingActorDTO codingActor) {
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
}
