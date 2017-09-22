package com.barchart.mpchartdemo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/09/15
 *     desc   : 用于分段堆积柱状图数据实体类
 *     version: 1.0
 * </pre>
 */

public class TextBarDataEntity {
    public String overviewName;//区块名称
    public List<Record> recordList;//数据列表，按时间区间

    public static class Record implements Serializable {
        public String timeScale;//时间区间
        public List<Source> sourceList;//来源数据列表

        public static class Source implements Serializable {
            public String sourceName;//来源名称
            public int sourceNum;//数量
        }
    }

    public TextBarDataEntity parseData() {
        TextBarDataEntity textBarDataEntity = new TextBarDataEntity();
        Random r = new Random();
        textBarDataEntity.overviewName = "TCL O2O";
        textBarDataEntity.recordList = new ArrayList<>();
        for (int i= 0;i<=4;i++){
            Record record = new Record();
            record.timeScale = "第" + (i+1) + "周" ;
            record.sourceList = new ArrayList<>();
            for (int j=0; j<= 3; j++ ){
                Record.Source source = new Record.Source();
                source.sourceName = "TCL第" + (j+1) + "产业";
                source.sourceNum = r.nextInt(10*(j+1));
                record.sourceList.add(source);
            }
            Record.Source source = new Record.Source();
            source.sourceName = "TCL第五产业";
            source.sourceNum = 100 - record.sourceList.get(0).sourceNum - record.sourceList.get(1).sourceNum
                  - record.sourceList.get(2).sourceNum - record.sourceList.get(3).sourceNum;
            record.sourceList.add(source);
            textBarDataEntity.recordList.add(record);
        }
        return textBarDataEntity;
    }
}
