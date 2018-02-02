package com.daimengshi.ddcms.rest.datatable;

import java.io.Serializable;

/**
 * Created by zhoufeng on 2017/12/7.
 */
public class TableDataRequest implements Serializable {

    private int size = 10;
    private int page = 1;
    private String dateStart = "";
    private String dateEnd = "";
    private String searchKey = "";
    private String dateValue = "";


    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
