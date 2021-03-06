package it.clevercom.echo.rd.model.entity;
// Generated 24-mag-2017 22.43.53 by Hibernate Tools 5.2.2.Final


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
import it.clevercom.echo.common.jpa.entity.AbstractJpaEchoEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Region generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_region"
)
public class Region  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private Long idregion;
     private Country country;
     private String regionname;
     private String regionstdcode;
     private Set<Province> provinces = new HashSet<Province>(0);

    public Region() {
    }

    public Region(Country country, String regionname, String regionstdcode, Set<Province> provinces) {
       this.country = country;
       this.regionname = regionname;
       this.regionstdcode = regionstdcode;
       this.provinces = provinces;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="region_idregion_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idregion", unique=true, nullable=false)
    public Long getIdregion() {
        return this.idregion;
    }
    
    public void setIdregion(Long idregion) {
        this.idregion = idregion;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idcountry")
    public Country getCountry() {
        return this.country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }

    
    @Column(name="regionname", length=100)
    public String getRegionname() {
        return this.regionname;
    }
    
    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    
    @Column(name="regionstdcode", length=10)
    public String getRegionstdcode() {
        return this.regionstdcode;
    }
    
    public void setRegionstdcode(String regionstdcode) {
        this.regionstdcode = regionstdcode;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="region")
    public Set<Province> getProvinces() {
        return this.provinces;
    }
    
    public void setProvinces(Set<Province> provinces) {
        this.provinces = provinces;
    }




}


