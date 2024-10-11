package com.is.clientcrm;

import java.sql.Connection;
import java.util.List;

import com.is.utils.FilterField;

public interface Dao<T> {
    <T1 extends T> void setFilter(T1 filter);

    List<FilterField> getFilterFields();

    int getCount();

    List<T> getList();

    List<T> getListWithPaging(int pageIndex, int pageSize);

    T getItemByLongId(String branch, long itemId);

    T getItemByLongId(Connection c, String branch, long itemId);

    T getItemByStringId(String branch, String itemId);

    T getItemByStringId(Connection c, String branch, String itemId);

    T create(T item) throws Exception;

    T create(Connection c, T item) throws Exception;

    int update(T item) throws Exception;

    int update(Connection c, T item) throws Exception;

    int remove(T item) throws Exception;

    int remove(Connection c, T item) throws Exception;
}
