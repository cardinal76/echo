package it.clevercom.echo.tm.model.entity.gateway;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.BitSet;

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

/**
 * @author gfares	
 * @since 0.1
 *
 *        This class rapresents the shared information of any data sent from the
 *        gateway.
 */

/**
 * @author gfares
 *
 */
@Entity
@Table(name = "tm_gateway_event")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "devicetype", discriminatorType = DiscriminatorType.STRING, length = 2)
public class BaseGatewayEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final DateTimeFormatter ISO_UTC_DATE_TIME = new DateTimeFormatterBuilder().parseCaseInsensitive()
			.append(DateTimeFormatter.ISO_LOCAL_DATE).appendLiteral('T').append(DateTimeFormatter.ISO_LOCAL_TIME)
			.appendLiteral('Z').toFormatter();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEvent")
	private Long id;

	@Column(name = "pheripheralmac", nullable = false)
	private String pheripheral;

	@Column(name = "gatewayserialnumber", nullable = false)
	private String gatewaySerialNumber;

	@Column(name = "receiveddate", nullable = false)
	private LocalDateTime receivedDate;

	@Column(name = "timestamp", nullable = false)
	private LocalDateTime timeStamp;

	public BaseGatewayEntity() {
	}

	@PrePersist
	public void onPrePersist() {
		receivedDate = LocalDateTime.now();// new
											// Date(System.currentTimeMillis());
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @param pheripheral
	 *            the pheripheral to set
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
	 * @param gatewaySerialNumber
	 *            the gatewaySerialNumber to set
	 */
	public void setGatewaySerialNumber(String gatewaySerialNumber) {
		this.gatewaySerialNumber = gatewaySerialNumber;
	}

	/**
	 * @return the riceivedDate
	 */
	public LocalDateTime getRiceivedDate() {
		return receivedDate;
	}

	/**
	 * @param riceivedDate
	 *            the riceivedDate to set
	 */
	public void setRiceivedDate(LocalDateTime riceivedDate) {
		this.receivedDate = riceivedDate;
	}

	/**
	 * @return the timeStamp
	 */
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp
	 *            the timeStamp to set
	 */
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @param timeStamp
	 *            The string must be ISO_8601
	 * 
	 *            The gateway send an UTC timestamp as 2017-04-22T11:32:01.355932Z
	 *            so provide a string setter
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = LocalDateTime.parse(timeStamp, ISO_UTC_DATE_TIME);
	}

	/**
	 * @param l
	 *            The long to convert to a {@link BitSet}
	 * @return The bitset
	 */
	protected static BitSet convertToBitSet(Long l) {
		BitSet bs = new BitSet(16);
		long v = l != null ? l.longValue() : 0;
		int idx = 0;
		while (v != 0) {
			if (v % 2L != 0) {
				bs.set(idx);
			}
			++idx;
			v = v >>> 1;
		}
		return bs;
	}
}
