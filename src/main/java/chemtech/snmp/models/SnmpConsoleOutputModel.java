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
	static public String SelectPrefData(String message, 
			LinkedList<SnmpOidModel> prefdata) {
		
		// Build output String
		String output = message + " |";
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
	static public String AllPrefData(String message, 
			LinkedList<SnmpOidModel> prefdata) {
		
		// Build output String
		String output = message + " |";
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

}
