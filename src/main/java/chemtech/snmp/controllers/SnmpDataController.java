package chemtech.snmp.controllers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import chemtech.snmp.models.SnmpOidModel;


public class SnmpDataController {
	
	private SnmpConnectionController connectionController;
	private LinkedList<SnmpOidModel> dataList = new LinkedList<SnmpOidModel>();
			
	public SnmpDataController(SnmpConnectionController connectionController) {
		this.connectionController = connectionController;
	}
	
	public void retrieveData() throws Exception {
		connectionController.pushData(dataList);
	}
	
	public LinkedList<SnmpOidModel> getData() {
		return dataList;
	}
	
	public void setData(LinkedList<SnmpOidModel> dataList) {
		this.dataList = dataList;
	}
	
	/**
	 * Status by strings
	 * @param symbol
	 * @param listOk
	 * @param listWarning
	 * @param listCritical
	 * @return
	 * @throws Exception
	 */
	public int getStatusByStrings(String symbol, String limits) throws Exception {
		
		// Get value
		String value = getValueBySymbol(symbol);
		
		// Get limits
		List<String> listLimits = Arrays.asList(limits.split(","));
		List<String> listOk = Arrays.asList(listLimits.get(0).split("\\."));
		List<String> listWarning = Arrays.asList(listLimits.get(1).split("\\."));
		List<String> listCritical = Arrays.asList(listLimits.get(2).split("\\."));
		Iterator<String> okIterator = listOk.iterator();
		Iterator<String> warningIterator = listWarning.iterator();
		Iterator<String> criticalIterator = listCritical.iterator();
		
		// Check critical
		while (criticalIterator.hasNext()) 
			if (value.equals(criticalIterator.next()))
				return 2;
		
		// Check warning
		while (warningIterator.hasNext()) 
			if (value.equals(warningIterator.next()))
				return 1;
		
		// Check OK
		while (okIterator.hasNext()) 
			if (value.equals(okIterator.next()))
				return 0;

		// String to found
        return 3;	
	}

	/**
	 * 
	 * @param symbol
	 * @return value
	 * @throws Exception
	 */
	public String getValueBySymbol(String symbol) throws Exception {
		
		Iterator<SnmpOidModel> Iterator = dataList.iterator();
		SnmpOidModel oid;
		
        while (Iterator.hasNext()) {
        	oid = Iterator.next();
        	if (oid.getSymbol().equals(symbol)) {
        		return oid.getValue();
        	}
        }
        // Symbol not found
        throw new Exception("Error: Symbol not found");
	}
	
	/**
	 * Status of value by limits
	 * @param symbol
	 * @param limits
	 * @return OK 0, Warning 1, Critical 2, Unknown 3
	 * @throws Exception
	 */
	public int getStatusByLimits(String symbol, String limits) throws Exception {
		
		// Get value
		String value = getValueBySymbol(symbol);
		double currentValue = Double.parseDouble(value);
				
		// Get limits
		List<String> alimits = Arrays.asList(limits.split(","));
        double lowerLimitCritical = Double.parseDouble(alimits.get(0));
        double lowerLimitWarning = Double.parseDouble(alimits.get(1));
        double upperLimitWarning = Double.parseDouble(alimits.get(2));
        double upperLimitCritical = Double.parseDouble(alimits.get(3));
        
		// Check status
		if ((currentValue < lowerLimitCritical) || (currentValue > upperLimitCritical))
			return 2;
		else if ((currentValue < lowerLimitWarning) || (currentValue > upperLimitWarning)) 
			return 1;
		else
			return 0;	
	}
	
	/**
	 * Add an OID to listData
	 * @param oid
	 */
	public void addOidToList(SnmpOidModel oid) {
		dataList.add(oid);
	}

}
	

