package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int prime = 37;
		int result = 17;
        result = prime * result + ((id==null) ? 0 : id.hashCode());
        result = prime * result + ((name==null) ? 0 : name.hashCode());
        result = prime * result + ((code==null) ? 0 : code.hashCode());
        return result;
	}

	/**
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object object) {
		boolean flag = true;
		if (!(object instanceof BaseObjectDTO)) {
			return flag &= false;
		} else {
			BaseObjectDTO obj = (BaseObjectDTO) object;
			
			if ((this.id == null) ^ (obj.id == null)) {
				flag &= false;
			} else if ((this.id != null) && (obj.id != null) && (!this.id.equalsIgnoreCase(obj.id))) {
				flag &= false;
			} else if ((this.id != null) && (obj.id != null) && (this.id.equalsIgnoreCase(obj.id))) {
				flag &= true;
			}
			
			if ((this.name == null) ^ (obj.name == null)) {
				flag &= false;
			} else if ((this.name != null) && (obj.name != null) && (!this.name.equalsIgnoreCase(obj.name))) {
				flag &= false;
			} else if ((this.name != null) && (obj.name != null) && (this.name.equalsIgnoreCase(obj.name))) {
				flag &= true;
			}
			
			if ((this.code == null) ^ (obj.code == null)) {
				flag &= false;
			} else if ((this.code != null) && (obj.code != null) && (!this.code.equalsIgnoreCase(obj.code))) {
				flag &= false;
			} else if ((this.code != null) && (obj.code != null) && (this.code.equalsIgnoreCase(obj.code))) {
				flag &= true;
			}
			
			return flag;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", code=" + "code" + "]";
	}	
}
