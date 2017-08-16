package com.barchart.mpchartdemo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/08/16
 *     desc   : 用于横向柱状图数据
 *     version: 1.0
 * </pre>
 */

public class BarDataEntity implements Serializable{
    private List<Type> typeList;

    public List<Type> getTypeList() {
        return typeList;
    }

    public static class Type implements Serializable {
        private String typeName;//类型名称
        private int sale;//销量
        private double typeScale;//类型占比

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public int getSale() {
            return sale;
        }

        public void setSale(int sale) {
            this.sale = sale;
        }

        public double getTypeScale() {
            return typeScale;
        }

        public void setTypeScale(double typeScale) {
            this.typeScale = typeScale;
        }
    }

    public List<Type> parseData(){
        typeList = new ArrayList<>();
        Random r = new Random();
        int all=0;
        for (int i= 0;i<=6;i++){
            Type type = new Type();
            type.setSale(r.nextInt(100));
            type.setTypeName("品类" + i);
            typeList.add(type);
        }
        for (int i= 0;i<=6;i++){
            all+= typeList.get(i).getSale();
        }
        for (int i= 0;i<=6;i++){
            double typeScale = (double) typeList.get(i).getSale()/all;
            typeList.get(i).setTypeScale(typeScale);
            System.out.println("==>"+typeList.get(i).getTypeScale());
        }
        return typeList;
    }
}
