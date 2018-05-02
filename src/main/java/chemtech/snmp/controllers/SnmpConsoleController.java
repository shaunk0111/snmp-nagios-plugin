package chemtech.snmp.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import chemtech.snmp.models.SnmpConsoleOutputModel;
import chemtech.snmp.models.SnmpOidModel;

public class SnmpConsoleController {
	
	private SnmpDataController dataController;
	private String systemName;
	
	public SnmpConsoleController(SnmpConnectionController connectionController, 
			SnmpDataController dataController, String systemName) {

		this.dataController = dataController;
		this.systemName = systemName;
	}
	
	
	public void printOutputandExit(String system) {
		
		String alarms = "";
		
		Iterator<SnmpOidModel> Iterator = dataController.getData().iterator();
		List<Integer> states = new ArrayList<Integer>();
        while (Iterator.hasNext()) {
        	SnmpOidModel oid = Iterator.next();
        	states.add(oid.getState());
        	
        	if (oid.getState() > 0) {
        		alarms = alarms + " - " + oid.getName() + " = " + oid.getValue() + oid.getType();
        	}
        	
        }
        
        int maxState = Collections.max(states); // Find highest state
        		
		try {
			
			System.out.println(SnmpConsoleOutputModel.getStatusString(systemName,maxState) +
					alarms +
					SnmpConsoleOutputModel.AllPrefData(dataController.getData()));
			
			System.exit(maxState); // Service status OK

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
