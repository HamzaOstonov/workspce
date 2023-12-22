package com.is.tf.Goods;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Goods> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Goods> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    GoodsFilter fc;
    if(fl !=null){
        fc = (GoodsFilter)fl;
}else{
        fc = new GoodsFilter();
}
    return GoodsService.getGoodssFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    GoodsFilter fc;
    if(fl !=null){
        fc = (GoodsFilter)fl;
}else{
        fc = new GoodsFilter();
}
    return GoodsService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Goods> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
