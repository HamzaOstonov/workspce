package com.is.tf.movefromex;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<MoveFromEx> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<MoveFromEx> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    MoveFromExFilter fc;
    if(fl !=null){
        fc = (MoveFromExFilter)fl;
}else{
        fc = new MoveFromExFilter();
}
    return MoveFromExService.getMoveFromExsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    MoveFromExFilter fc;
    if(fl !=null){
        fc = (MoveFromExFilter)fl;
}else{
        fc = new MoveFromExFilter();
}
    return MoveFromExService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<MoveFromEx> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}


}
