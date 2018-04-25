package chemtech.snmp.controllers;

import java.util.Iterator;
import java.util.LinkedList;

import chemtech.snmp.models.SnmpOidModel;

public interface SnmpConnectionController {
	
	/**
	 * Get Response from SNMP agent
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	public String getResponse(String oid) throws Exception;
	
	/**
	 * Push SNMP responses into SnmpOidModel list
	 * @param oidList
	 * @return
	 * @throws Exception
	 */
	public default void pushData(LinkedList<SnmpOidModel> oidList) throws Exception {
		
		Iterator<SnmpOidModel> Iterator = oidList.iterator();
        while (Iterator.hasNext()) {
        	SnmpOidModel oid = Iterator.next();
        	oid.setValue(getResponse(oid.getOid()));
        }
	}
	
	/**
	 * Push SNMP response into SnmpOidModel
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	public default void pushValue(SnmpOidModel oid) throws Exception {
        oid.setValue(getResponse(oid.getOid()));
	}

}
