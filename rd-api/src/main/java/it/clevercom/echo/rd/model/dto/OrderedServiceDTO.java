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
}
