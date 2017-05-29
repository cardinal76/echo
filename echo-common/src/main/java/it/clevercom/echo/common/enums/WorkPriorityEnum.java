package it.clevercom.echo.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum WorkPriorityEnum {
	
	DEFERRABLE("DEF"),
    LOW("LOW"),
    HIGH("HIG"),
    NORMAL("NOR");

    private String code;
    private static Map<String, WorkPriorityEnum> codeValueMap = new HashMap<String, WorkPriorityEnum>(4);
    
    static
    {
        for (WorkPriorityEnum  type : WorkPriorityEnum.values())
        {
            codeValueMap.put(type.code, type);
        }
    }
    
    /**
     * @param code
     */
    WorkPriorityEnum(String code) {
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
        for (WorkPriorityEnum c : WorkPriorityEnum.values()) {
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
    public static WorkPriorityEnum getInstanceFromCodeValue(String codeValue) {
        return codeValueMap.get(codeValue);
    }
    
    /**
     * @return
     */
    public static String enumValuesToString() {
    	String output = "";
    	WorkPriorityEnum[] array = WorkPriorityEnum.class.getEnumConstants();
    	int i = 0;
    	for (WorkPriorityEnum workPriorityEnum : array) {
			output += workPriorityEnum.code + "[" + workPriorityEnum.toString() + "]" + ((i<=(array.length-2)) ? ", " : "");
			i++;
		}
    	return output;
    }
}