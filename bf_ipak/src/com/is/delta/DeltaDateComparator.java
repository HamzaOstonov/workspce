package com.is.delta;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by root on 30.06.2017.
 * 14:09
 */
public class DeltaDateComparator implements Comparator {
    private final boolean asc;

    public DeltaDateComparator(boolean asc) {
        this.asc = asc;
    }

    @Override
    public int compare(Object o1, Object o2) {
        DELTARecord r1 = (DELTARecord) o1;
        DELTARecord r2 = (DELTARecord) o2;

        Date d1 = r1.getV_date();
        Date d2 = r2.getV_date();

        if (d1 != null && d2 != null)
            return d1.compareTo(d2) * (asc ? 1 : -1);

        return 0;
    }
}
