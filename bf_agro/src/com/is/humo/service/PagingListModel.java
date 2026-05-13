// 
// Decompiled by Procyon v0.5.36
// 

package com.is.humo.service;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<HumoServiceModel> implements BindingListModel
{
    public PagingListModel(final int startPageNumber, final int pageSize, final Object fl, final String branch) {
        super(startPageNumber, pageSize, fl, branch);
    }
    
    protected List<HumoServiceModel> getPageData(final int itemStartNumber, final int pageSize, final Object fl, final String branch) {
        final HumoService humoService = new HumoService();
        HumoServiceModelFilter fc;
        if (fl != null) {
            fc = (HumoServiceModelFilter)fl;
        }
        else {
            fc = new HumoServiceModelFilter();
        }
        return humoService.gethumoservicesFl(itemStartNumber, pageSize, fc);
    }
    
    public int getTotalSize(final Object fl, final String branch) {
        final HumoService humoService = new HumoService();
        HumoServiceModelFilter fc;
        if (fl != null) {
            fc = (HumoServiceModelFilter)fl;
        }
        else {
            fc = new HumoServiceModelFilter();
        }
        return humoService.getCount(fc);
    }
    
    public int indexOf(final Object obj) {
        return 0;
    }
    
    protected List<HumoServiceModel> getPageData(final int itemStartNumber, final int pageSize) {
        return null;
    }
    
    public int getTotalSize() {
        return 0;
    }
}
