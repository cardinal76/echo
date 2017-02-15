package it.clevercom.echo.common.enums;

public enum ApplicationEnum {

	TM("TM", "SISTEMA DI TELEMEDICINA"),
	RD("RD", "SISTEMA INFORMATICO RADIOLOGICO");

	private String code;
	private String descritpion;


	private ApplicationEnum(String code, String descritpion) {
		this.code = code;
		this.descritpion = descritpion;
	}


	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescritpion() {
		return descritpion;
	}
	public void setDescritpion(String descritpion) {
		this.descritpion = descritpion;
	}

	public static ApplicationEnum getByCode(String code) {
		for (ApplicationEnum value : ApplicationEnum.values()) {
			if (value.code.equals(code)) return value;
		}
		return null;
	}
}
