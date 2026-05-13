package com.is.soato;

import java.util.List;

/**
 * Created by root on 22.04.2017.
 * 18:11
 */
public interface PagingDao<T> {
    List<T> getData(Criteria criteria);

    int getCount(Criteria criteria);
}
