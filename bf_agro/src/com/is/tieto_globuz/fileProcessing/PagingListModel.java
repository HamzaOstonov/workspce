// 
// Decompiled by Procyon v0.5.36
// 

package com.is.tieto_globuz.fileProcessing;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<File> implements BindingListModel
{
    private static final long serialVersionUID = 1L;
    
    public PagingListModel(final int startPageNumber, final int pageSize, final Object fl, final String branch) {
        super(startPageNumber, pageSize, fl, branch);
    }
    
    protected List<File> getPageData(final int itemStartNumber, final int pageSize, final Object fl, final String branch) {
        FileFilter fc;
        if (fl != null) {
            fc = (FileFilter)fl;
        }
        else {
            fc = new FileFilter();
        }
        return FileService.getFilesFl(itemStartNumber, pageSize, fc);
    }
    
    public int getTotalSize(final Object fl, final String branch) {
        FileFilter fc;
        if (fl != null) {
            fc = (FileFilter)fl;
        }
        else {
            fc = new FileFilter();
        }
        return FileService.getCount(fc);
    }
    
    public int indexOf(final Object obj) {
        return 0;
    }
    
    protected List<File> getPageData(final int itemStartNumber, final int pageSize) {
        return null;
    }
    
    public int getTotalSize() {
        return 0;
    }
}
