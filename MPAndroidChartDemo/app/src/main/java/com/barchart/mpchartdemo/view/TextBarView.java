package com.barchart.mpchartdemo.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.barchart.mpchartdemo.R;
import com.barchart.mpchartdemo.entity.TextBarDataEntity;

import java.text.DecimalFormat;
import java.util.Map;

/**
 * Created by zhangchanghua on 2017/6/15.
 */

public class TextBarView extends LinearLayout {
    public TextBarView(Context context) {
        super(context);
        setOrientation(VERTICAL);
    }

    public TextBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    public TextBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }

    DecimalFormat format = new DecimalFormat("##.##");
    PopupWindow popupWindow;
    View popView;

    public void init(TextBarDataEntity.Record record, int height, Map<String, Integer> nameColorMap) {
        if (record.sourceList == null && record.sourceList.isEmpty()) {
            return;
        }
        popView = LayoutInflater.from(getContext()).inflate(
                R.layout.pop_bg, null);
        //计算空白填充部分占比
        double blankScale = 1;
        for (int i = 0; i < record.sourceList.size(); i++) {
            blankScale -= record.sourceList.get(i).sourceNum;
        }
//        if (blankScale==1) {
//            TextView item = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.negative_sentiment_source_item_txt, this, false);
//            ViewGroup.LayoutParams lp = item.getLayoutParams();
//            lp.height = (int) (blankScale * height);
//            addView(item);
//            return;
//        }

        for (int i = 0; i < record.sourceList.size(); i++) {
            final TextView item = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.text_source_item_txt, this, false);
            final TextBarDataEntity.Record.Source source = record.sourceList.get(i);
            item.setText(source.sourceNum >= 5 ? format.format(source.sourceNum) + "%" : "");
            GradientDrawable bg = (GradientDrawable) getResources().getDrawable(R.drawable.n_s_bar_bg);
            bg.setColor(nameColorMap.get(source.sourceName));
            item.setBackground(bg);
            ViewGroup.LayoutParams lp = item.getLayoutParams();
            lp.height = (int) (source.sourceNum/100 * height);
            addView(item, lp);
        }
    }

    private void showPop(final View view) {
        if (popupWindow != null)
            popupWindow.dismiss();
        popupWindow = null;
        popupWindow = new PopupWindow(popView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(view, view.getWidth() / 2, view.getHeight() / 2);
    }
}
