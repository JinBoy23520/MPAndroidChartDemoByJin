package com.barchart.mpchartdemo;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.barchart.mpchartdemo.entity.BarDataEntity;
import com.barchart.mpchartdemo.entity.BarEntity;
import com.barchart.mpchartdemo.entity.SourceEntity;
import com.barchart.mpchartdemo.entity.TextBarDataEntity;
import com.barchart.mpchartdemo.view.BarGroup;
import com.barchart.mpchartdemo.view.BarView;
import com.barchart.mpchartdemo.view.TextBarGroupView;
import com.nex3z.flowlayout.FlowLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFragment extends Fragment implements View.OnClickListener{
    View itemView;
    LinearLayout container;
    Button btRefresh,btRefresh1,btRefresh2;
    DecimalFormat format = new DecimalFormat("##.##");

    DecimalFormat mFormat = new DecimalFormat("##.####");

    private BarGroup barGroup;
    private HorizontalScrollView root;

    /*柱状图的最大值*/
    private float sourceMax = 0.00f;

    private int left;
    private int baseLineHeiht;
    private RelativeLayout.LayoutParams lp;
    private PopupWindow popupWindow;
    private View popView;
    TextBarDataEntity textBarDataEntity = new TextBarDataEntity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        itemView = inflater.inflate(R.layout.view_fragment, container, false);
        btRefresh = (Button) itemView.findViewById(R.id.bt_refresh);
        btRefresh1 = (Button) itemView.findViewById(R.id.bt_refresh1);
        btRefresh2 = (Button) itemView.findViewById(R.id.bt_refresh2);
        btRefresh.setOnClickListener(this);
        btRefresh1.setOnClickListener(this);
        btRefresh2.setOnClickListener(this);
        bindData();
        setBarChart();
        textBarData(textBarDataEntity);
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

    public void setBarChart(){
        barGroup = (BarGroup) itemView.findViewById(R.id.bar_group);
        root = (HorizontalScrollView) itemView.findViewById(R.id.bar_scroll);
        popView = LayoutInflater.from(getContext()).inflate(
                R.layout.pop_bg, null);

        final SourceEntity sourceEntity = new SourceEntity();
        sourceEntity.parseData();
        setYAxis(sourceEntity.getList());

        barGroup.removeAllViews();
        List<BarEntity> datas = new ArrayList<>();
        final int size = sourceEntity.getList().size();
        for (int i = 0; i < size; i++) {
            BarEntity barEntity = new BarEntity();
            SourceEntity.Source entity = sourceEntity.getList().get(i);
            String negative = mFormat.format(entity.getBadCount() / sourceMax);
            barEntity.setNegativePer(Float.parseFloat(negative));
            String neutral = mFormat.format(entity.getOtherCount() / sourceMax);
            barEntity.setNeutralPer(Float.parseFloat(neutral));
            String positive = mFormat.format(entity.getGoodCount() / sourceMax);
            barEntity.setPositivePer(Float.parseFloat(positive));
            barEntity.setTitle(entity.getSource());
            barEntity.setScale(entity.getScale());
            barEntity.setAllcount(entity.getAllCount());
            /*计算柱状图透明区域的比例*/
            barEntity.setFillScale(1 - entity.getAllCount() / sourceMax);
            datas.add(barEntity);
        }
        barGroup.setDatas(datas);
        //计算间距
        barGroup.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                barGroup.getViewTreeObserver().removeOnPreDrawListener(this);
                int height = itemView.findViewById(R.id.bg).getMeasuredHeight();
                final View baseLineView = itemView.findViewById(R.id.left_base_line);
                int baseLineTop = baseLineView.getTop();
                barGroup.setHeight(sourceMax, height - baseLineTop - baseLineView.getHeight() / 2);
                barGroup.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        BarView barItem = (BarView) barGroup.getChildAt(0).findViewById(R.id.barView);
                        baseLineHeiht = itemView.findViewById(R.id.base_line).getTop();
                        lp = (RelativeLayout.LayoutParams) root.getLayoutParams();
                        left = baseLineView.getLeft();
                        lp.leftMargin = (int) (left + getContext().getResources().getDisplayMetrics().density * 3);
                        lp.topMargin = Math.abs(baseLineHeiht - barItem.getHeight());
                        root.setLayoutParams(lp);
//                        final int initHeight = barItem.getHeight();
//                        final ObjectAnimator anim = ObjectAnimator.ofFloat(barItem, "zch", 0.0F, 1.0F).setDuration(1500);
//                        final LinearLayout.LayoutParams barLP= (LinearLayout.LayoutParams) barItem.getLayoutParams();
//                        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                            @Override
//                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                                float cVal = (Float) anim.getAnimatedValue();
//                                barLP.height = (int) (initHeight * cVal);
//                                barItem.setLayoutParams(barLP);
//                            }
//                        });
//                        anim.start();
                    }
                }, 0);

                for (int i = 0; i < size; i++) {
                    final BarView barItem = (BarView) barGroup.getChildAt(i).findViewById(R.id.barView);
                    final int finalI = i;
                    barItem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final float top = view.getHeight() - barItem.getFillHeight();
                            SourceEntity.Source ss = sourceEntity.getList().get(finalI);
                            String showText = "正面：" + (int) ss.getGoodCount() + "条\n"
                                    + "中性：" + (int) ss.getOtherCount() + "条\n"
                                    + "负面：" + (int) ss.getBadCount() + "条";
                            ((TextView) popView.findViewById(R.id.txt)).setText(showText);
                            showPop(barItem, top);
                        }
                    });
                }
                return false;
            }
        });
    }

    private int initPopHeitht = 0;

    private void showPop(final View barItem, final float top) {
        if (popupWindow != null)
            popupWindow.dismiss();
        popupWindow = null;
        popupWindow = new PopupWindow(popView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(barItem, barItem.getWidth() / 2, -((int) top + initPopHeitht));
        if (initPopHeitht == 0) {
            popView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    popView.getViewTreeObserver().removeOnPreDrawListener(this);
                    initPopHeitht = popView.getHeight();
                    popupWindow.update(barItem, barItem.getWidth() / 2, -((int) top + initPopHeitht),
                            popupWindow.getWidth(), popupWindow.getHeight());
                    return false;
                }
            });
        }
    }

    private void setYAxis(List<SourceEntity.Source> list) {
        sourceMax = list.get(0).getAllCount();
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).getAllCount() > sourceMax) {
                sourceMax = list.get(i).getAllCount();
            }
        }
        ((TextView) itemView.findViewById(R.id.tv_num1)).setText((int) sourceMax / 5 + "");
        ((TextView) itemView.findViewById(R.id.tv_num2)).setText((int) sourceMax * 2 / 5 + "");
        ((TextView) itemView.findViewById(R.id.tv_num3)).setText((int) sourceMax * 3 / 5 + "");
        ((TextView) itemView.findViewById(R.id.tv_num4)).setText((int) sourceMax * 4 / 5 + "");
        ((TextView) itemView.findViewById(R.id.tv_num5)).setText((int) sourceMax + "");
    }

    public void textBarData(final TextBarDataEntity data) {
        final FlowLayout sourceContainer= (FlowLayout) itemView.findViewById(R.id.container2);
        itemView.findViewById(R.id.bg_text).getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                itemView.findViewById(R.id.bg_text).getViewTreeObserver().removeOnPreDrawListener(this);
                if (data != null) {
//                    ((TextView) itemView.findViewById(R.id.tv_head_item)).setText(data.overviewName);
                    ((TextBarGroupView) itemView.findViewById(R.id.bar_group_text)).
                            init(data.getRecordList(), itemView.findViewById(R.id.item0).getHeight() * 5,sourceContainer);
                } else {
//                    ((TextView) itemView.findViewById(R.id.tv_head_item)).setText("");
                    ((TextBarGroupView) itemView.findViewById(R.id.bar_group_text)).
                            init(null, itemView.findViewById(R.id.item0).getHeight() * 5,sourceContainer);
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_refresh:
                bindData();
                break;
            case R.id.bt_refresh1:
                setBarChart();
                break;
            case R.id.bt_refresh2:
                TextBarDataEntity textBarDataEntity = new TextBarDataEntity(); //仿数据请求了
                textBarData(textBarDataEntity);
                break;
        }
    }
}
