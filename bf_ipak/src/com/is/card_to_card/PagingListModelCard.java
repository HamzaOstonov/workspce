package com.is.card_to_card;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModelCard extends AbstractPagingListModel<Card> implements BindingListModel {

public PagingListModelCard(int startPageNumber, int pageSize, Object fl, String alias) {
    super(startPageNumber, pageSize,fl, alias);
    }
    
@Override
protected List<Card> getPageData(int indexpage, int pageSize, Object fl,String alias) {
	CardFilter fc;
    if(fl !=null){
        fc = (CardFilter)fl;
}else{
        fc = new CardFilter();
}
    return CardtcService.getCards(indexpage, pageSize,fc, alias);
}

@Override
public int getTotalSize(Object filter,String alias)  {
    CardFilter fc;
    if(filter !=null){
        fc = (CardFilter)filter;
}else{
        fc = new CardFilter();
}
    return CardtcService.getCount1(fc, alias);
}

@Override
public int indexOf(Object obj) {
        return 0;
}

@Override
public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

@Override
protected List<TrAcc> getPageData1(int itemStartNumber, int pageSize, Object fl, String alias) {
		// TODO Auto-generated method stub
		return null;
	}

@Override
protected List<Card> getPageData(int itemStartNumber, int pageSize) {
	// TODO Auto-generated method stub
	return null;
}

}


