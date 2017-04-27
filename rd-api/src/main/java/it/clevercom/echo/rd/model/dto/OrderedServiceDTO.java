package it.clevercom.echo.rd.model.dto;

public class OrderedServiceDTO extends BaseObjectDTO {
	private static final long serialVersionUID = 8518818775395682590L;

	private String addedReason;
	private String cancelReason;
	
	/**
	 * @return the addedReason
	 */
	public String getAddedReason() {
		return addedReason;
	}
	
	/**
	 * @param addedReason the addedReason to set
	 */
	public void setAddedReason(String addedReason) {
		this.addedReason = addedReason;
	}
	
	/**
	 * @return the cancelReason
	 */
	public String getCancelReason() {
		return cancelReason;
	}
	
	/**
	 * @param cancelReason the cancelReason to set
	 */
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((addedReason == null) ? 0 : addedReason.hashCode());
		result = prime * result + ((cancelReason == null) ? 0 : cancelReason.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderedServiceDTO other = (OrderedServiceDTO) obj;
		if (addedReason == null) {
			if (other.addedReason != null)
				return false;
		} else if (!addedReason.equals(other.addedReason))
			return false;
		if (cancelReason == null) {
			if (other.cancelReason != null)
				return false;
		} else if (!cancelReason.equals(other.cancelReason))
			return false;
		return true;
	}
}
