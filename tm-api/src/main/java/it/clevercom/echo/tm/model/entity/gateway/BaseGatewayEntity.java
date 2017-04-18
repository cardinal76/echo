package it.clevercom.echo.tm.model.entity.gateway;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author gfares
 * @since 0.1
 *
 *        This class rapresents the shared information of any data sent from the
 *        gateway.
 */

@Entity
@Table(name = "tm_gateway_events")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "devicetype", discriminatorType = DiscriminatorType.STRING, length = 2)
public class BaseGatewayEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idEvent")
	private Long id;

	@Column(name = "pheripheralmac", nullable = false)
	private String pheripheral;

	@Column(name = "gatewayserialnumber", nullable = false)
	private String gatewaySerialNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "receiveddate", nullable = false)
	private Date receivedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timestamp", nullable = false)
	private Date timeStamp;

	public BaseGatewayEntity() {
	}

	@PrePersist
	public void onPrePersist() {
		receivedDate = new Date(System.currentTimeMillis());
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the pheripheral
	 */
	public String getPheripheral() {
		return pheripheral;
	}

	/**
	 * @param pheripheral the pheripheral to set
	 */
	public void setPheripheral(String pheripheral) {
		this.pheripheral = pheripheral;
	}

	/**
	 * @return the gatewaySerialNumber
	 */
	public String getGatewaySerialNumber() {
		return gatewaySerialNumber;
	}

	/**
	 * @param gatewaySerialNumber the gatewaySerialNumber to set
	 */
	public void setGatewaySerialNumber(String gatewaySerialNumber) {
		this.gatewaySerialNumber = gatewaySerialNumber;
	}

	/**
	 * @return the riceivedDate
	 */
	public Date getRiceivedDate() {
		return receivedDate;
	}

	/**
	 * @param riceivedDate the riceivedDate to set
	 */
	public void setRiceivedDate(Date riceivedDate) {
		this.receivedDate = riceivedDate;
	}

	/**
	 * @return the timeStamp
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	protected static BitSet convertToBitSet(Long l) {
		BitSet bs = new BitSet(16);
		long v = l != null ? l.longValue() : 0;
		int idx = 0;
		while(v != 0) {
			if(v % 2L != 0) {
				bs.set(idx);
			}
			++idx;
			v = v >>> 1;
		}
		return bs;
	}
}
