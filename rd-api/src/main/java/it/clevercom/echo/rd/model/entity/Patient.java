package it.clevercom.echo.rd.model.entity;
// Generated 8-mar-2017 10.59.59 by Hibernate Tools 5.2.2.Final


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Patient generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_patient"
)
public class Patient  implements java.io.Serializable {


     private Long idpatient;
     private Citizenship citizenship;
     private Country countryByDomicileidcountry;
     private Country countryByResidenceidcountry;
     private Country countryByBirthplaceidcountry;
     private Maritalstatus maritalstatus;
     private Municipality municipalityByDomicileidmunicipality;
     private Municipality municipalityByResidenceidmunicipality;
     private Municipality municipalityByBirthplaceidmunicipality;
     private OrganizationUnit organizationUnitByIdintorganizationunit;
     private OrganizationUnit organizationUnitByIdextorganizationunit;
     private String name;
     private String surname;
     private Date dateofbirth;
     private String gender;
     private String residencestreetaddress;
     private String domicilestreetaddress;
     private Date deathdate;
     private String taxcode;
     private String phonenumber;
     private Date created;
     private Date updated;
     private String userupdate;
     private boolean active;
     private String email;
     private Set<Hl7Patient> hl7Patients = new HashSet<Hl7Patient>(0);
     private Set<PatientCodingActor> patientCodingActors = new HashSet<PatientCodingActor>(0);
     private Set<WorkSession> workSessions = new HashSet<WorkSession>(0);

    public Patient() {
    }

	
    public Patient(Date created, Date updated, String userupdate, boolean active) {
        this.created = created;
        this.updated = updated;
        this.userupdate = userupdate;
        this.active = active;
    }
    public Patient(Citizenship citizenship, Country countryByDomicileidcountry, Country countryByResidenceidcountry, Country countryByBirthplaceidcountry, Maritalstatus maritalstatus, Municipality municipalityByDomicileidmunicipality, Municipality municipalityByResidenceidmunicipality, Municipality municipalityByBirthplaceidmunicipality, OrganizationUnit organizationUnitByIdintorganizationunit, OrganizationUnit organizationUnitByIdextorganizationunit, String name, String surname, Date dateofbirth, String gender, String residencestreetaddress, String domicilestreetaddress, Date deathdate, String taxcode, String phonenumber, Date created, Date updated, String userupdate, boolean active, String email, Set<Hl7Patient> hl7Patients, Set<PatientCodingActor> patientCodingActors, Set<WorkSession> workSessions) {
       this.citizenship = citizenship;
       this.countryByDomicileidcountry = countryByDomicileidcountry;
       this.countryByResidenceidcountry = countryByResidenceidcountry;
       this.countryByBirthplaceidcountry = countryByBirthplaceidcountry;
       this.maritalstatus = maritalstatus;
       this.municipalityByDomicileidmunicipality = municipalityByDomicileidmunicipality;
       this.municipalityByResidenceidmunicipality = municipalityByResidenceidmunicipality;
       this.municipalityByBirthplaceidmunicipality = municipalityByBirthplaceidmunicipality;
       this.organizationUnitByIdintorganizationunit = organizationUnitByIdintorganizationunit;
       this.organizationUnitByIdextorganizationunit = organizationUnitByIdextorganizationunit;
       this.name = name;
       this.surname = surname;
       this.dateofbirth = dateofbirth;
       this.gender = gender;
       this.residencestreetaddress = residencestreetaddress;
       this.domicilestreetaddress = domicilestreetaddress;
       this.deathdate = deathdate;
       this.taxcode = taxcode;
       this.phonenumber = phonenumber;
       this.created = created;
       this.updated = updated;
       this.userupdate = userupdate;
       this.active = active;
       this.email = email;
       this.hl7Patients = hl7Patients;
       this.patientCodingActors = patientCodingActors;
       this.workSessions = workSessions;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="patient_idpatient_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idpatient", unique=true, nullable=false)
    public Long getIdpatient() {
        return this.idpatient;
    }
    
    public void setIdpatient(Long idpatient) {
        this.idpatient = idpatient;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idcitizenship")
    public Citizenship getCitizenship() {
        return this.citizenship;
    }
    
    public void setCitizenship(Citizenship citizenship) {
        this.citizenship = citizenship;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="domicileidcountry")
    public Country getCountryByDomicileidcountry() {
        return this.countryByDomicileidcountry;
    }
    
    public void setCountryByDomicileidcountry(Country countryByDomicileidcountry) {
        this.countryByDomicileidcountry = countryByDomicileidcountry;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="residenceidcountry")
    public Country getCountryByResidenceidcountry() {
        return this.countryByResidenceidcountry;
    }
    
    public void setCountryByResidenceidcountry(Country countryByResidenceidcountry) {
        this.countryByResidenceidcountry = countryByResidenceidcountry;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="birthplaceidcountry")
    public Country getCountryByBirthplaceidcountry() {
        return this.countryByBirthplaceidcountry;
    }
    
    public void setCountryByBirthplaceidcountry(Country countryByBirthplaceidcountry) {
        this.countryByBirthplaceidcountry = countryByBirthplaceidcountry;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="codemaritalstatus")
    public Maritalstatus getMaritalstatus() {
        return this.maritalstatus;
    }
    
    public void setMaritalstatus(Maritalstatus maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="domicileidmunicipality")
    public Municipality getMunicipalityByDomicileidmunicipality() {
        return this.municipalityByDomicileidmunicipality;
    }
    
    public void setMunicipalityByDomicileidmunicipality(Municipality municipalityByDomicileidmunicipality) {
        this.municipalityByDomicileidmunicipality = municipalityByDomicileidmunicipality;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="residenceidmunicipality")
    public Municipality getMunicipalityByResidenceidmunicipality() {
        return this.municipalityByResidenceidmunicipality;
    }
    
    public void setMunicipalityByResidenceidmunicipality(Municipality municipalityByResidenceidmunicipality) {
        this.municipalityByResidenceidmunicipality = municipalityByResidenceidmunicipality;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="birthplaceidmunicipality")
    public Municipality getMunicipalityByBirthplaceidmunicipality() {
        return this.municipalityByBirthplaceidmunicipality;
    }
    
    public void setMunicipalityByBirthplaceidmunicipality(Municipality municipalityByBirthplaceidmunicipality) {
        this.municipalityByBirthplaceidmunicipality = municipalityByBirthplaceidmunicipality;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idintorganizationunit")
    public OrganizationUnit getOrganizationUnitByIdintorganizationunit() {
        return this.organizationUnitByIdintorganizationunit;
    }
    
    public void setOrganizationUnitByIdintorganizationunit(OrganizationUnit organizationUnitByIdintorganizationunit) {
        this.organizationUnitByIdintorganizationunit = organizationUnitByIdintorganizationunit;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idextorganizationunit")
    public OrganizationUnit getOrganizationUnitByIdextorganizationunit() {
        return this.organizationUnitByIdextorganizationunit;
    }
    
    public void setOrganizationUnitByIdextorganizationunit(OrganizationUnit organizationUnitByIdextorganizationunit) {
        this.organizationUnitByIdextorganizationunit = organizationUnitByIdextorganizationunit;
    }

    
    @Column(name="name", length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="surname", length=100)
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

    
    @Column(name="domicilestreetaddress", length=120)
    public String getDomicilestreetaddress() {
        return this.domicilestreetaddress;
    }
    
    public void setDomicilestreetaddress(String domicilestreetaddress) {
        this.domicilestreetaddress = domicilestreetaddress;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="deathdate", length=13)
    public Date getDeathdate() {
        return this.deathdate;
    }
    
    public void setDeathdate(Date deathdate) {
        this.deathdate = deathdate;
    }

    
    @Column(name="taxcode", length=50)
    public String getTaxcode() {
        return this.taxcode;
    }
    
    public void setTaxcode(String taxcode) {
        this.taxcode = taxcode;
    }

    
    @Column(name="phonenumber", length=100)
    public String getPhonenumber() {
        return this.phonenumber;
    }
    
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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
    public boolean isActive() {
        return this.active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }

    
    @Column(name="email")
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="patient")
    public Set<Hl7Patient> getHl7Patients() {
        return this.hl7Patients;
    }
    
    public void setHl7Patients(Set<Hl7Patient> hl7Patients) {
        this.hl7Patients = hl7Patients;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="patient")
    public Set<PatientCodingActor> getPatientCodingActors() {
        return this.patientCodingActors;
    }
    
    public void setPatientCodingActors(Set<PatientCodingActor> patientCodingActors) {
        this.patientCodingActors = patientCodingActors;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="patient")
    public Set<WorkSession> getWorkSessions() {
        return this.workSessions;
    }
    
    public void setWorkSessions(Set<WorkSession> workSessions) {
        this.workSessions = workSessions;
    }




}


