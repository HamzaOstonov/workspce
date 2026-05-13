package com.is.card_to_card;
import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;


public class PagingListModel extends AbstractPagingListModel<Card> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Card> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    Card fc;
    if(fl !=null){
        fc = (Card)fl;
}else{
        fc = new Card();
}
    return CardtcService.getTrAccsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    Card fc;
    if(fl !=null){
        fc = (Card)fl;
}else{
        fc = new Card();
}
    return CardtcService.getCount(fc);
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
protected List<Card> getPageData3(int itemStartNumber, int pageSize, Object fl, String alias) {
	// TODO Auto-generated method stub
	return null;
}

@Override
protected List<Card> getPageData1(int itemStartNumber, int pageSize, Object fl, String alias) {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected List<Card> getPageData(int itemStartNumber, int pageSize) {
	// TODO Auto-generated method stub
	return null;
}


}