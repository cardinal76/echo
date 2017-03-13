package it.clevercom.echo.rd.model.entity;
// Generated 13-mar-2017 9.59.07 by Hibernate Tools 5.2.2.Final


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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * BurnRobot generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_burn_robot"
)
public class BurnRobot  implements java.io.Serializable {


     private Long idburnrobot;
     private OrganizationUnit organizationUnit;
     private String name;
     private String hostname;
     private String ipaddress;
     private String uri;
     private Date created;
     private Date updated;
     private String userupdate;
     private Boolean active;

    public BurnRobot() {
    }

	
    public BurnRobot(String name, Date created, Date updated, String userupdate, Boolean active) {
        this.name = name;
        this.created = created;
        this.updated = updated;
        this.userupdate = userupdate;
        this.active = active;
    }
    public BurnRobot(OrganizationUnit organizationUnit, String name, String hostname, String ipaddress, String uri, Date created, Date updated, String userupdate, Boolean active) {
       this.organizationUnit = organizationUnit;
       this.name = name;
       this.hostname = hostname;
       this.ipaddress = ipaddress;
       this.uri = uri;
       this.created = created;
       this.updated = updated;
       this.userupdate = userupdate;
       this.active = active;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="burn_robot_idburnrobot_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idburnrobot", unique=true, nullable=false)
    public Long getIdburnrobot() {
        return this.idburnrobot;
    }
    
    public void setIdburnrobot(Long idburnrobot) {
        this.idburnrobot = idburnrobot;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idorganizationunit")
    public OrganizationUnit getOrganizationUnit() {
        return this.organizationUnit;
    }
    
    public void setOrganizationUnit(OrganizationUnit organizationUnit) {
        this.organizationUnit = organizationUnit;
    }

    
    @Column(name="name", nullable=false)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="hostname")
    public String getHostname() {
        return this.hostname;
    }
    
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    
    @Column(name="ipaddress")
    public String getIpaddress() {
        return this.ipaddress;
    }
    
    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    
    @Column(name="uri")
    public String getUri() {
        return this.uri;
    }
    
    public void setUri(String uri) {
        this.uri = uri;
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


