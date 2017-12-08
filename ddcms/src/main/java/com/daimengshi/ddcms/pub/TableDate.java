package com.daimengshi.ddcms.pub;

import com.xiaoleilu.hutool.date.DateUtil;

import java.util.Date;

/**
 * Created by zhoufeng on 2017/11/17.
 *
 */
public class TableDate {

    /**
     * date : 15
     * hours : 0
     * seconds : 0
     * month : 12
     * year : 2017
     * minutes : 0
     */
    private int date;
    private int hours;
    private int seconds;
    private int month;
    private int year;
    private int minutes;

    public void setDate(int date) {
        this.date = date;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getMinutes() {
        return minutes;
    }

    public static Date toDate(TableDate tableDate) {

        if(tableDate == null)
            return null;
        String dateStr = tableDate.getYear() + "-" + tableDate.getMonth() + "-" + tableDate.getDate();

        return DateUtil.parse(dateStr, "yyyy-MM-dd");
    }
}
