package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientSmartDTO implements Serializable {
	private static final long serialVersionUID = -8879525081996795944L;
	
	private Long idPatient;	
	private String name;
	private String surname;
	private Long dateOfBirth;
	private String gender;
	private String taxCode;
	private String email;
	private String prettyPrint;
	
	/**
	 * @return the idPatient
	 */
	public Long getIdPatient() {
		return idPatient;
	}

	/**
	 * @param idPatient the idPatient to set
	 */
	public void setIdPatient(Long idPatient) {
		this.idPatient = idPatient;
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
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Long getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the taxCode
	 */
	public String getTaxCode() {
		return taxCode;
	}

	/**
	 * @param taxCode the taxCode to set
	 */
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the prettyPrint
	 */
	public String getPrettyPrint() {
		return prettyPrint;
	}

	/**
	 * @param prettyPrint the prettyPrint to set
	 */
	public void setPrettyPrint(String prettyPrint) {
		this.prettyPrint = prettyPrint;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return
	 */
	public PatientSmartDTO buildExtendedObject() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.setPrettyPrint(this.getName() + " " + this.getSurname() + " (" + ((this.getDateOfBirth()!=null) ? df.format(new Date(this.getDateOfBirth()))  : "--/--/----") + ")");
		return this;
	}
}
