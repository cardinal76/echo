package it.clevercom.echo.tm.model.entity;
// Generated 11-apr-2017 13.43.21 by Hibernate Tools 5.2.2.Final


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Notification generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="tm_notification"
)
public class Notification  implements java.io.Serializable {


     private Long idnotification;
     private NotificationType notificationType;
     private String description;
     private Date created;
     private Date updated;
     private boolean active;
     private String updateuser;

    public Notification() {
    }

	
    public Notification(Long idnotification, NotificationType notificationType, Date created, Date updated, boolean active, String updateuser) {
        this.idnotification = idnotification;
        this.notificationType = notificationType;
        this.created = created;
        this.updated = updated;
        this.active = active;
        this.updateuser = updateuser;
    }
    public Notification(Long idnotification, NotificationType notificationType, String description, Date created, Date updated, boolean active, String updateuser) {
       this.idnotification = idnotification;
       this.notificationType = notificationType;
       this.description = description;
       this.created = created;
       this.updated = updated;
       this.active = active;
       this.updateuser = updateuser;
    }
   
     @Id 

    
    @Column(name="idnotification", unique=true, nullable=false)
    public Long getIdnotification() {
        return this.idnotification;
    }
    
    public void setIdnotification(Long idnotification) {
        this.idnotification = idnotification;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idnotificationtype", nullable=false)
    public NotificationType getNotificationType() {
        return this.notificationType;
    }
    
    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    
    @Column(name="description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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

    
    @Column(name="active", nullable=false)
    public boolean isActive() {
        return this.active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }

    
    @Column(name="updateuser", nullable=false, length=100)
    public String getUpdateuser() {
        return this.updateuser;
    }
    
    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }




}


