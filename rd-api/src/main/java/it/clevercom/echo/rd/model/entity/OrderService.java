package it.clevercom.echo.rd.model.entity;
// Generated 7-mar-2017 16.29.46 by Hibernate Tools 5.2.2.Final


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * OrderService generated by hbm2java
 */
@Entity
@Table(name="rd_order_service"
)
public class OrderService  implements java.io.Serializable {


     private Long idorderservice;
     private Order order;
     private Service service;
     private Date created;
     private Date updated;
     private String userupdate;
     private Boolean active;

    public OrderService() {
    }

	
    public OrderService(Long idorderservice, Order order, Service service, Date created, Date updated, String userupdate) {
        this.idorderservice = idorderservice;
        this.order = order;
        this.service = service;
        this.created = created;
        this.updated = updated;
        this.userupdate = userupdate;
    }
    public OrderService(Long idorderservice, Order order, Service service, Date created, Date updated, String userupdate, Boolean active) {
       this.idorderservice = idorderservice;
       this.order = order;
       this.service = service;
       this.created = created;
       this.updated = updated;
       this.userupdate = userupdate;
       this.active = active;
    }
   
     @Id 

    
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

    
    @Column(name="active")
    public Boolean getActive() {
        return this.active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }




}


