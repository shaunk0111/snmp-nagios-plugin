package chemtech.snmp.models;

import java.util.Iterator;
import java.util.LinkedList;

public final class SnmpConsoleOutputModel {
	
	/**
	 * Build String with selected prefdata
	 * @param message
	 * @param prefdata
	 * @return
	 */
	static public String SelectPrefData(LinkedList<SnmpOidModel> prefdata) {
		
		// Build output String
		String output = " |";
		Iterator<SnmpOidModel> Iterator = prefdata.iterator();
        while (Iterator.hasNext()) {
        	SnmpOidModel oid = Iterator.next();
        	if (oid.getPrefdataStatus())
        	output = output + " " + oid.getSymbol() + "=" + oid.getValue();
        }
		return output;
	}
	
	/**
	 * Build String with all prefdata
	 * @param message
	 * @param prefdata
	 * @return
	 */
	static public String AllPrefData(LinkedList<SnmpOidModel> prefdata) {
		
		// Build output String
		String output = " |";
		Iterator<SnmpOidModel> Iterator = prefdata.iterator();
        while (Iterator.hasNext()) {
        	SnmpOidModel oid = Iterator.next();
        	output = output + " " + oid.getSymbol() + "=" + oid.getValue();
        }
		return output;
	}
	
	/**
	 * Build String with no prefdata
	 * @param message
	 * @return
	 */
	static public String NoPrefData(String message) {
		
		// Build output String
		String output = message;
		
		return output;
	}
	
	public static String getStatusString(String systemName, int status) {
		
		if (status == 0)
			return systemName + " OK";
		else if (status == 1)
			return systemName + " WARNING";
		else if (status == 2)
			return systemName + " CRITICAL";
		else if (status == 3)
			return systemName + " UNKNOWN";
		else
			return systemName + " UNKNOWN";
	}

}
