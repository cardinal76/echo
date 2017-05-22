package it.clevercom.echo.rd.model.entity;
// Generated 22-mag-2017 10.36.57 by Hibernate Tools 5.2.2.Final


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
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * AppSetting generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_app_setting"
    , uniqueConstraints = @UniqueConstraint(columnNames={"key", "feature", "username"}) 
)
public class AppSetting  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private Long idappsetting;
     private User user;
     private String key;
     private String value;
     private String feature;

    public AppSetting() {
    }

	
    public AppSetting(String key, String value) {
        this.key = key;
        this.value = value;
    }
    public AppSetting(User user, String key, String value, String feature) {
       this.user = user;
       this.key = key;
       this.value = value;
       this.feature = feature;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="app_setting_idappsetting_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idappsetting", unique=true, nullable=false)
    public Long getIdappsetting() {
        return this.idappsetting;
    }
    
    public void setIdappsetting(Long idappsetting) {
        this.idappsetting = idappsetting;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="username")
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    
    @Column(name="key", nullable=false)
    public String getKey() {
        return this.key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }

    
    @Column(name="value", nullable=false, length=1000)
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

    
    @Column(name="feature")
    public String getFeature() {
        return this.feature;
    }
    
    public void setFeature(String feature) {
        this.feature = feature;
    }




}


