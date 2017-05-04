package it.clevercom.echo.tm.model.dto.gateway;

import java.io.Serializable;

import it.clevercom.echo.common.dto.AbstractEchoDTO;

public class BaseGatewayEntityDTO extends AbstractEchoDTO implements Serializable{
	private static final long serialVersionUID = -8698686876104664533L;

	private Long id;
	private String pheripheral;
	private String gatewaySerialNumber;
	private Long receivedDate;
	private Long timeStamp;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the pheripheral
	 */
	public String getPheripheral() {
		return pheripheral;
	}
	
	/**
	 * @param pheripheral the pheripheral to set
	 */
	public void setPheripheral(String pheripheral) {
		this.pheripheral = pheripheral;
	}
	
	/**
	 * @return the gatewaySerialNumber
	 */
	public String getGatewaySerialNumber() {
		return gatewaySerialNumber;
	}
	
	/**
	 * @param gatewaySerialNumber the gatewaySerialNumber to set
	 */
	public void setGatewaySerialNumber(String gatewaySerialNumber) {
		this.gatewaySerialNumber = gatewaySerialNumber;
	}
	
	/**
	 * @return the receivedDate
	 */
	public Long getReceivedDate() {
		return receivedDate;
	}
	
	/**
	 * @param receivedDate the receivedDate to set
	 */
	public void setReceivedDate(Long receivedDate) {
		this.receivedDate = receivedDate;
	}
	
	/**
	 * @return the timeStamp
	 */
	public Long getTimeStamp() {
		return timeStamp;
	}
	
	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public Object getIdd() {
		return getId();
	}
}
