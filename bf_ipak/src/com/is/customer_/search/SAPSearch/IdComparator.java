package com.is.customer_.search.SAPSearch;

import com.is.customer_.search.SAPSearch.model.Response;

import java.util.Comparator;

/**
 * Created by root on 23.06.2017.
 * 14:58
 */
public class IdComparator implements Comparator{
    private boolean asc;

    public IdComparator(boolean asc){
        this.asc = asc;
    }
    @Override
    public int compare(Object o1, Object o2) {
        Response r1 = (Response) o1;
        Response r2 = (Response) o2;

        String id1 = r1.getSapId();
        String id2 = r2.getSapId();

        if (id1 != null && id2!=null)
            return id1.compareTo(id2) * (asc ? 1 : -1);
        return 0;
    }
}
