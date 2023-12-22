package com.is.tf.sender;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Sender> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Sender> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    SenderFilter fc;
    if(fl !=null){
        fc = (SenderFilter)fl;
}else{
        fc = new SenderFilter();
}
    return SenderService.getSendersFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    SenderFilter fc;
    if(fl !=null){
        fc = (SenderFilter)fl;
}else{
        fc = new SenderFilter();
}
    return SenderService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Sender> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
