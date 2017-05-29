package it.clevercom.echo.rd.model.dto;

public class PidDTO extends BaseObjectDTO {
	private static final long serialVersionUID = 8518818775395682590L;

	private Long idPid;
	private String assigningAutority;
	private String idTypeCode;
	
	public Long getIdPid() {
		return idPid;
	}

	public void setIdPid(Long idPid) {
		this.idPid = idPid;
	}

	public String getAssigningAutority() {
		return assigningAutority;
	}

	public void setAssigningAutority(String assigningAutority) {
		this.assigningAutority = assigningAutority;
	}

	public String getIdTypeCode() {
		return idTypeCode;
	}

	public void setIdTypeCode(String idTypeCode) {
		this.idTypeCode = idTypeCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
