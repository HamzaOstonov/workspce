package com.is.tieto_visa.tieto;


/**
 * @author D
 * 
 */

public class Card {

	private LogicalCard logicalCard;

	private PhysicalCard physicalCard;

	private EMVData eMVData;

	private TSMData tSMData;

	public LogicalCard getLogicalCard() {
		if(logicalCard == null) {
			logicalCard = new LogicalCard();
		}
		return logicalCard;
	}
	
	public PhysicalCard getPhysicalCard() {
		if(physicalCard == null) {
			physicalCard = new PhysicalCard();
		}
		return physicalCard;
	}
	
	public EMVData getEMVData() {
		if(eMVData == null) {
			eMVData = new EMVData();
		}
		return eMVData;
	}
	
	public TSMData getTSMData() {
		if(tSMData == null) {
			tSMData = new TSMData();
		}
		return tSMData;
	}
}
