package com.is.customer_.search.SAPSearch;

import com.is.customer_.search.SAPSearch.model.Response;

import java.util.Comparator;

/**
 * Created by root on 23.06.2017.
 * 13:45
 */
public class NameComparator implements Comparator<Object>{
    private int type;
    private boolean asc;

    public NameComparator(boolean asc,int type ){
        this.asc = asc;
        this.type = type;
    }
    @Override
    public int compare(Object o1, Object o2) {
        Response r1 = (Response) o1;
        Response r2 = (Response) o2;

        String v1 = type == 1 ? r1.getLastNameLocal() : r1.getFirstNameLocal();
        String v2 = type == 1 ? r2.getLastNameLocal() : r2.getFirstNameLocal();

        if (v1 != null && v2 != null)
            return v1.compareTo(v2) * (asc ? 1 : -1);

        return 0;
    }
}
