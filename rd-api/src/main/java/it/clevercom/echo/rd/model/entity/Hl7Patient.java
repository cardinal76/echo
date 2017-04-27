package it.clevercom.echo.rd.model.entity;
// Generated 27-apr-2017 10.34.01 by Hibernate Tools 5.2.2.Final


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import it.clevercom.echo.common.jpa.entity.AbstractJpaEchoEntity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Hl7Patient generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_hl7_patient"
)
public class Hl7Patient  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private Long idhl7patient;
     private Patient patient;
     private String idhl7authoritypatient;
     private String idauthoritynamespaceid;
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

	
    public Hl7Patient(String idhl7authoritypatient, String idauthoritynamespaceid, String idauthority, String idtypecode, String messagecontrolid) {
        this.idhl7authoritypatient = idhl7authoritypatient;
        this.idauthoritynamespaceid = idauthoritynamespaceid;
        this.idauthority = idauthority;
        this.idtypecode = idtypecode;
        this.messagecontrolid = messagecontrolid;
    }
    public Hl7Patient(Patient patient, String idhl7authoritypatient, String idauthoritynamespaceid, String idauthority, String idtypecode, String name, String surname, Date dateofbirth, String gender, String residencestreetaddress, String residencecitycode, String residencecountry, String domicilestreetaddress, String domicilecitycode, String domicilecountry, String maritalstatus, String birthplace, String citizenshipid, String citizenshipdesc, Date deathdate, String messagecontrolid) {
       this.patient = patient;
       this.idhl7authoritypatient = idhl7authoritypatient;
       this.idauthoritynamespaceid = idauthoritynamespaceid;
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
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="hl7patient_id_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idhl7patient", unique=true, nullable=false)
    public Long getIdhl7patient() {
        return this.idhl7patient;
    }
    
    public void setIdhl7patient(Long idhl7patient) {
        this.idhl7patient = idhl7patient;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idpatient")
    public Patient getPatient() {
        return this.patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    
    @Column(name="idhl7authoritypatient", nullable=false, length=16)
    public String getIdhl7authoritypatient() {
        return this.idhl7authoritypatient;
    }
    
    public void setIdhl7authoritypatient(String idhl7authoritypatient) {
        this.idhl7authoritypatient = idhl7authoritypatient;
    }

    
    @Column(name="idauthoritynamespaceid", nullable=false, length=20)
    public String getIdauthoritynamespaceid() {
        return this.idauthoritynamespaceid;
    }
    
    public void setIdauthoritynamespaceid(String idauthoritynamespaceid) {
        this.idauthoritynamespaceid = idauthoritynamespaceid;
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


