package com.daimengshi.ddcms.pub;

/**
 * Created by zhoufeng on 2017/12/7.
 *
 */
public class TableDataRequest {

    private int size;
    private int page;
    private String dateStart;
    private String dateEnd;

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
