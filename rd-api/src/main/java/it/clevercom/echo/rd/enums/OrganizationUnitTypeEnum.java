package it.clevercom.echo.rd.enums;

import java.util.HashMap;
import java.util.Map;

public enum OrganizationUnitTypeEnum {
	HEADQUARTER("HQ"),
	DEPARTMENT("DPT"),
	OPERATION_UNIT("OPU"),
	WARD("WAR"),
	EXTERNAL("EXT");
	
	private String code;
    private static Map<String, OrganizationUnitTypeEnum> codeValueMap = new HashMap<String, OrganizationUnitTypeEnum>(5);
    
    static
    {
        for (OrganizationUnitTypeEnum  type : OrganizationUnitTypeEnum.values())
        {
            codeValueMap.put(type.code, type);
        }
    }
    
    /**
     * @param code
     */
    OrganizationUnitTypeEnum(String code) {
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
        for (OrganizationUnitTypeEnum c : OrganizationUnitTypeEnum.values()) {
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
    public static OrganizationUnitTypeEnum getInstanceFromCodeValue(String codeValue) {
        return codeValueMap.get(codeValue);
    }
    
    /**
     * @return
     */
    public static String enumValuesToString() {
    	String output = "";
    	OrganizationUnitTypeEnum[] array = OrganizationUnitTypeEnum.class.getEnumConstants();
    	int i = 0;
    	for (OrganizationUnitTypeEnum organizationUnitEnum : array) {
			output += organizationUnitEnum.code + "[" + organizationUnitEnum.toString() + "]" + ((i<=(array.length-2)) ? ", " : "");
			i++;
		}
    	return output;
    }
}
