package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.clevercom.echo.common.dto.AbstractEchoDTO;

@JsonIgnoreProperties({"created","updated","userupdate","active","idd"})
public class Hl7InboundMessageDTO extends AbstractEchoDTO implements Serializable {
	private static final long serialVersionUID = -1741576081102311354L;

	private Long idinboundmessage;
    private String messagecontrolid;
    private String message;
    private String status;
    private String messagetype;
    private Long received;
    
    // transient attributes
 	private Date created;
 	private Date updated;
 	private String userupdate;
 	private Boolean active;
    
	@Override
	public Object getIdd() {
		return getIdinboundmessage();
	}

	/**
	 * @return the idinboundmessage
	 */
	public Long getIdinboundmessage() {
		return idinboundmessage;
	}

	/**
	 * @param idinboundmessage the idinboundmessage to set
	 */
	public void setIdinboundmessage(Long idinboundmessage) {
		this.idinboundmessage = idinboundmessage;
	}

	/**
	 * @return the messagecontrolid
	 */
	public String getMessagecontrolid() {
		return messagecontrolid;
	}

	/**
	 * @param messagecontrolid the messagecontrolid to set
	 */
	public void setMessagecontrolid(String messagecontrolid) {
		this.messagecontrolid = messagecontrolid;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the messagetype
	 */
	public String getMessagetype() {
		return messagetype;
	}

	/**
	 * @param messagetype the messagetype to set
	 */
	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}

	/**
	 * @return the received
	 */
	public Long getReceived() {
		return received;
	}

	/**
	 * @param received the received to set
	 */
	public void setReceived(Long received) {
		this.received = received;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}


