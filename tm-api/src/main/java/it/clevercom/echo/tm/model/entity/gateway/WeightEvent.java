package it.clevercom.echo.tm.model.entity.gateway;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author gfares
 * @since 0.1
 * 
 *        This class rapresents data sent from a weight device
 */

@Entity
@Table(name = "tm_gateway_event_weight")
@DiscriminatorValue(value = "we")
public class WeightEvent extends BaseGatewayEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	

	@Column(name = "bmi", nullable = false)
	private Long bmi;

	@Column(name = "weight", nullable = false)
	private Double weight;

	@Column(name = "height", nullable = false)
	private Double height;

	@Column(name="uom", nullable=false, length=10)
	private String uom;
	
	public WeightEvent() {
	}

	/**
	 * @return the bmi
	 */
	public Long getBmi() {
		return bmi;
	}

	/**
	 * @param bmi the bmi to set
	 */
	public void setBmi(Long bmi) {
		this.bmi = bmi;
	}

	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/**
	 * @return the height
	 */
	public Double getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(Double height) {
		this.height = height;
	}

	/**
	 * @return the uom
	 */
	public String getUom() {
		return uom;
	}

	/**
	 * @param uom the uom to set
	 */
	public void setUom(String uom) {
		this.uom = uom;
	}

}
