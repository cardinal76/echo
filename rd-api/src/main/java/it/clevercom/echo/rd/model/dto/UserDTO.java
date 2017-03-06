package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;


public class UserDTO implements Serializable {
	private static final long serialVersionUID = 5153480429424037770L;

	private Long iduser;
	private Boolean active;
	private String alternativephonenumber;
	private Date birthdate;
	private Date created;
	private String email;
	private String gender;
	private String name;
	private String phonenumber;
	private String surname;
	private String taxcode;
	private String type;
	private Date updated;
	private String username;
	private String userupdate;
	
	/**
	 * @return the iduser
	 */
	public Long getIduser() {
		return iduser;
	}
	
	/**
	 * @param iduser the iduser to set
	 */
	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}
	
	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}
	
	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	/**
	 * @return the alternativephonenumber
	 */
	public String getAlternativephonenumber() {
		return alternativephonenumber;
	}
	
	/**
	 * @param alternativephonenumber the alternativephonenumber to set
	 */
	public void setAlternativephonenumber(String alternativephonenumber) {
		this.alternativephonenumber = alternativephonenumber;
	}
	
	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}
	
	/**
	 * @param birthdate the birthdate to set
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}
	
	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
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
	 * @return the phonenumber
	 */
	public String getPhonenumber() {
		return phonenumber;
	}
	
	/**
	 * @param phonenumber the phonenumber to set
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @return the updated
	 */
	public Date getUpdated() {
		return updated;
	}
	
	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return the userupdate
	 */
	public String getUserupdate() {
		return userupdate;
	}
	
	/**
	 * @param userupdate the userupdate to set
	 */
	public void setUserupdate(String userupdate) {
		this.userupdate = userupdate;
	}
	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}