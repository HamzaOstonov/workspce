package com.is.card_to_card;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.zkoss.zkplus.databind.BindingListModel;


import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public abstract class PagingListModel1 extends AbstractPagingListModel<Card> implements BindingListModel {

	public PagingListModel1(int startPageNumber, int pageSize, Object fl, String alias) {
		super(startPageNumber, pageSize, fl, alias);
	}

	@Override
	protected List<Card> getPageData1(int itemStartNumber, int pageSize, Object fl, String alias) {
		Card fc;
		if (fl != null) {
			fc = (Card) fl;
		} else {
			fc = new Card();
		}
		return CardtcService.getProtocolById(alias, alias, alias);
	}
	private List<Card> _items = new ArrayList<Card>(); // Ensure initialization

  

    
    public int getSize() {
        return _items.size();
    }
	@Override
	protected List<Card> getPageData3(int itemStartNumber, int pageSize, Object fl, String alias) {
	    return getPageData1(itemStartNumber, pageSize, fl, alias);  // Call your existing method
	}

	@Override
	public int getTotalSize(Object fl, String alias) {
		Card fc;
		if (fl != null) {
			fc = (Card) fl;
		} else {
			fc = new Card();
		}
		return CardtcService.getCount(fc, alias);
	}


	


}