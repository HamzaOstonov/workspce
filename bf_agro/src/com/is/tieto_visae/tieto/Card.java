package com.is.tieto_visae.tieto;


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

	public EMVData geteMVData() {
		return eMVData;
	}

	public void seteMVData(EMVData eMVData) {
		this.eMVData = eMVData;
	}

	public TSMData gettSMData() {
		return tSMData;
	}

	public void settSMData(TSMData tSMData) {
		this.tSMData = tSMData;
	}

	public void setLogicalCard(LogicalCard logicalCard) {
		this.logicalCard = logicalCard;
	}

	public void setPhysicalCard(PhysicalCard physicalCard) {
		this.physicalCard = physicalCard;
	}

	public Card(LogicalCard logicalCard, PhysicalCard physicalCard,
			EMVData eMVData, TSMData tSMData) {
		super();
		this.logicalCard = logicalCard;
		this.physicalCard = physicalCard;
		this.eMVData = eMVData;
		this.tSMData = tSMData;
	}

	public Card() {
		super();
	}
	
	
}
