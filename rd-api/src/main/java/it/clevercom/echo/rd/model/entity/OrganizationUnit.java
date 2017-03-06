package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rd_organization_unit database table.
 * 
 */
@Entity
@Table(name="rd_organization_unit")
@NamedQuery(name="OrganizationUnit.findAll", query="SELECT o FROM OrganizationUnit o")
public class OrganizationUnit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idorganizationunit;

	private Boolean active;

	private String address;

	private String code;

	private Timestamp created;

	private String description;

	private String email;

	private String fax;

	private String name;

	private String telephone;

	private String type;

	private Timestamp updated;

	private String userupdate;

	private String website;

	//bi-directional many-to-one association to BurnRobot
	@OneToMany(mappedBy="rdOrganizationUnit")
	private List<BurnRobot> rdBurnRobots;

	//bi-directional many-to-one association to Modality
	@OneToMany(mappedBy="rdOrganizationUnit")
	private List<Modality> rdModalities;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="rdOrganizationUnit1")
	private List<Order> rdOrders1;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="rdOrganizationUnit2")
	private List<Order> rdOrders2;

	//bi-directional many-to-one association to Municipality
	@ManyToOne
	@JoinColumn(name="idmunicipality")
	private Municipality rdMunicipality;

	//bi-directional many-to-one association to OrganizationUnit
	@ManyToOne
	@JoinColumn(name="parentid")
	private OrganizationUnit rdOrganizationUnit;

	//bi-directional many-to-one association to OrganizationUnit
	@OneToMany(mappedBy="rdOrganizationUnit")
	private List<OrganizationUnit> rdOrganizationUnits;

	//bi-directional many-to-one association to Patient
	@OneToMany(mappedBy="rdOrganizationUnit1")
	private List<Patient> rdPatients1;

	//bi-directional many-to-one association to Patient
	@OneToMany(mappedBy="rdOrganizationUnit2")
	private List<Patient> rdPatients2;

	public OrganizationUnit() {
	}

	public Long getIdorganizationunit() {
		return this.idorganizationunit;
	}

	public void setIdorganizationunit(Long idorganizationunit) {
		this.idorganizationunit = idorganizationunit;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public List<BurnRobot> getRdBurnRobots() {
		return this.rdBurnRobots;
	}

	public void setRdBurnRobots(List<BurnRobot> rdBurnRobots) {
		this.rdBurnRobots = rdBurnRobots;
	}

	public BurnRobot addRdBurnRobot(BurnRobot rdBurnRobot) {
		getRdBurnRobots().add(rdBurnRobot);
		rdBurnRobot.setRdOrganizationUnit(this);

		return rdBurnRobot;
	}

	public BurnRobot removeRdBurnRobot(BurnRobot rdBurnRobot) {
		getRdBurnRobots().remove(rdBurnRobot);
		rdBurnRobot.setRdOrganizationUnit(null);

		return rdBurnRobot;
	}

	public List<Modality> getRdModalities() {
		return this.rdModalities;
	}

	public void setRdModalities(List<Modality> rdModalities) {
		this.rdModalities = rdModalities;
	}

	public Modality addRdModality(Modality rdModality) {
		getRdModalities().add(rdModality);
		rdModality.setRdOrganizationUnit(this);

		return rdModality;
	}

	public Modality removeRdModality(Modality rdModality) {
		getRdModalities().remove(rdModality);
		rdModality.setRdOrganizationUnit(null);

		return rdModality;
	}

	public List<Order> getRdOrders1() {
		return this.rdOrders1;
	}

	public void setRdOrders1(List<Order> rdOrders1) {
		this.rdOrders1 = rdOrders1;
	}

	public Order addRdOrders1(Order rdOrders1) {
		getRdOrders1().add(rdOrders1);
		rdOrders1.setRdOrganizationUnit1(this);

		return rdOrders1;
	}

	public Order removeRdOrders1(Order rdOrders1) {
		getRdOrders1().remove(rdOrders1);
		rdOrders1.setRdOrganizationUnit1(null);

		return rdOrders1;
	}

	public List<Order> getRdOrders2() {
		return this.rdOrders2;
	}

	public void setRdOrders2(List<Order> rdOrders2) {
		this.rdOrders2 = rdOrders2;
	}

	public Order addRdOrders2(Order rdOrders2) {
		getRdOrders2().add(rdOrders2);
		rdOrders2.setRdOrganizationUnit2(this);

		return rdOrders2;
	}

	public Order removeRdOrders2(Order rdOrders2) {
		getRdOrders2().remove(rdOrders2);
		rdOrders2.setRdOrganizationUnit2(null);

		return rdOrders2;
	}

	public Municipality getRdMunicipality() {
		return this.rdMunicipality;
	}

	public void setRdMunicipality(Municipality rdMunicipality) {
		this.rdMunicipality = rdMunicipality;
	}

	public OrganizationUnit getRdOrganizationUnit() {
		return this.rdOrganizationUnit;
	}

	public void setRdOrganizationUnit(OrganizationUnit rdOrganizationUnit) {
		this.rdOrganizationUnit = rdOrganizationUnit;
	}

	public List<OrganizationUnit> getRdOrganizationUnits() {
		return this.rdOrganizationUnits;
	}

	public void setRdOrganizationUnits(List<OrganizationUnit> rdOrganizationUnits) {
		this.rdOrganizationUnits = rdOrganizationUnits;
	}

	public OrganizationUnit addRdOrganizationUnit(OrganizationUnit rdOrganizationUnit) {
		getRdOrganizationUnits().add(rdOrganizationUnit);
		rdOrganizationUnit.setRdOrganizationUnit(this);

		return rdOrganizationUnit;
	}

	public OrganizationUnit removeRdOrganizationUnit(OrganizationUnit rdOrganizationUnit) {
		getRdOrganizationUnits().remove(rdOrganizationUnit);
		rdOrganizationUnit.setRdOrganizationUnit(null);

		return rdOrganizationUnit;
	}

	public List<Patient> getRdPatients1() {
		return this.rdPatients1;
	}

	public void setRdPatients1(List<Patient> rdPatients1) {
		this.rdPatients1 = rdPatients1;
	}

	public Patient addRdPatients1(Patient rdPatients1) {
		getRdPatients1().add(rdPatients1);
		rdPatients1.setRdOrganizationUnit1(this);

		return rdPatients1;
	}

	public Patient removeRdPatients1(Patient rdPatients1) {
		getRdPatients1().remove(rdPatients1);
		rdPatients1.setRdOrganizationUnit1(null);

		return rdPatients1;
	}

	public List<Patient> getRdPatients2() {
		return this.rdPatients2;
	}

	public void setRdPatients2(List<Patient> rdPatients2) {
		this.rdPatients2 = rdPatients2;
	}

	public Patient addRdPatients2(Patient rdPatients2) {
		getRdPatients2().add(rdPatients2);
		rdPatients2.setRdOrganizationUnit2(this);

		return rdPatients2;
	}

	public Patient removeRdPatients2(Patient rdPatients2) {
		getRdPatients2().remove(rdPatients2);
		rdPatients2.setRdOrganizationUnit2(null);

		return rdPatients2;
	}

}