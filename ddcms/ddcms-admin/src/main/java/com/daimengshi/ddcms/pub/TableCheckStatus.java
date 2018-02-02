package com.daimengshi.ddcms.pub;

import java.util.List;

/**
 * Created by zhoufeng on 2017/11/21.
 * 接收View层传递表格多选信息
 */
public class TableCheckStatus<T> {
    private boolean isAll;
    private List<T> data;

    public boolean isAll() {
        return isAll;
    }

    public void setAll(boolean all) {
        isAll = all;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

