package it.clevercom.echo.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum WorkStatusEnum {
	
    REQUESTED("REQ",1),
    SCHEDULED("SCH",2),
    ACCEPTED("ACC",3),
    EXECUTING("EXE1",4),
    EXECUTED("EXE2",5),
    REPORTING("REP1",6),
    REPORTED("REP2",7),
    SIGNED("SIG",8),
    DELIVERED("DEL",9),
    ARCHIVED("ARC",10),
    CANCELED("CAN",11);
	
	private String code;
	private int order;
    private static Map<String, WorkStatusEnum> codeValueMap = new HashMap<String, WorkStatusEnum>(11);

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
    WorkStatusEnum(String code,int order) {
        this.code = code;
        this.order = order;
    }

    /**
     * @return
     */
    public String code() {
        return code;
    }
    
    /**
     * @return
     */
    public int order() {
        return order;
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
    	String output = "";
    	WorkStatusEnum[] array = WorkStatusEnum.class.getEnumConstants();
    	int i = 0;
    	for (WorkStatusEnum workStatusEnum : array) {
			output += workStatusEnum.code + "[" + workStatusEnum.toString() + "]" + ((i<=(array.length-2)) ? ", " : "");
			i++;
		}
    	return output;
    }
}