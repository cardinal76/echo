package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rd_province database table.
 * 
 */
@Entity
@Table(name="rd_province")
@NamedQuery(name="Province.findAll", query="SELECT p FROM Province p")
public class Province implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idprovince;

	private Boolean active;

	private Timestamp created;

	private String provincename;

	private String provincestdcode;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to Municipality
	@OneToMany(mappedBy="rdProvince")
	private List<Municipality> rdMunicipalities;

	//bi-directional many-to-one association to Region
	@ManyToOne
	@JoinColumn(name="idregion")
	private Region rdRegion;

	public Province() {
	}

	public Long getIdprovince() {
		return this.idprovince;
	}

	public void setIdprovince(Long idprovince) {
		this.idprovince = idprovince;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getProvincename() {
		return this.provincename;
	}

	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}

	public String getProvincestdcode() {
		return this.provincestdcode;
	}

	public void setProvincestdcode(String provincestdcode) {
		this.provincestdcode = provincestdcode;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public String getUserupdate() {
		return this.userupdate;
	}

	public void setUserupdate(String userupdate) {
		this.userupdate = userupdate;
	}

	public List<Municipality> getRdMunicipalities() {
		return this.rdMunicipalities;
	}

	public void setRdMunicipalities(List<Municipality> rdMunicipalities) {
		this.rdMunicipalities = rdMunicipalities;
	}

	public Municipality addRdMunicipality(Municipality rdMunicipality) {
		getRdMunicipalities().add(rdMunicipality);
		rdMunicipality.setRdProvince(this);

		return rdMunicipality;
	}

	public Municipality removeRdMunicipality(Municipality rdMunicipality) {
		getRdMunicipalities().remove(rdMunicipality);
		rdMunicipality.setRdProvince(null);

		return rdMunicipality;
	}

	public Region getRdRegion() {
		return this.rdRegion;
	}

	public void setRdRegion(Region rdRegion) {
		this.rdRegion = rdRegion;
	}

}