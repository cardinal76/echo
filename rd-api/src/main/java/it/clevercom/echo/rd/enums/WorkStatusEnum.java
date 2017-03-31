package it.clevercom.echo.rd.enums;

public enum WorkStatusEnum {
	
    REQUESTED("REQ"),
    SCHEDULED("SCH"),
    ACCEPTED("ACC"),
    EXECUTED("EXE"),
    REPORTED("REP"),
    VALIDATED("VAL"),
    SIGNED("SIG"),
    DELIVERED("DEL"),
    ARCHIVED("ARC"),
    CANCELED("CAN");
	
    private String code;

    WorkStatusEnum(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
    
    public static boolean contains(String test) {
        for (WorkStatusEnum c : WorkStatusEnum.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }

        return false;
    }
    
    public static String enumValuesToString() {
    	String output = "[";
    	WorkStatusEnum[] array = WorkStatusEnum.class.getEnumConstants();
    	for (WorkStatusEnum workStatusEnum : array) {
			output += workStatusEnum.toString() + ", ";
		}
    	output += "]";
    	return output;
    }
}