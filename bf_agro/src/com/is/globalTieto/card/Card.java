package com.is.globalTieto.card;

import com.is.globalTieto.tietoModels.EMVData;
import com.is.globalTieto.tietoModels.LogicalCard;
import com.is.globalTieto.tietoModels.PhysicalCard;
import com.is.globalTieto.tietoModels.TSMData;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author D
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
public class Card {
	@Setter
	private LogicalCard logicalCard;
	@Setter
	private PhysicalCard physicalCard;
	@Setter
	private EMVData eMVData;
	@Setter
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
