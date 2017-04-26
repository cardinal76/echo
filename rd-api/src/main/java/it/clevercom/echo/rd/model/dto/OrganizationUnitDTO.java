package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.clevercom.echo.common.dto.AbstractEchoDTO;

@JsonIgnoreProperties({"created","updated","userupdate","active","idd"})
public class OrganizationUnitDTO extends AbstractEchoDTO implements Serializable {
	private static final long serialVersionUID = -8750546516765816251L;

	private Long idorganizationunit;
	private BaseObjectDTO municipality;
	private BaseObjectDTO parentOrganizationUnit;
	private String name;
	private String description;
	private String address;
	private String telephone;
	private String email;
	private String fax;
	private String website;
	private String type;
	private Date created;
	private Date updated;
	private String userupdate;
	private Boolean active;
	private String code;

	private Set<OrganizationUnitDTO> childOrganizationUnit = new HashSet<OrganizationUnitDTO>(0);

	public OrganizationUnitDTO() {
	}

	public OrganizationUnitDTO(Long idorganizationunit, Date created, Date updated, String userupdate, boolean active) {
		this.idorganizationunit = idorganizationunit;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
	}

	/**
	 * @return the idorganizationunit
	 */
	public Long getIdorganizationunit() {
		return idorganizationunit;
	}

	/**
	 * @param idorganizationunit the idorganizationunit to set
	 */
	public void setIdorganizationunit(Long idorganizationunit) {
		this.idorganizationunit = idorganizationunit;
	}

	/**
	 * @return the municipality
	 */
	public BaseObjectDTO getMunicipality() {
		return municipality;
	}

	/**
	 * @param municipality the municipality to set
	 */
	public void setMunicipality(BaseObjectDTO municipality) {
		this.municipality = municipality;
	}

	/**
	 * @return the parentOrganizationUnit
	 */
	public BaseObjectDTO getParentOrganizationUnit() {
		return parentOrganizationUnit;
	}

	/**
	 * @param parentOrganizationUnit the parentOrganizationUnit to set
	 */
	public void setParentOrganizationUnit(BaseObjectDTO parentOrganizationUnit) {
		this.parentOrganizationUnit = parentOrganizationUnit;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the childOrganizationUnit
	 */
	public Set<OrganizationUnitDTO> getChildOrganizationUnit() {
		return childOrganizationUnit;
	}

	/**
	 * @param childOrganizationUnit the childOrganizationUnit to set
	 */
	public void setChildOrganizationUnit(Set<OrganizationUnitDTO> childOrganizationUnit) {
		this.childOrganizationUnit = childOrganizationUnit;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public Object getIdd() {
		return idorganizationunit;
	}
}
