package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;

public class BaseObjectDTO implements Serializable {
	private static final long serialVersionUID = -6326932178674963831L;
	
	private String id;
	private String name;
	private String code;
	
	public BaseObjectDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseObjectDTO(String id, String name, String code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
}
