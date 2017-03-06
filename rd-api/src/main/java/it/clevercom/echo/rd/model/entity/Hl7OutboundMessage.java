package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rd_hl7_outbound_message database table.
 * 
 */
@Entity
@Table(name="rd_hl7_outbound_message")
@NamedQuery(name="Hl7OutboundMessage.findAll", query="SELECT h FROM Hl7OutboundMessage h")
public class Hl7OutboundMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idoutboundmessage;

	private String message;

	private String messagecontrolid;

	private String messagetype;

	private Timestamp sent;

	private String status;

	public Hl7OutboundMessage() {
	}

	public Long getIdoutboundmessage() {
		return this.idoutboundmessage;
	}

	public void setIdoutboundmessage(Long idoutboundmessage) {
		this.idoutboundmessage = idoutboundmessage;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessagecontrolid() {
		return this.messagecontrolid;
	}

	public void setMessagecontrolid(String messagecontrolid) {
		this.messagecontrolid = messagecontrolid;
	}

	public String getMessagetype() {
		return this.messagetype;
	}

	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}

	public Timestamp getSent() {
		return this.sent;
	}

	public void setSent(Timestamp sent) {
		this.sent = sent;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}