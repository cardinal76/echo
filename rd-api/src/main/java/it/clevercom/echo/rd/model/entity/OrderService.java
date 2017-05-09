package it.clevercom.echo.rd.model.entity;
// Generated 9-mag-2017 16.58.12 by Hibernate Tools 5.2.2.Final


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
 * OrderService generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_order_service"
    , uniqueConstraints = @UniqueConstraint(columnNames={"idorder", "idservice"}) 
)
public class OrderService  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private Long idorderservice;
     private Order order;
     private Service service;
     private String addedreason;
     private String canceledreason;

    public OrderService() {
    }

	
    public OrderService(Order order, Service service) {
        this.order = order;
        this.service = service;
    }
    public OrderService(Order order, Service service, String addedreason, String canceledreason) {
       this.order = order;
       this.service = service;
       this.addedreason = addedreason;
       this.canceledreason = canceledreason;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="orderservice_idorderservice_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idorderservice", unique=true, nullable=false)
    public Long getIdorderservice() {
        return this.idorderservice;
    }
    
    public void setIdorderservice(Long idorderservice) {
        this.idorderservice = idorderservice;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idorder", nullable=false)
    public Order getOrder() {
        return this.order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idservice", nullable=false)
    public Service getService() {
        return this.service;
    }
    
    public void setService(Service service) {
        this.service = service;
    }

    
    @Column(name="addedreason", length=1000)
    public String getAddedreason() {
        return this.addedreason;
    }
    
    public void setAddedreason(String addedreason) {
        this.addedreason = addedreason;
    }

    
    @Column(name="canceledreason", length=1000)
    public String getCanceledreason() {
        return this.canceledreason;
    }
    
    public void setCanceledreason(String canceledreason) {
        this.canceledreason = canceledreason;
    }




}


