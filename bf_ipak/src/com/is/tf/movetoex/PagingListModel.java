package com.is.tf.movetoex;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Movetoex> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Movetoex> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    MovetoexFilter fc;
    if(fl !=null){
        fc = (MovetoexFilter)fl;
}else{
        fc = new MovetoexFilter();
}
    return MovetoexService.getMovetoexsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    MovetoexFilter fc;
    if(fl !=null){
        fc = (MovetoexFilter)fl;
}else{
        fc = new MovetoexFilter();
}
    return MovetoexService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Movetoex> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
