package com.barchart.mpchartdemo.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.barchart.mpchartdemo.R;
import com.barchart.mpchartdemo.entity.TextBarDataEntity;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by zhangchanghua on 2017/6/15.
 */

public class TextBarGroupView extends LinearLayout {
    public TextBarGroupView(Context context) {
        super(context);
        setOrientation(HORIZONTAL);
    }

    public TextBarGroupView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
    }

    public TextBarGroupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
    }


    String other = "其它";

    public void init(final List<TextBarDataEntity.Record> datas, final int barHeight, FlowLayout sourceContainer) {
        removeAllViews();
        if (datas == null || datas.isEmpty()) {
            return;
        }
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#3fa0ff"));
        colors.add(Color.parseColor("#98b3e5"));
        colors.add(Color.parseColor("#d7546d"));
        colors.add(Color.parseColor("#51d4c4"));
        colors.add(Color.parseColor("#6d43cc"));
        colors.add(Color.parseColor("#ffb256"));
        colors.add(Color.parseColor("#69390e"));
        colors.add(Color.parseColor("#7ab024"));
        colors.add(Color.parseColor("#a7d0c8"));
        colors.add(Color.parseColor("#a29258"));
        colors.add(Color.parseColor("#297350"));
        colors.add(Color.parseColor("#eebdc7"));
        colors.add(Color.parseColor("#bb59d0"));

        List<TextBarDataEntity.Record.Source> allSourceList = new ArrayList<>();
        List<String> allSourceNameList = new ArrayList<>();
        Map<String, Integer> nameColorMap = new HashMap<>();
        final int lineHeight = (int) getResources().getDisplayMetrics().density * 1;
        for (int i = 0; i < datas.size(); i++) {
            //加载所有来源，去重复
            List<TextBarDataEntity.Record.Source> sourceList = datas.get(i).getSourceList();
            if (sourceList != null && !sourceList.isEmpty()) {
                int j = 0;
                for (TextBarDataEntity.Record.Source entry : sourceList) {
                    if (!nameColorMap.containsKey(entry.getSourceName())) {
                        Integer colorValue = colors.get(j % colors.size());
                        if (!nameColorMap.containsValue(colorValue)) {
                            nameColorMap.put(entry.getSourceName(), colorValue);
                        } else {
                            int index=colors.indexOf(colorValue);
                            for(int x=index;x<colors.size();x++){
                                Integer colorValue1 = colors.get(x % colors.size());
                                if(!nameColorMap.containsValue(colorValue1)){
                                    nameColorMap.put(entry.getSourceName(), colorValue1);
                                    break;
                                }
                            }
                        }
                    }
                    if (!allSourceNameList.contains(entry.getSourceName())) {
                        allSourceNameList.add(entry.getSourceName());
                        allSourceList.add(entry);
                    }
                    j++;
                }
                Collections.reverse(sourceList);
            }
        }
        initAllSourceLayout(allSourceList, sourceContainer, nameColorMap);
        for (int i = 0; i < datas.size(); i++) {
            final View item = LayoutInflater.from(getContext()).inflate(R.layout.text_source_item_group, this, false);
            final TextBarView barView = (TextBarView) item.findViewById(R.id.barview);
            barView.init(datas.get(i), barHeight + lineHeight, nameColorMap);
            ((TextView) item.findViewById(R.id.time)).setText(datas.get(i).getTimeScale());
            item.findViewById(R.id.time).setMinimumWidth((int) (getResources().getDisplayMetrics().density*80));
            if (i == 0) {
                final LayoutParams lp = (LayoutParams) item.getLayoutParams();
                lp.leftMargin = 0;
                addView(item, lp);
            } else {
                addView(item);
            }
            final View coverView = item.findViewById(R.id.cover);
            ViewGroup.LayoutParams lp = coverView.getLayoutParams();
            lp.height = barHeight;
            coverView.setLayoutParams(lp);
            //动画
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    final int initCoverHeight = coverView.getHeight();
                    ObjectAnimator anim = ObjectAnimator.ofFloat(coverView, "translationY", 0, -initCoverHeight);
                    anim.setDuration(1000);
                    anim.start();
                }
            }, (i + 1) * 500);
        }

        getViewTreeObserver().

                addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        getViewTreeObserver().removeOnPreDrawListener(this);
                        HorizontalScrollView.LayoutParams lp = (HorizontalScrollView.LayoutParams) getLayoutParams();
                        lp.topMargin = barHeight / 5 / 2 - lineHeight;
                        setLayoutParams(lp);
                        return false;
                    }
                });

    }

    private void initAllSourceLayout(List<TextBarDataEntity.Record.Source> list, FlowLayout sourceContainer, Map<String, Integer> nameColorMap) {
        sourceContainer.removeAllViews();
        for (TextBarDataEntity.Record.Source source : list) {
            View item = LayoutInflater.from(getContext()).inflate(R.layout.pie_lable_item, sourceContainer, false);
            GradientDrawable bg = (GradientDrawable) item.findViewById(R.id.icon).getBackground();
            TextView txt = (TextView) item.findViewById(R.id.txt);
            bg.setColor(nameColorMap.get(source.getSourceName()));
            item.findViewById(R.id.icon).setBackground(bg);
            txt.setText(source.getSourceName());
            sourceContainer.addView(item);
        }
    }

}
