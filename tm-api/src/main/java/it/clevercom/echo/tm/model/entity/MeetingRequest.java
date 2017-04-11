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
 * MeetingRequest generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="tm_meeting_request"
)
public class MeetingRequest  implements java.io.Serializable {


     private Long idmeetingrequest;
     private Patient patient;
     private Physician physician;
     private Boolean approved;
     private String meetingdate;
     private String motivation;
     private Date created;
     private Date updated;
     private boolean active;
     private String updateuser;

    public MeetingRequest() {
    }

	
    public MeetingRequest(Long idmeetingrequest, Patient patient, Date created, Date updated, boolean active, String updateuser) {
        this.idmeetingrequest = idmeetingrequest;
        this.patient = patient;
        this.created = created;
        this.updated = updated;
        this.active = active;
        this.updateuser = updateuser;
    }
    public MeetingRequest(Long idmeetingrequest, Patient patient, Physician physician, Boolean approved, String meetingdate, String motivation, Date created, Date updated, boolean active, String updateuser) {
       this.idmeetingrequest = idmeetingrequest;
       this.patient = patient;
       this.physician = physician;
       this.approved = approved;
       this.meetingdate = meetingdate;
       this.motivation = motivation;
       this.created = created;
       this.updated = updated;
       this.active = active;
       this.updateuser = updateuser;
    }
   
     @Id 

    
    @Column(name="idmeetingrequest", unique=true, nullable=false)
    public Long getIdmeetingrequest() {
        return this.idmeetingrequest;
    }
    
    public void setIdmeetingrequest(Long idmeetingrequest) {
        this.idmeetingrequest = idmeetingrequest;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idpatient", nullable=false)
    public Patient getPatient() {
        return this.patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idphysician")
    public Physician getPhysician() {
        return this.physician;
    }
    
    public void setPhysician(Physician physician) {
        this.physician = physician;
    }

    
    @Column(name="approved")
    public Boolean getApproved() {
        return this.approved;
    }
    
    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    
    @Column(name="meetingdate", length=45)
    public String getMeetingdate() {
        return this.meetingdate;
    }
    
    public void setMeetingdate(String meetingdate) {
        this.meetingdate = meetingdate;
    }

    
    @Column(name="motivation", length=45)
    public String getMotivation() {
        return this.motivation;
    }
    
    public void setMotivation(String motivation) {
        this.motivation = motivation;
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

    
    @Column(name="updateuser", nullable=false)
    public String getUpdateuser() {
        return this.updateuser;
    }
    
    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }




}


