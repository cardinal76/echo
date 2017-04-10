package it.clevercom.echo.rd.model.entity;
// Generated 10-apr-2017 11.15.06 by Hibernate Tools 5.2.2.Final


import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Hl7Patient generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_hl7_patient"
)
public class Hl7Patient  implements java.io.Serializable {


     private Hl7PatientId id;
     private Patient patient;
     private String idauthority;
     private String idtypecode;
     private String name;
     private String surname;
     private Date dateofbirth;
     private String gender;
     private String residencestreetaddress;
     private String residencecitycode;
     private String residencecountry;
     private String domicilestreetaddress;
     private String domicilecitycode;
     private String domicilecountry;
     private String maritalstatus;
     private String birthplace;
     private String citizenshipid;
     private String citizenshipdesc;
     private Date deathdate;
     private String messagecontrolid;

    public Hl7Patient() {
    }

	
    public Hl7Patient(Hl7PatientId id, String idauthority, String idtypecode, String messagecontrolid) {
        this.id = id;
        this.idauthority = idauthority;
        this.idtypecode = idtypecode;
        this.messagecontrolid = messagecontrolid;
    }
    public Hl7Patient(Hl7PatientId id, Patient patient, String idauthority, String idtypecode, String name, String surname, Date dateofbirth, String gender, String residencestreetaddress, String residencecitycode, String residencecountry, String domicilestreetaddress, String domicilecitycode, String domicilecountry, String maritalstatus, String birthplace, String citizenshipid, String citizenshipdesc, Date deathdate, String messagecontrolid) {
       this.id = id;
       this.patient = patient;
       this.idauthority = idauthority;
       this.idtypecode = idtypecode;
       this.name = name;
       this.surname = surname;
       this.dateofbirth = dateofbirth;
       this.gender = gender;
       this.residencestreetaddress = residencestreetaddress;
       this.residencecitycode = residencecitycode;
       this.residencecountry = residencecountry;
       this.domicilestreetaddress = domicilestreetaddress;
       this.domicilecitycode = domicilecitycode;
       this.domicilecountry = domicilecountry;
       this.maritalstatus = maritalstatus;
       this.birthplace = birthplace;
       this.citizenshipid = citizenshipid;
       this.citizenshipdesc = citizenshipdesc;
       this.deathdate = deathdate;
       this.messagecontrolid = messagecontrolid;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="idhl7patient", column=@Column(name="idhl7patient", nullable=false, length=16) ), 
        @AttributeOverride(name="idauthoritynamespaceid", column=@Column(name="idauthoritynamespaceid", nullable=false, length=20) ) } )
    public Hl7PatientId getId() {
        return this.id;
    }
    
    public void setId(Hl7PatientId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idpatient")
    public Patient getPatient() {
        return this.patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    
    @Column(name="idauthority", nullable=false, length=199)
    public String getIdauthority() {
        return this.idauthority;
    }
    
    public void setIdauthority(String idauthority) {
        this.idauthority = idauthority;
    }

    
    @Column(name="idtypecode", nullable=false, length=5)
    public String getIdtypecode() {
        return this.idtypecode;
    }
    
    public void setIdtypecode(String idtypecode) {
        this.idtypecode = idtypecode;
    }

    
    @Column(name="name", length=30)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="surname", length=194)
    public String getSurname() {
        return this.surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="dateofbirth", length=13)
    public Date getDateofbirth() {
        return this.dateofbirth;
    }
    
    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    
    @Column(name="gender", length=1)
    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    
    @Column(name="residencestreetaddress", length=120)
    public String getResidencestreetaddress() {
        return this.residencestreetaddress;
    }
    
    public void setResidencestreetaddress(String residencestreetaddress) {
        this.residencestreetaddress = residencestreetaddress;
    }

    
    @Column(name="residencecitycode", length=50)
    public String getResidencecitycode() {
        return this.residencecitycode;
    }
    
    public void setResidencecitycode(String residencecitycode) {
        this.residencecitycode = residencecitycode;
    }

    
    @Column(name="residencecountry", length=4)
    public String getResidencecountry() {
        return this.residencecountry;
    }
    
    public void setResidencecountry(String residencecountry) {
        this.residencecountry = residencecountry;
    }

    
    @Column(name="domicilestreetaddress", length=120)
    public String getDomicilestreetaddress() {
        return this.domicilestreetaddress;
    }
    
    public void setDomicilestreetaddress(String domicilestreetaddress) {
        this.domicilestreetaddress = domicilestreetaddress;
    }

    
    @Column(name="domicilecitycode", length=50)
    public String getDomicilecitycode() {
        return this.domicilecitycode;
    }
    
    public void setDomicilecitycode(String domicilecitycode) {
        this.domicilecitycode = domicilecitycode;
    }

    
    @Column(name="domicilecountry", length=4)
    public String getDomicilecountry() {
        return this.domicilecountry;
    }
    
    public void setDomicilecountry(String domicilecountry) {
        this.domicilecountry = domicilecountry;
    }

    
    @Column(name="maritalstatus", length=20)
    public String getMaritalstatus() {
        return this.maritalstatus;
    }
    
    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    
    @Column(name="birthplace", length=250)
    public String getBirthplace() {
        return this.birthplace;
    }
    
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    
    @Column(name="citizenshipid", length=20)
    public String getCitizenshipid() {
        return this.citizenshipid;
    }
    
    public void setCitizenshipid(String citizenshipid) {
        this.citizenshipid = citizenshipid;
    }

    
    @Column(name="citizenshipdesc", length=199)
    public String getCitizenshipdesc() {
        return this.citizenshipdesc;
    }
    
    public void setCitizenshipdesc(String citizenshipdesc) {
        this.citizenshipdesc = citizenshipdesc;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="deathdate", length=13)
    public Date getDeathdate() {
        return this.deathdate;
    }
    
    public void setDeathdate(Date deathdate) {
        this.deathdate = deathdate;
    }

    
    @Column(name="messagecontrolid", nullable=false)
    public String getMessagecontrolid() {
        return this.messagecontrolid;
    }
    
    public void setMessagecontrolid(String messagecontrolid) {
        this.messagecontrolid = messagecontrolid;
    }




}


