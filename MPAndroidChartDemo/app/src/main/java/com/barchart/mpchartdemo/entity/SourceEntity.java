package com.barchart.mpchartdemo.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by chenjie05 on 2016/12/8.
 */

public class SourceEntity  {
public class Source{
    private int allCount;
    private int badCount;
    private int goodCount;
    private int otherCount;
    private String source;
    private int scale;
    public float getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public int getBadCount() {
        return badCount;
    }

    public void setBadCount(int badCount) {
        this.badCount = badCount;
    }

    public int getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(int goodCount) {
        this.goodCount = goodCount;
    }

    public int getOtherCount() {
        return otherCount;
    }

    public void setOtherCount(int otherCount) {
        this.otherCount = otherCount;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
    private List<Source> list;

    public List<Source> parseData() {
        list = new ArrayList<>();
        Random r = new Random();
        for (int i= 0;i<=6;i++){
            Source source = new Source();
            source.setBadCount(r.nextInt(100));
            source.setGoodCount(r.nextInt(100));
            source.setOtherCount(r.nextInt(100));
            source.setScale(r.nextInt(100));
            source.setSource("品类" + i);
            source.setAllCount(source.getBadCount() + source.getGoodCount() + source.getOtherCount());
            list.add(source);
        }
        return list;
    }



    public List<Source> getList() {
        return list;
    }

    public void setList(List<Source> list) {
        this.list = list;
    }
}
