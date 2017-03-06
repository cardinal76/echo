package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rd_hl7_inbound_message database table.
 * 
 */
@Entity
@Table(name="rd_hl7_inbound_message")
@NamedQuery(name="Hl7InboundMessage.findAll", query="SELECT h FROM Hl7InboundMessage h")
public class Hl7InboundMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idinboundmessage;

	private String message;

	private String messagecontrolid;

	private String messagetype;

	private Timestamp received;

	private String status;

	public Hl7InboundMessage() {
	}

	public Long getIdinboundmessage() {
		return this.idinboundmessage;
	}

	public void setIdinboundmessage(Long idinboundmessage) {
		this.idinboundmessage = idinboundmessage;
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

	public Timestamp getReceived() {
		return this.received;
	}

	public void setReceived(Timestamp received) {
		this.received = received;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}