package it.clevercom.echo.rd.model.entity;
// Generated 10-apr-2017 11.15.06 by Hibernate Tools 5.2.2.Final


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Hl7OutboundMessage generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_hl7_outbound_message"
)
public class Hl7OutboundMessage  implements java.io.Serializable {


     private Long idoutboundmessage;
     private String messagecontrolid;
     private String message;
     private String status;
     private String messagetype;
     private Date sent;

    public Hl7OutboundMessage() {
    }

    public Hl7OutboundMessage(String messagecontrolid, String message, String status, String messagetype, Date sent) {
       this.messagecontrolid = messagecontrolid;
       this.message = message;
       this.status = status;
       this.messagetype = messagetype;
       this.sent = sent;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="hl7outboundmessage_id_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idoutboundmessage", unique=true, nullable=false)
    public Long getIdoutboundmessage() {
        return this.idoutboundmessage;
    }
    
    public void setIdoutboundmessage(Long idoutboundmessage) {
        this.idoutboundmessage = idoutboundmessage;
    }

    
    @Column(name="messagecontrolid", nullable=false)
    public String getMessagecontrolid() {
        return this.messagecontrolid;
    }
    
    public void setMessagecontrolid(String messagecontrolid) {
        this.messagecontrolid = messagecontrolid;
    }

    
    @Column(name="message", nullable=false)
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    
    @Column(name="status", nullable=false, length=20)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    
    @Column(name="messagetype", nullable=false, length=10)
    public String getMessagetype() {
        return this.messagetype;
    }
    
    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="sent", nullable=false, length=29)
    public Date getSent() {
        return this.sent;
    }
    
    public void setSent(Date sent) {
        this.sent = sent;
    }




}


