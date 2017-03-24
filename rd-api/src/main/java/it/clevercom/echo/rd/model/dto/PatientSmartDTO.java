package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PatientSmartDTO implements Serializable {
	private static final long serialVersionUID = -8879525081996795944L;
	
	private Long idpatient;	
	private String name;
	private String surname;
	private Long dateofbirth;
	private String gender;
	private String taxcode;
	private String email;
	private String prettyPrint;
	
	/**
	 * @return the idpatient
	 */
	public Long getIdpatient() {
		return idpatient;
	}
	
	/**
	 * @param idpatient the idpatient to set
	 */
	public void setIdpatient(Long idpatient) {
		this.idpatient = idpatient;
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
	 * @return the dateofbirth
	 */
	public Long getDateofbirth() {
		return dateofbirth;
	}
	
	/**
	 * @param dateofbirth the dateofbirth to set
	 */
	public void setDateofbirth(Long dateofbirth) {
		this.dateofbirth = dateofbirth;
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
	 * @return the taxcode
	 */
	public String getTaxcode() {
		return taxcode;
	}
	
	/**
	 * @param taxcode the taxcode to set
	 */
	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
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
	 * @return
	 */
	public PatientSmartDTO buildExtendedObject() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.setPrettyPrint(this.getName() + " " + this.getSurname() + " (" + df.format(this.getDateofbirth()) + ")");
		return this;
	}
}
