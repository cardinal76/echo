package it.clevercom.echo.rd.enums;

import java.util.HashMap;
import java.util.Map;

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
    private static Map<String, WorkStatusEnum> codeValueMap = new HashMap<String, WorkStatusEnum>(10);

    static
    {
        for (WorkStatusEnum  type : WorkStatusEnum.values())
        {
            codeValueMap.put(type.code, type);
        }
    }

    /**
     * @param code
     */
    WorkStatusEnum(String code) {
        this.code = code;
    }

    /**
     * @return
     */
    public String code() {
        return code;
    }

    /**
     * @param test
     * @return
     */
    public static boolean contains(String test) {
        for (WorkStatusEnum c : WorkStatusEnum.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @param codeValue
     * @return
     */
    public static WorkStatusEnum getInstanceFromCodeValue(String codeValue) {
        return codeValueMap.get(codeValue);
    }
    
    /**
     * @return
     */
    public static String enumValuesToString() {
    	String output = "[";
    	WorkStatusEnum[] array = WorkStatusEnum.class.getEnumConstants();
    	int i = 0;
    	for (WorkStatusEnum workStatusEnum : array) {
			output += workStatusEnum.toString() + ((i<=(array.length-2)) ? ", " : "");
			i++;
		}
    	output += "]";
    	return output;
    }
}