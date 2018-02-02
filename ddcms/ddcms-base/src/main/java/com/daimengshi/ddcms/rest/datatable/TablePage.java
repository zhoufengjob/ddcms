package com.daimengshi.ddcms.rest.datatable;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhoufeng on 2017/11/17.
 */
public class TablePage<T extends Model> implements Serializable {

    /**
     * code : 0
     * msg :
     * count : 1000
     * data : []
     */

    private int code;
    private String msg;
    private int total;
    private List<T> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

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


    public TablePage(Page page) {
        this.code = 0;
        this.msg = "成功";
        this.rows = page.getList();
        this.total = page.getTotalRow();

    }

    public static TablePage dataTableFormat(Page page) {
        return new TablePage(page);
    }
}
