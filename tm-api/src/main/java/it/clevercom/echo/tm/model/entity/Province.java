package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the province database table.
 * 
 */
@Entity
@Table(name="province")
@NamedQuery(name="Province.findAll", query="SELECT p FROM Province p")
public class Province implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idProvince;

	private Object active;

	private Timestamp created;

	private String provinceName;

	private String provinceStdCode;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Municipality
	@OneToMany(mappedBy="province")
	private List<Municipality> municipalities;

	//bi-directional many-to-one association to Region
	@ManyToOne
	@JoinColumn(name="idRegion")
	private Region region;

	public Province() {
	}

	public int getIdProvince() {
		return this.idProvince;
	}

	public void setIdProvince(int idProvince) {
		this.idProvince = idProvince;
	}

	public Object getActive() {
		return this.active;
	}

	public void setActive(Object active) {
		this.active = active;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getProvinceStdCode() {
		return this.provinceStdCode;
	}

	public void setProvinceStdCode(String provinceStdCode) {
		this.provinceStdCode = provinceStdCode;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public List<Municipality> getMunicipalities() {
		return this.municipalities;
	}

	public void setMunicipalities(List<Municipality> municipalities) {
		this.municipalities = municipalities;
	}

	public Municipality addMunicipality(Municipality municipality) {
		getMunicipalities().add(municipality);
		municipality.setProvince(this);

		return municipality;
	}

	public Municipality removeMunicipality(Municipality municipality) {
		getMunicipalities().remove(municipality);
		municipality.setProvince(null);

		return municipality;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}