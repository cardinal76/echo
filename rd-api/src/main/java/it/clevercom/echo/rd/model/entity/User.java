package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rd_user database table.
 * 
 */
@Entity
@Table(name="rd_user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long iduser;

	private Boolean active;

	private String alternativephonenumber;

	@Temporal(TemporalType.DATE)
	private Date birthdate;

	private Timestamp created;

	private String email;

	private String gender;

	private String name;

	private String phonenumber;

	private String surname;

	private String taxcode;

	private String type;

	private Timestamp updated;

	private String username;

	private String userupdate;

	//bi-directional many-to-one association to AppSetting
	@OneToMany(mappedBy="rdUser")
	private List<AppSetting> rdAppSettings;

	//bi-directional many-to-one association to PhraseBook
	@OneToMany(mappedBy="rdUser")
	private List<PhraseBook> rdPhraseBooks;

	//bi-directional many-to-one association to WorkReportUser
	@OneToMany(mappedBy="rdUser")
	private List<WorkReportUser> rdWorkReportUsers;

	//bi-directional many-to-one association to WorkTask
	@OneToMany(mappedBy="rdUser")
	private List<WorkTask> rdWorkTasks;

	public User() {
	}

	public Long getIduser() {
		return this.iduser;
	}

	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getAlternativephonenumber() {
		return this.alternativephonenumber;
	}

	public void setAlternativephonenumber(String alternativephonenumber) {
		this.alternativephonenumber = alternativephonenumber;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTaxcode() {
		return this.taxcode;
	}

	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserupdate() {
		return this.userupdate;
	}

	public void setUserupdate(String userupdate) {
		this.userupdate = userupdate;
	}

	public List<AppSetting> getRdAppSettings() {
		return this.rdAppSettings;
	}

	public void setRdAppSettings(List<AppSetting> rdAppSettings) {
		this.rdAppSettings = rdAppSettings;
	}

	public AppSetting addRdAppSetting(AppSetting rdAppSetting) {
		getRdAppSettings().add(rdAppSetting);
		rdAppSetting.setRdUser(this);

		return rdAppSetting;
	}

	public AppSetting removeRdAppSetting(AppSetting rdAppSetting) {
		getRdAppSettings().remove(rdAppSetting);
		rdAppSetting.setRdUser(null);

		return rdAppSetting;
	}

	public List<PhraseBook> getRdPhraseBooks() {
		return this.rdPhraseBooks;
	}

	public void setRdPhraseBooks(List<PhraseBook> rdPhraseBooks) {
		this.rdPhraseBooks = rdPhraseBooks;
	}

	public PhraseBook addRdPhraseBook(PhraseBook rdPhraseBook) {
		getRdPhraseBooks().add(rdPhraseBook);
		rdPhraseBook.setRdUser(this);

		return rdPhraseBook;
	}

	public PhraseBook removeRdPhraseBook(PhraseBook rdPhraseBook) {
		getRdPhraseBooks().remove(rdPhraseBook);
		rdPhraseBook.setRdUser(null);

		return rdPhraseBook;
	}

	public List<WorkReportUser> getRdWorkReportUsers() {
		return this.rdWorkReportUsers;
	}

	public void setRdWorkReportUsers(List<WorkReportUser> rdWorkReportUsers) {
		this.rdWorkReportUsers = rdWorkReportUsers;
	}

	public WorkReportUser addRdWorkReportUser(WorkReportUser rdWorkReportUser) {
		getRdWorkReportUsers().add(rdWorkReportUser);
		rdWorkReportUser.setRdUser(this);

		return rdWorkReportUser;
	}

	public WorkReportUser removeRdWorkReportUser(WorkReportUser rdWorkReportUser) {
		getRdWorkReportUsers().remove(rdWorkReportUser);
		rdWorkReportUser.setRdUser(null);

		return rdWorkReportUser;
	}

	public List<WorkTask> getRdWorkTasks() {
		return this.rdWorkTasks;
	}

	public void setRdWorkTasks(List<WorkTask> rdWorkTasks) {
		this.rdWorkTasks = rdWorkTasks;
	}

	public WorkTask addRdWorkTask(WorkTask rdWorkTask) {
		getRdWorkTasks().add(rdWorkTask);
		rdWorkTask.setRdUser(this);

		return rdWorkTask;
	}

	public WorkTask removeRdWorkTask(WorkTask rdWorkTask) {
		getRdWorkTasks().remove(rdWorkTask);
		rdWorkTask.setRdUser(null);

		return rdWorkTask;
	}

}