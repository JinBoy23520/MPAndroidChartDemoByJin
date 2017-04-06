package com.barchart.mpchartdemo.entity;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/04/05
 *     desc   :实体类
 *     version: 1.0
 * </pre>
 */

public class YoyListEntity {
    private String year;
    private String month;
    private String amount; //销售数值

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
