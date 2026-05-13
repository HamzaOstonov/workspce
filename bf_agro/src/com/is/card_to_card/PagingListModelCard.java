package com.is.card_to_card;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public abstract class PagingListModelCard extends AbstractPagingListModel<Card> implements BindingListModel {

public PagingListModelCard(int startPageNumber, int pageSize, Object fl, String alias) {
    super(startPageNumber, pageSize,fl, alias);
    }
    
@Override
protected List<Card> getPageData(int indexpage, int pageSize, Object fl,String alias) {
	Card fc;
    if(fl !=null){
        fc = (Card)fl;
}else{
        fc = new Card();
}
    return CardtcService.getCards(indexpage, pageSize,fc, alias);
}

@Override
public int getTotalSize(Object filter,String alias)  {
    Card fc;
    if(filter !=null){
        fc = (Card)filter;
}else{
        fc = new Card();
}
    return CardtcService.getCount1(fc, alias);
}

@Override
public int indexOf(Object obj) {
        return 0;
}



}


