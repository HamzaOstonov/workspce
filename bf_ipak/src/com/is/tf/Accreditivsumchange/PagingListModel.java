package com.is.tf.Accreditivsumchange;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Accreditivsumchange> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Accreditivsumchange> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    AccreditivsumchangeFilter fc;
    if(fl !=null){
        fc = (AccreditivsumchangeFilter)fl;
}else{
        fc = new AccreditivsumchangeFilter();
}
    return AccreditivsumchangeService.getAccreditivsumchangesFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    AccreditivsumchangeFilter fc;
    if(fl !=null){
        fc = (AccreditivsumchangeFilter)fl;
}else{
        fc = new AccreditivsumchangeFilter();
}
    return AccreditivsumchangeService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Accreditivsumchange> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}



}
