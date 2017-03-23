package it.clevercom.echo.rd.model.jpa.helper;
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;
    public static String pattern = "(\\w+)(:|<|>|:>|<:|!)(\\w+)"; 
    
    public SearchCriteria(String key, String operation, Object value) {
		super();
		this.key = key;
		this.operation = operation;
		this.value = value;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}
	
	/**
	 * @param operation the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}
	
	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}
}