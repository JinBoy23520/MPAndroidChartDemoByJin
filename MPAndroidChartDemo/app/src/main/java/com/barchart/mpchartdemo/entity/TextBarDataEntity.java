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
    private String overviewName;//区块名称
    private List<Record> recordList;//数据列表，按时间区间

    public TextBarDataEntity(){
        setRecordList(parseData());
    }

    public String getOverviewName() {
        return overviewName;
    }

    public void setOverviewName(String overviewName) {
        this.overviewName = overviewName;
    }

    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    public static class Record implements Serializable {
        private String timeScale;//时间区间
        private List<Source> sourceList;//来源数据列表

        public String getTimeScale() {
            return timeScale;
        }

        public void setTimeScale(String timeScale) {
            this.timeScale = timeScale;
        }

        public List<Source> getSourceList() {
            return sourceList;
        }

        public void setSourceList(List<Source> sourceList) {
            this.sourceList = sourceList;
        }

        public static class Source implements Serializable {
            private String sourceName;//来源名称
            private int sourceNum;//数量

            public String getSourceName() {
                return sourceName;
            }

            public void setSourceName(String sourceName) {
                this.sourceName = sourceName;
            }

            public int getSourceNum() {
                return sourceNum;
            }

            public void setSourceNum(int sourceNum) {
                this.sourceNum = sourceNum;
            }
        }
    }

    public List<Record>  parseData(){
        recordList = new ArrayList<>();
        Random r = new Random();
        for (int i= 0;i<=4;i++){
            Record record = new Record();
            record.setTimeScale("第" + (i+1) + "周");
            List<Record.Source> list = new ArrayList<>();
            for (int j=0; j<= 3; j++ ){
                Record.Source source = new Record.Source();
                source.setSourceName("TCL第" + (j+1) + "产业");
                source.setSourceNum(r.nextInt(10*(j+1)));
                list.add(source);
            }
            Record.Source source = new Record.Source();
            source.setSourceName("TCL第5产业");
            int sourceNum = 100 - list.get(0).getSourceNum() - list.get(1).getSourceNum()
                    - list.get(2).getSourceNum() - list.get(3).getSourceNum();
            source.setSourceNum(sourceNum);
            list.add(source);
            record.setSourceList(list);
            recordList.add(record);
        }
        return recordList;
    }
}
