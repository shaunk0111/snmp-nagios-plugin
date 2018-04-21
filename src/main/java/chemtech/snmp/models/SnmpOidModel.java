package chemtech.snmp.models;

public class SnmpOidModel {
		
	private String oid;
	private String name;
	private String type;
	private String symbol;
	private double lowerLimitWarning;
	private double lowerLimitCritical;
	private double upperLimitWarning;
	private double upperLimitCritical;
	private String value;
	private boolean prefdata;
	
	public SnmpOidModel(String oid, String name, String type, String symbol) {
		
		this.oid = oid;
		this.name = name;
		this.type = type;
		this.symbol = symbol;
	}
	
	/**
	 * Name of value
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * SNMP OID of value
	 * @return
	 */
	public String getOid() {
		return oid;
	}
	
	/**
	 * Value type
	 * @return
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Symbol tag
	 * @return
	 */
	public String getSymbol() {
		return symbol;
	}
	
	/**
	 * Upper limit warning
	 * @return
	 */
	public double getUpperLimitWarning() {
		return upperLimitWarning;
	}
	
	/**
	 * Upper limit critical
	 * @return
	 */
	public double getUpperLimitCritical() {
		return upperLimitCritical;
	}
	
	/**
	 * Lower limit warning
	 * @return
	 */
	public double getLowerLimitWarning() {
		return lowerLimitWarning;
	}
	
	/**
	 * Lower limit critical
	 * @return
	 */
	public double getLowerLimitCritical() {
		return lowerLimitCritical;
	}
	
	/**
	 * True value indicates to store performance data
	 * @return
	 */
	public boolean getPrefdataStatus() {
		return prefdata;
	}
	
	/**
	 * Upper limit warning
	 * @return
	 */
	public void setUpperLimitWarning(double upperLimitWarning) {
		this.upperLimitWarning = upperLimitWarning;
	}
	
	/**
	 * Upper limit critical
	 * @return
	 */
	public void setUpperLimitCritical(double upperLimitCritical) {
		this.upperLimitCritical = upperLimitCritical;
	}
	
	/**
	 * Lower limit warning
	 * @return
	 */
	public void setLowerLimitWarning(double lowerLimitWarning) {
		this.lowerLimitWarning = lowerLimitWarning;
	}
	
	/**
	 * Lower limit critical
	 * @return
	 */
	public void setLowerLimitCritical(double lowerLimitCritical) {
		this.lowerLimitCritical = lowerLimitCritical;
	}
	
	/**
	 * True value indicates to store performance data
	 * @return
	 */
	public void getPrefdataStatus(boolean prefdata) {
		this.prefdata = prefdata;
	}
	/**
	 * Value
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Set value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
}
