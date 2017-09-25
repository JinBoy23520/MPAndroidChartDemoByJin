package com.barchart.mpchartdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.barchart.mpchartdemo.R;
import com.barchart.mpchartdemo.entity.BarEntity;
import com.barchart.mpchartdemo.util.DensityUtil;

import java.text.DecimalFormat;
import java.util.List;


/**
 * Created by chenjie05 on 2016/12/14.
 */

public class BarGroup extends LinearLayout {
    private List<BarEntity> datas;
    public BarGroup(Context context) {
        super(context);
        init();
    }

    public BarGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BarGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);

    }

    public void setDatas(List<BarEntity> datas) {
        if (datas != null) {
            this.datas = datas;
        }
    }

    public void setHeight(float maxValue,int height) {
        if (datas != null) {
            for (int i = 0; i < datas.size(); i++) {
                /*通过柱状图的最大值和相对比例计算出每条柱状图的高度*/
                float barHeight = datas.get(i).getAllcount()/maxValue*height;
                View view = LayoutInflater.from(getContext()).inflate(R.layout.bar_item, null);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DensityUtil.dip2px(getContext(),30),height);
//                FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(DensityUtil.dip2px(getContext(),30),height);
//                view.setLayoutParams(lp);
                ((BarView) view.findViewById(R.id.barView)).setData(datas.get(i));
                (view.findViewById(R.id.barView)).setLayoutParams(lp);
                ((TextView)view.findViewById(R.id.title)).setText(getFeedString(datas.get(i).getTitle()));
                DecimalFormat mFormat=new DecimalFormat("##.#");
                ((TextView)view.findViewById(R.id.percent)).setText(mFormat.format(datas.get(i).getAllcount()));
                addView(view);
            }
        }
    }

    /*字符串換行*/
    private String getFeedString(String text){
        StringBuilder sb = new StringBuilder(text);
        sb.insert(2,"\n");
        return sb.toString();
    }
}
