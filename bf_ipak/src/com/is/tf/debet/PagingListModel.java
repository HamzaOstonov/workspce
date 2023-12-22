package com.is.tf.debet;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Debet> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Debet> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    DebetFilter fc;
    if(fl !=null){
        fc = (DebetFilter)fl;
}else{
        fc = new DebetFilter();
}
    return DebetService.getDebetsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    DebetFilter fc;
    if(fl !=null){
        fc = (DebetFilter)fl;
}else{
        fc = new DebetFilter();
}
    return DebetService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Debet> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}
}
