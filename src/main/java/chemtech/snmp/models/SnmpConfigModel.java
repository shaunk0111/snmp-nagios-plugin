package chemtech.snmp.models;

public class SnmpConfigModel {
	
	/**
	 * IP Address
	 */
	private String  ipAddress;
	/**
	 * Port Number
	 */
	private String  port;
	/**
	 * SNMP Version
	 */
	private int version;
	/**
	 * SMNP Community Type
	 */
	private String  community;
	/**
	 * Connection retires
	 */
	private int retries;
	/**
	 * Connection timeout
	 */
	private int timeout;
	
	
	/**
	 * Build SNMP config
	 * @param ipAddress
	 * @param port
	 * @param version
	 * @param community
	 * @param retries
	 * @param timeout
	 */
	public SnmpConfigModel(
			String ipAddress, 
			String port, 
			int version, 
			String community, 
			int retries, 
			int timeout){
		
			this.ipAddress = ipAddress;
			this.port = port;
			this.version = version;
			this.community = community;
			this.retries = retries;
			this.timeout = timeout;
	}

	/**
	 * Return SNMP Connection IP
	 * @return
	 */
	public String getIP(){
		return ipAddress;
	}

	/**
	 * Return SNMP Connection Port
	 * @return
	 */
	public String getPort() {
		return port;
	}

	/**
	 * Return SNMP Version
	 * @return
	 */
	public int getVersion(){
		return version;
	}
	
	/**
	 *  Return SNMP community type
	 * @return
	 */
	public String getCommunity() {
		return community;
	}
	
	/**
	 * Return connection retries
	 * @return
	 */
	public int getRetries() {
		return retries;
	}
	
	/**
	 * Return connection timeout
	 * @return
	 */
	public int getTimeout() {
		return timeout;
	}

}
