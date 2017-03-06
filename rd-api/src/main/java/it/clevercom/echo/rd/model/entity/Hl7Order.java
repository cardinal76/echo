package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rd_hl7_order database table.
 * 
 */
@Entity
@Table(name="rd_hl7_order")
@NamedQuery(name="Hl7Order.findAll", query="SELECT h FROM Hl7Order h")
public class Hl7Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idhl7order;

	public Hl7Order() {
	}

	public Long getIdhl7order() {
		return this.idhl7order;
	}

	public void setIdhl7order(Long idhl7order) {
		this.idhl7order = idhl7order;
	}

}