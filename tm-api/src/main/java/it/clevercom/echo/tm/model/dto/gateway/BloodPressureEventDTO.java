package it.clevercom.echo.tm.model.dto.gateway;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"created","updated","userupdate","active", "idd"})
public class BloodPressureEventDTO extends BaseGatewayEntityDTO implements Serializable {
	private static final long serialVersionUID = -3262759467412065437L;
	
	private Long idbloodpressure;
	private Long systolicPressure;
	private Long diastolicPressure;
	private Long arterialPressure;
	private Long pulseRate;
	private Long measureStatus;
	private String uom;
	private String deviceType= "bp";
	private Date created;
	private Date updated;
	private String userupdate;
	private Boolean active;
	
	/**
	 * @return the idbloodpressure
	 */
	public Long getIdbloodpressure() {
		return idbloodpressure;
	}

	/**
	 * @param idbloodpressure the idbloodpressure to set
	 */
	public void setIdbloodpressure(Long idbloodpressure) {
		this.idbloodpressure = idbloodpressure;
	}

	/**
	 * @return the systolicPressure
	 */
	public Long getSystolicPressure() {
		return systolicPressure;
	}
	
	/**
	 * @param systolicPressure the systolicPressure to set
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
	 * @param diastolicPressure the diastolicPressure to set
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
	 * @param arterialPressure the arterialPressure to set
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
	 * @param pulseRate the pulseRate to set
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
	 * @param measureStatus the measureStatus to set
	 */
	public void setMeasureStatus(Long measureStatus) {
		this.measureStatus = measureStatus;
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

	/**
	 * @return the deviceType
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(String deviceType) {
		super.setDeviceType("bp");
	}
	
	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the updated
	 */
	public Date getUpdated() {
		return updated;
	}

	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	/**
	 * @return the userupdate
	 */
	public String getUserupdate() {
		return userupdate;
	}

	/**
	 * @param userupdate the userupdate to set
	 */
	public void setUserupdate(String userupdate) {
		this.userupdate = userupdate;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
