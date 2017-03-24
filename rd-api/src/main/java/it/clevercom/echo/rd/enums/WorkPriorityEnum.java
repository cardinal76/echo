package it.clevercom.echo.rd.enums;

public enum WorkPriorityEnum {
	
	DEFERRABLE("DEF"),
    LOW("LOW"),
    HIGH("HIG"),
    NORMAL("NOR");

    private String code;

    WorkPriorityEnum(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}