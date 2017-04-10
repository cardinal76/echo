package it.clevercom.echo.rd.model.entity;
// Generated 10-apr-2017 14.37.18 by Hibernate Tools 5.2.2.Final


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_user"
)
public class User  implements java.io.Serializable {


     private String username;
     private String type;
     private String name;
     private String surname;
     private String gender;
     private String taxcode;
     private Date birthdate;
     private String phonenumber;
     private Date created;
     private Date updated;
     private String userupdate;
     private Boolean active;
     private String alternativephonenumber;
     private String email;
     private Set<AppSetting> appSettings = new HashSet<AppSetting>(0);
     private Set<WorkReportUser> workReportUsers = new HashSet<WorkReportUser>(0);
     private Set<PhraseBook> phraseBooks = new HashSet<PhraseBook>(0);
     private Set<WorkTask> workTasks = new HashSet<WorkTask>(0);

    public User() {
    }

	
    public User(String username, String type, String name, String surname, String gender, Date created, Date updated, String userupdate, Boolean active) {
        this.username = username;
        this.type = type;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.created = created;
        this.updated = updated;
        this.userupdate = userupdate;
        this.active = active;
    }
    public User(String username, String type, String name, String surname, String gender, String taxcode, Date birthdate, String phonenumber, Date created, Date updated, String userupdate, Boolean active, String alternativephonenumber, String email, Set<AppSetting> appSettings, Set<WorkReportUser> workReportUsers, Set<PhraseBook> phraseBooks, Set<WorkTask> workTasks) {
       this.username = username;
       this.type = type;
       this.name = name;
       this.surname = surname;
       this.gender = gender;
       this.taxcode = taxcode;
       this.birthdate = birthdate;
       this.phonenumber = phonenumber;
       this.created = created;
       this.updated = updated;
       this.userupdate = userupdate;
       this.active = active;
       this.alternativephonenumber = alternativephonenumber;
       this.email = email;
       this.appSettings = appSettings;
       this.workReportUsers = workReportUsers;
       this.phraseBooks = phraseBooks;
       this.workTasks = workTasks;
    }
   
     @Id 

    
    @Column(name="username", unique=true, nullable=false, length=100)
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    
    @Column(name="type", nullable=false, length=50)
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    
    @Column(name="name", nullable=false, length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="surname", nullable=false, length=100)
    public String getSurname() {
        return this.surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }

    
    @Column(name="gender", nullable=false, length=1)
    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    
    @Column(name="taxcode", length=20)
    public String getTaxcode() {
        return this.taxcode;
    }
    
    public void setTaxcode(String taxcode) {
        this.taxcode = taxcode;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="birthdate", length=13)
    public Date getBirthdate() {
        return this.birthdate;
    }
    
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
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
    public Boolean getActive() {
        return this.active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }

    
    @Column(name="alternativephonenumber", length=100)
    public String getAlternativephonenumber() {
        return this.alternativephonenumber;
    }
    
    public void setAlternativephonenumber(String alternativephonenumber) {
        this.alternativephonenumber = alternativephonenumber;
    }

    
    @Column(name="email")
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<AppSetting> getAppSettings() {
        return this.appSettings;
    }
    
    public void setAppSettings(Set<AppSetting> appSettings) {
        this.appSettings = appSettings;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<WorkReportUser> getWorkReportUsers() {
        return this.workReportUsers;
    }
    
    public void setWorkReportUsers(Set<WorkReportUser> workReportUsers) {
        this.workReportUsers = workReportUsers;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<PhraseBook> getPhraseBooks() {
        return this.phraseBooks;
    }
    
    public void setPhraseBooks(Set<PhraseBook> phraseBooks) {
        this.phraseBooks = phraseBooks;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<WorkTask> getWorkTasks() {
        return this.workTasks;
    }
    
    public void setWorkTasks(Set<WorkTask> workTasks) {
        this.workTasks = workTasks;
    }




}


