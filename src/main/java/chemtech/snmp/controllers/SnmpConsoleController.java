package chemtech.snmp.controllers;

import java.util.Collections;
import java.util.List;

import chemtech.snmp.models.SnmpConsoleOutputModel;

public class SnmpConsoleController {
	
	private SnmpConnectionContoller connectionController;
	private SnmpDataController dataController;
	
	public SnmpConsoleController(SnmpConnectionContoller connectionController, 
			SnmpDataController dataController) {
		
		this.connectionController = connectionController;
		this.dataController = dataController;
	}
	
	
	public void printOutputandExit(List<Integer> states) {
		
		// Find highest state
		int maxState = Collections.max(states);
		
		try {
			
			// Print console and exit
			switch (maxState) {
			
			    case 0:  {
			    	System.out.println(SnmpConsoleOutputModel.AllPrefData(
			    			"System OK", dataController.getData()));
					connectionController.closeConnection();
			        System.exit(0); // Service status OK
			    }   
			    case 1:  {
			    	System.out.println(SnmpConsoleOutputModel.AllPrefData(
			    			"System Warning", dataController.getData()));
					connectionController.closeConnection();
			        System.exit(1); // Service status Warning
			    }   
			    case 2: {
			    	System.out.println(SnmpConsoleOutputModel.AllPrefData(
			    			"System Critical", dataController.getData()));
					connectionController.closeConnection();
			        System.exit(2); // Service status Critical
			    }
			    case 3: {
			    	System.out.println(SnmpConsoleOutputModel.AllPrefData(
			    			"System Unknown", dataController.getData()));
					connectionController.closeConnection();
			        System.exit(3); // Service status Unknown
			    }
			    default: {
			    	System.out.println(SnmpConsoleOutputModel.AllPrefData(
			    			"System Unknown", dataController.getData()));
					connectionController.closeConnection();
			        System.exit(3); // Service status Unknown
			    	}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
