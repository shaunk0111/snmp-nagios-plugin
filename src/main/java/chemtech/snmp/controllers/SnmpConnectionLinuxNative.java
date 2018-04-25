package chemtech.snmp.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

import chemtech.snmp.models.SnmpConfigModel;
import chemtech.snmp.models.SnmpOidModel;

public class SnmpConnectionLinuxNative implements SnmpConnectionController {

	SnmpConfigModel config;
	
	SnmpConnectionLinuxNative(SnmpConfigModel config) {
		this.config = config;
	}
	
	@Override
	public String getResponse(String oid) throws Exception {
		
		Process process;
		BufferedReader reader;
		StringBuffer output = new StringBuffer();
		
		// Execute native snmpwalk
		process = Runtime.getRuntime().exec("snmpwalk -v 2c " + oid);
		process.wait(1000);
		reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String line = "";
		while ((line = reader.readLine())!=null) {
			output.append(line + "\n");
		}
		
		return output.toString();
	}

}
