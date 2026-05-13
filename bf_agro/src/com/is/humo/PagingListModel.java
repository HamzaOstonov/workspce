package com.is.humo;

import java.sql.SQLException;
import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<HumoCards> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<HumoCards> getPageData(int itemStartNumber, int pageSize, Object fl, String branch)  {
    HumoCardsFilter fc;
    if(fl !=null){
        fc = (HumoCardsFilter)fl;
}else{
        fc = new HumoCardsFilter();
}
    return HumoCardsService.getHumoCardssFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)    {
    HumoCardsFilter fc;
    if(fl !=null){
        fc = (HumoCardsFilter)fl;
}else{
        fc = new HumoCardsFilter();
}
    return HumoCardsService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<HumoCards> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}


}


