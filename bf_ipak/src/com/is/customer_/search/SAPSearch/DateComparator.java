package com.is.customer_.search.SAPSearch;

import com.is.customer_.search.SAPSearch.model.Response;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by root on 23.06.2017.
 * 14:05
 */
public class DateComparator implements Comparator<Object>{
    private boolean asc;

    public DateComparator(boolean asc){
        this.asc = asc;
    }

    @Override
    public int compare(Object o1, Object o2) {
        Response r1 = (Response) o1;
        Response r2 = (Response) o2;

        Date d1 = r1.getBirthDay();
        Date d2 = r2.getBirthDay();

        if (d1 != null && d2 != null) {
            return d1.compareTo(d2) * (asc ? 1 : - 1);
/*
            if (d1.before(d2))
                return (asc ? 1 : -1) * -1;
            else if (d1.after(d2))
                return (asc ? 1 : -1) * 1;
            else
                return 0;*/
        }
        return 0;
    }
}
