package com.is.globalTieto.account;

import java.util.ArrayList;
import java.util.List;

import com.is.globalTieto.card.Card;
import com.is.globalTieto.tietoModels.AccAddInfo;
import com.is.globalTieto.tietoModels.AccBaseInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author D
 * 
 */
public class Account {	

	private AccBaseInfo accBaseInfo;
	private AccAddInfo accAddInfo;

	private List<Card> cards = new ArrayList<Card>();
	
	public AccBaseInfo getAccBaseInfo() {
		if(accBaseInfo == null) {
			accBaseInfo = new AccBaseInfo();
		}
		return accBaseInfo;
	}
	
	public AccAddInfo getAdditional() {
		if(accAddInfo == null) {
			accAddInfo = new AccAddInfo();
		}
		return accAddInfo;
	}

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void setAccBaseInfo(AccBaseInfo accBaseInfo) {
        this.accBaseInfo = accBaseInfo;
    }

    public void setAccAddInfo(AccAddInfo accAddInfo) {
        this.accAddInfo = accAddInfo;
    }

    public Account(AccBaseInfo accBaseInfo, AccAddInfo accAddInfo, List<Card> cards) {
        this.accBaseInfo = accBaseInfo;
        this.accAddInfo = accAddInfo;
        this.cards = cards;
    }

    public Account() {
    }
}
