package com.daimengshi.ddcms.pub;

import com.jfinal.plugin.activerecord.Page;

import java.util.List;

/**
 * Created by zhoufeng on 2017/11/17.
 *
 */
public class TablePage<T> {

    /**
     * code : 0
     * msg :
     * count : 1000
     * data : []
     */

    private int code;
    private String msg;
    private int count;
    private List<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public TablePage(Page page) {
        this.code = 0;
        this.msg = "成功";
        this.data = page.getList();
        this.count = page.getTotalRow();

    }

    public static TablePage ok(Page page) {
        return new TablePage(page);
    }
}
