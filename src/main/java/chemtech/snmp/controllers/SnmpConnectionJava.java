package chemtech.snmp.controllers;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.SecurityModel;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import chemtech.snmp.models.SnmpConfigModel;
import chemtech.snmp.models.SnmpOidModel;

public class SnmpConnectionJava implements SnmpConnectionController {
	
	SnmpConfigModel config;
	Snmp snmp;
	PDU pdu;
	CommunityTarget comtarget;
	TransportMapping<UdpAddress> transport;

	/**
	 * Build SNMP Controller with assigned configuration
	 * @param config
	 */
	public SnmpConnectionJava(SnmpConfigModel config) {
		this.config = config;
	}
	
	/**
	 * Create SNMP client agent
	 * @throws Exception
	 */
	public void createConnection() throws Exception {
		
		transport = new DefaultUdpTransportMapping();
		transport.listen();
	    comtarget = new CommunityTarget();
	    comtarget.setCommunity(new OctetString(config.getCommunity()));
	    comtarget.setVersion(config.getVersion());
	    comtarget.setAddress(new UdpAddress(config.getIP() + "/" + config.getPort()));
	    comtarget.setRetries(2);
	    comtarget.setTimeout(1000);
	    comtarget.setSecurityLevel(SecurityLevel.NOAUTH_NOPRIV);
	    comtarget.setSecurityModel(SecurityModel.SECURITY_MODEL_SNMPv2c);
	    snmp = new Snmp(transport);
	}
	
	/**
	 * Close SNMP connection
	 * @throws IOException
	 */
	public void closeConnection() throws Exception {
		snmp.close();
	}
	
	/**
	 * Get Response from SNMP agent
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	@Override
	public String getResponse(String oid) throws Exception {
			    
	    int errorStatus = 0;
        String errorStatusText = null;
	  
	    // Create the PDU object
	    PDU pdu = new PDU();
	    pdu.add(new VariableBinding(new OID(oid)));
	    pdu.setType(PDU.GET);
	    pdu.setRequestID(new Integer32(1));
	    ResponseEvent response = snmp.get(pdu, comtarget);

	    // Get Agent Response
	    if (response != null) {
	    	PDU responsePDU = response.getResponse();
	    	if (responsePDU != null) {
	    		errorStatusText = responsePDU.getErrorStatusText();
	    		
	    		// Get response
	    		if (errorStatus == PDU.noError) 
	    			return String.valueOf(responsePDU.getVariable(new OID(oid)));
	    		// PDU returned error
	    		else 
	    			throw new Exception("Error: PDU error code " + errorStatusText);
	    			    		
	    	// PDU response Empty
	    	} else  
	    		throw new Exception("Error: PDU Empty");
	    	
	    // Agent response timed out  
	    } else 
	    	throw new Exception("Error: Response Timed Out");	    
	}

}
