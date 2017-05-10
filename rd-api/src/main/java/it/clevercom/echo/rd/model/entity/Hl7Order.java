package it.clevercom.echo.rd.model.entity;
// Generated 10-mag-2017 16.45.35 by Hibernate Tools 5.2.2.Final


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import it.clevercom.echo.common.jpa.entity.AbstractJpaEchoEntity;

/**
 * Hl7Order generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_hl7_order"
)
public class Hl7Order  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private Long idhl7order;

    public Hl7Order() {
    }

    public Hl7Order(Long idhl7order) {
       this.idhl7order = idhl7order;
    }
   
     @Id 

    
    @Column(name="idhl7order", unique=true, nullable=false)
    public Long getIdhl7order() {
        return this.idhl7order;
    }
    
    public void setIdhl7order(Long idhl7order) {
        this.idhl7order = idhl7order;
    }




}


