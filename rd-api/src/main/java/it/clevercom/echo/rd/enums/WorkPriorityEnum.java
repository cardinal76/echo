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
    
    public static boolean contains(String test) {
        for (WorkPriorityEnum c : WorkPriorityEnum.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }

        return false;
    }
    
    public static String enumValuesToString() {
    	String output = "[";
    	WorkPriorityEnum[] array = WorkPriorityEnum.class.getEnumConstants();
    	for (WorkPriorityEnum workPriorityEnum : array) {
			output += workPriorityEnum.toString() + ", ";
		}
    	output += "]";
    	return output;
    }
}