package com.barchart.mpchartdemo;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.barchart.mpchartdemo.entity.BarDataEntity;

import java.text.DecimalFormat;

import static android.R.attr.format;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFragment extends Fragment implements View.OnClickListener{
    View itemView;
    LinearLayout container;
    Button bt_refresh;
    DecimalFormat format = new DecimalFormat("##.##");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        itemView = inflater.inflate(R.layout.view_fragment, container, false);
        bt_refresh = (Button) itemView.findViewById(R.id.bt_refresh);
        bt_refresh.setOnClickListener(this);
        bindData();
        return itemView;
    }

    public void bindData() {
        container = (LinearLayout) itemView.findViewById(R.id.container);
        container.removeAllViews();
        BarDataEntity data = new BarDataEntity();
        data.parseData();
        if (data == null || data.getTypeList() == null) {
            return;
        }
        int color = Color.parseColor("#3FA0FF");
        double maxScale = 0;
        for (int i = 0; i < data.getTypeList().size(); i++) {
            if (data.getTypeList().get(i).getTypeScale() > maxScale)
                maxScale = data.getTypeList().get(i).getTypeScale();
        }
        for (int i = 0; i < data.getTypeList().size(); i++) {
            final View item = LayoutInflater.from(itemView.getContext()).inflate(R.layout.h_bar_item, container, false);
            final BarDataEntity.Type type = data.getTypeList().get(i);
            ((TextView) item.findViewById(R.id.index)).setText("");
            ((TextView) item.findViewById(R.id.name)).setText(type.getTypeName());
            ((TextView) item.findViewById(R.id.index)).setText("" + i);
            final View bar = item.findViewById(R.id.bar);
            bar.setBackgroundColor(color);
            ((TextView) item.findViewById(R.id.percent)).setText(format.format(type.getTypeScale() * 100) + "%");
            ((TextView) item.findViewById(R.id.percent)).setTextColor(color);
            final double finalMaxScale = maxScale;
            item.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    item.getViewTreeObserver().removeOnPreDrawListener(this);
                    int barContainerWidth = item.findViewById(R.id.bar_container).getWidth();
                    int percentTxtWidth = item.findViewById(R.id.percent).getWidth();
                    final int initWidth = barContainerWidth - percentTxtWidth;
                    final LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) bar.getLayoutParams();
                    lp.width = (int) (initWidth * type.getTypeScale()/ finalMaxScale * 100 / 100);
                    bar.setLayoutParams(lp);
                    item.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            final int initWidth = bar.getWidth();
                            final ObjectAnimator anim = ObjectAnimator.ofFloat(bar, "alpha", 0.0F, 1.0F).setDuration(1500);
                            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    float cVal = (Float) anim.getAnimatedValue();
                                    lp.width = (int) (initWidth * cVal);
                                    bar.setLayoutParams(lp);
                                }
                            });
                            anim.start();
                        }
                    }, 0);
                    return false;
                }
            });
            container.addView(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_refresh:
                bindData();
                break;
        }
    }
}
