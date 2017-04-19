package it.clevercom.echo.rd.model.entity;
// Generated 19-apr-2017 12.25.52 by Hibernate Tools 5.2.2.Final


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
 * Hl7InboundMessage generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_hl7_inbound_message"
)
public class Hl7InboundMessage  implements java.io.Serializable {


     private Long idinboundmessage;
     private String messagecontrolid;
     private String message;
     private String status;
     private String messagetype;
     private Date received;
     private Date created;
     private Date updated;
     private String userupdate;
     private Boolean active;

    public Hl7InboundMessage() {
    }

    public Hl7InboundMessage(String messagecontrolid, String message, String status, String messagetype, Date received, Date created, Date updated, String userupdate, Boolean active) {
       this.messagecontrolid = messagecontrolid;
       this.message = message;
       this.status = status;
       this.messagetype = messagetype;
       this.received = received;
       this.created = created;
       this.updated = updated;
       this.userupdate = userupdate;
       this.active = active;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="hl7inboundmessage_id_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idinboundmessage", unique=true, nullable=false)
    public Long getIdinboundmessage() {
        return this.idinboundmessage;
    }
    
    public void setIdinboundmessage(Long idinboundmessage) {
        this.idinboundmessage = idinboundmessage;
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
    @Column(name="received", nullable=false, length=29)
    public Date getReceived() {
        return this.received;
    }
    
    public void setReceived(Date received) {
        this.received = received;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created", nullable=false, length=29)
    public Date getCreated() {
        return this.created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated", nullable=false, length=29)
    public Date getUpdated() {
        return this.updated;
    }
    
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    
    @Column(name="userupdate", nullable=false, length=100)
    public String getUserupdate() {
        return this.userupdate;
    }
    
    public void setUserupdate(String userupdate) {
        this.userupdate = userupdate;
    }

    
    @Column(name="active", nullable=false)
    public Boolean getActive() {
        return this.active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }




}


