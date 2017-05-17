package it.clevercom.echo.rd.model.dto;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import it.clevercom.echo.common.dto.AbstractEchoDTO;
import it.clevercom.echo.common.jpa.entity.AbstractJpaEchoEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

public class Icd9PatologyGroupDTO  extends AbstractEchoDTO implements java.io.Serializable {
     private Long idIcd9PatologyGroup;
     private String codeIcd9Group;
     private String description;
     // private Set<Icd9Patology> icd9Patologies = new HashSet<Icd9Patology>(0);
	
     /**
	 * @return the idIcd9PatologyGroup
	 */
	public Long getIdIcd9PatologyGroup() {
		return idIcd9PatologyGroup;
	}
	
	/**
	 * @param idIcd9PatologyGroup the idIcd9PatologyGroup to set
	 */
	public void setIdIcd9PatologyGroup(Long idIcd9PatologyGroup) {
		this.idIcd9PatologyGroup = idIcd9PatologyGroup;
	}
	
	/**
	 * @return the codeIcd9Group
	 */
	public String getCodeIcd9Group() {
		return codeIcd9Group;
	}
	
	/**
	 * @param codeIcd9Group the codeIcd9Group to set
	 */
	public void setCodeIcd9Group(String codeIcd9Group) {
		this.codeIcd9Group = codeIcd9Group;
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

	@Override
	public Object getIdd() {
		return getIdIcd9PatologyGroup();
	}
}


