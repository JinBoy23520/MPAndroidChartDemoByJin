package com.barchart.mpchartdemo.entity;

import com.github.mikephil.chart_3_0_1v.data.BarEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/04/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MonthItemEntity {

        /**数据名*/
        private String kpiName;
        /**数据类型*/
        private int kpiType;
        /**单位*/
        String kpiUnit;
        private List<WeekEntity> monthDatas = new ArrayList<>();
        private List<BarEntry> monthEntries = new ArrayList<>();

        String entityString ="{\"kpiName\":\"接通率\",\"kpiType\":1,\"kpiUnit\":\"%\"," +
                "\"monthData\":[{\"beginTime\":\"02.27\",\"endTime\":\"03.05\",\"kpiValue\":97.6800}," +
                "{\"beginTime\":\"03.06\",\"endTime\":\"03.12\",\"kpiValue\":96.9200}," +
                "{\"beginTime\":\"03.06\",\"endTime\":\"03.12\",\"kpiValue\":96.9200}," +
                "{\"beginTime\":\"03.13\",\"endTime\":\"03.19\",\"kpiValue\":96.4200}]}";

    public MonthItemEntity()  {
        parseData(entityString);
    }

    public void parseData( String decode) {
        try {
            JSONObject object = new JSONObject(decode);
            parseFromJson(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void parseFromJson (JSONObject jsonObject) {
            kpiName = jsonObject.optString("kpiName");
            kpiUnit = jsonObject.optString("kpiUnit");
            kpiType = jsonObject.optInt("kpiType");

            JSONArray monthArray = jsonObject.optJSONArray("monthData");
            if (monthArray != null && monthArray.length() > 0) {
                for (int index = 0, len = monthArray.length(); index < len; index ++) {
                    WeekEntity weekEntity = new WeekEntity();
                    weekEntity.parseFromJson(monthArray.optJSONObject(index));
                    monthDatas.add(weekEntity);
                    try {
                        float value = Float.parseFloat(weekEntity.getKpiValue());
                        monthEntries.add(new BarEntry(index + 1, value));
                    } catch (NumberFormatException e) {
                        monthEntries.add(new BarEntry(index + 1, 0));
                        e.printStackTrace();
                    }
                }
            }
        }


        public String getKpiName() {
            return kpiName;
        }

        public void setKpiName(String kpiName) {
            this.kpiName = kpiName;
        }

        public int getKpiType() {
            return kpiType;
        }

        public void setKpiType(int kpiType) {
            this.kpiType = kpiType;
        }

        public List<WeekEntity> getMonthDatas() {
            return monthDatas;
        }

        public String getKpiUnit() {
            return kpiUnit;
        }

        public void setMonthDatas(List<WeekEntity> monthDatas) {
            this.monthDatas = monthDatas;
        }

        public List<BarEntry> getMonthEntries() {
            return monthEntries;
        }

        public void setMonthEntries(List<BarEntry> monthEntries) {
            this.monthEntries = monthEntries;
        }

        /**
         * 每周实体
         */
        public static class WeekEntity {
            /**周起始时间*/
            String beginTime;
            /**周结束时间*/
            String endTime;
            /**数值*/
            String kpiValue;


            public void parseFromJson (JSONObject jsonObject) {
                kpiValue = jsonObject.optString("kpiValue");
                beginTime = jsonObject.optString("beginTime");
                endTime = jsonObject.optString("endTime");
            }

            public String getBeginTime() {
                return beginTime;
            }

            public void setBeginTime(String beginTime) {
                this.beginTime = beginTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public void setKpiValue(String kpiValue) {
                this.kpiValue = kpiValue;
            }


            public String getKpiValue() {
                return kpiValue;
            }

        }
    }

