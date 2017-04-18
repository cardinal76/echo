package it.clevercom.echo.tm.model.entity.gateway;

import java.util.BitSet;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * @author gfares
 * @since 0.1
 * 
 *        This class rapresents data sent from a blood pressure device
 */

@Entity
@Table(name = "tm_event_blood_pressure")
@DiscriminatorValue(value = "bp")
public class BloodPressureEvent extends BaseGatewayEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static int BIT_BODY_MOVEMENT = 0;
	private static int BIT_CUFF_TOO_LOSE = 1;
	private static int BIT_IRREGULAR_PULSE = 2;
	private static int BIT_PULSE_RANGE_1 = 3;
	private static int BIT_PULSE_RANGE_2 = 4;
	private static int BIT_IMPROPER_MEASURE_POSITION = 5;

	@Column(name = "systolicpressure", nullable = false)
	private Long systolicPressure;

	@Column(name = "diastolicpressure", nullable = false)
	private Long diastolicPressure;

	@Column(name = "arterialpressure", nullable = false)
	private Long arterialPressure;

	@Column(name = "pulserate", nullable = false)
	private Long pulseRate;

	@Column(name = "measurestatus", nullable = false)
	private Long measureStatus;

	private BitSet statusFlags;

	public BloodPressureEvent() {
	}

	/**
	 * Create the BitSet class to explore the measure status
	 */
	@PostLoad
	public void onPostLoad() {
		statusFlags = convertToBitSet(measureStatus);
	}

	@PrePersist
	@Override
	public void onPrePersist() {
		super.onPrePersist();
	}
	
	/**
	 * @return the systolicPressure
	 */
	public Long getSystolicPressure() {
		return systolicPressure;
	}

	/**
	 * @param systolicPressure
	 *            the systolicPressure to set
	 */
	public void setSystolicPressure(Long systolicPressure) {
		this.systolicPressure = systolicPressure;
	}

	/**
	 * @return the diastolicPressure
	 */
	public Long getDiastolicPressure() {
		return diastolicPressure;
	}

	/**
	 * @param diastolicPressure
	 *            the diastolicPressure to set
	 */
	public void setDiastolicPressure(Long diastolicPressure) {
		this.diastolicPressure = diastolicPressure;
	}

	/**
	 * @return the arterialPressure
	 */
	public Long getArterialPressure() {
		return arterialPressure;
	}

	/**
	 * @param arterialPressure
	 *            the arterialPressure to set
	 */
	public void setArterialPressure(Long arterialPressure) {
		this.arterialPressure = arterialPressure;
	}

	/**
	 * @return the pulseRate
	 */
	public Long getPulseRate() {
		return pulseRate;
	}

	/**
	 * @param pulseRate
	 *            the pulseRate to set
	 */
	public void setPulseRate(Long pulseRate) {
		this.pulseRate = pulseRate;
	}

	/**
	 * @return the measureStatus
	 */
	public Long getMeasureStatus() {
		return measureStatus;
	}

	/**
	 * @param measureStatus
	 *            the measureStatus to set
	 */
	public void setMeasureStatus(Long measureStatus) {
		this.measureStatus = measureStatus;
	}

	public boolean isBodyMovement() {
		return statusFlags.get(BIT_BODY_MOVEMENT);
	}

	public boolean isCuffTooLose() {
		return statusFlags.get(BIT_CUFF_TOO_LOSE);
	}

	public boolean isIrregularPulse() {
		return statusFlags.get(BIT_IRREGULAR_PULSE);
	}

	public boolean isPulseRateInRange() {
		return !statusFlags.get(BIT_PULSE_RANGE_1) && !statusFlags.get(BIT_PULSE_RANGE_2);
	}
	
	public boolean isPulseRateHigher() {
		return statusFlags.get(BIT_PULSE_RANGE_1) && !statusFlags.get(BIT_PULSE_RANGE_2);
	}

	public boolean isPulseRateLower() {
		return !statusFlags.get(BIT_PULSE_RANGE_1) && statusFlags.get(BIT_PULSE_RANGE_2);
	}
	
	public boolean isImproperMeasurePosition() {
		return statusFlags.get(BIT_IMPROPER_MEASURE_POSITION);
	}
}
