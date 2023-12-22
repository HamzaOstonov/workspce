package com.is.delta.core;

/**
 * Created by root on 22.04.2017.
 * 18:21
 */
public class Criteria {
    private int pageIndex;
    private int pageSize;
    private FilterInterface filter;

    private Criteria(){
    }

    private void setPageIndex(int pageIndex){

        this.pageIndex = pageIndex;
    }

    private void setPageSize(int pageSize){
        this.pageSize = pageSize;
    }

    public void setFilter(FilterInterface filter) {
        this.filter = filter;
    }

    public int getPageIndex(){
        return pageIndex;
    }

    public int getPageSize(){
        return pageSize;
    }

    public FilterInterface getFilter(){
        return filter;
    }

    public static class Builder{
        private int pageIndex_;
        private int pageSize_;
        private FilterInterface filter_;

        public Builder pageIndex(int pageIndex){
            pageIndex_ = pageIndex;
            return this;
        }

        public Builder pageSize(int pageSize){
            pageSize_ = pageSize;
            return this;
        }

        public Builder filter(FilterInterface filter){
            filter_ = filter;
            return this;
        }

        public Criteria build(){
            Criteria criteria = new Criteria();
            criteria.setPageSize(pageSize_);
            criteria.setPageIndex(pageIndex_);
            criteria.setFilter(filter_);
            return  criteria;
        }
    }
    @Override
    public String toString() {
        return "Criteria{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", filter=" + filter +
                '}';
    }
}

