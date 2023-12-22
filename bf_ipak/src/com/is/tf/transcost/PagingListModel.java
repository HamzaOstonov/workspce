package com.is.tf.transcost;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Transcost> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Transcost> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    TranscostFilter fc;
    if(fl !=null){
        fc = (TranscostFilter)fl;
}else{
        fc = new TranscostFilter();
}
    return TranscostService.getTranscostsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    TranscostFilter fc;
    if(fl !=null){
        fc = (TranscostFilter)fl;
}else{
        fc = new TranscostFilter();
}
    return TranscostService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Transcost> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}


}
