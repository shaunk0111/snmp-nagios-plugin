# snmp-nagios-plugin
Provides generic framework to build nagios and icinga compliant plugin with SNMP agents

# Example use
	// Create SNMP Agent
	SnmpConfigModel config = new SnmpConfigModel(hostname,port,1,public,1,1000);
	SnmpConnectionContoller connectionController = new SnmpConnectionContoller(config);
	SnmpConnectionContoller connectionController.createConnection();

	// Create PDU list
	LinkedList<SnmpOidModel> dataList = new LinkedList<SnmpOidModel>();

	// Retrieve data
	SnmpDataController dataController = new SnmpDataController(connectionController);
	dataControtller.setData(dataList);
	dataController.retrieveData();	

	// Output data
	List<Integer> states;
	consoleController = new SnmpConsoleController(connectionController,dataController);
	consoleController.printOutputandExit(states);

