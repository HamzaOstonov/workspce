package com.is.card_to_card;

import java.util.List;
import java.util.ArrayList;
import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zul.event.ListDataListener;

public class CustomPagingListModel implements BindingListModel {

    private int startPageNumber;
    private int pageSize;
    private Object filter;
    private String alias;

    private List<Card> _items = new ArrayList<Card>();

    public CustomPagingListModel(int startPageNumber, int pageSize, Object filter, String alias) {
        this.startPageNumber = startPageNumber;
        this.pageSize = pageSize;
        this.filter = filter;
        this.alias = alias;
    }

    public Card getElementAt(int index) {
        return _items.get(index);
    }

    public int getSize() {
        return _items.size();
    }

    public List<Card> getElements() {
        return _items;
    }

    protected List<Card> getPageData1(int itemStartNumber, int pageSize, Object fl, String alias) {
        Card fc;
        if (fl != null) {
            fc = (Card) fl;
        } else {
            fc = new Card();
        }
        return CardtcService.getProtocolById(alias, alias, alias);
    }

    protected List<Card> getPageData4(int itemStartNumber, int pageSize, Object fl, String alias) {
        Card fc;
        if (fl != null) {
            fc = (Card) fl;
        } else {
            fc = new Card();
        }
        return CardtcService.getProtocolById(alias, alias, alias);
    }

    public int getTotalSize(Object fl, String alias) {
		Card fc;
		if (fl != null) {
			fc = (Card) fl;
		} else {
			fc = new Card();
		}
		return CardtcService.getCount(fc, alias);
	}

	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int indexOf(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
