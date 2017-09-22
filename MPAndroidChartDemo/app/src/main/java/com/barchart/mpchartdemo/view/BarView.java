package com.barchart.mpchartdemo.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.barchart.mpchartdemo.entity.BarEntity;


/**
 * Created by chenjie05 on 2016/12/14.
 */

public class BarView extends View {
    private BarEntity data;
    private Paint paint;
    private float animTimeCell = 0;

    public BarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BarView(Context context) {
        super(context);
        init();
    }

    public BarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
    }

    public void setData(BarEntity data) {
        this.data = data;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (data != null) {
            //高度填充
            fillHeight = data.getFillScale() * getHeight();
            if (fillHeight != 0) {
                paint.setColor(Color.TRANSPARENT);
                canvas.drawRect(0f, 0f, getWidth(), fillHeight, paint);
                canvas.translate(0f, fillHeight);
            }
            //负面
            paint.setColor(Color.parseColor(data.negativeColor));
            canvas.drawRect(0f, 0f, getWidth(), data.getNegativePer() * getHeight(), paint);
            canvas.translate(0f, data.getNegativePer() * getHeight());
            //中性
            paint.setColor(Color.parseColor(data.neutralColor));
            canvas.drawRect(0f, 0f, getWidth(), data.getNeutralPer() * getHeight(), paint);
            canvas.translate(0f, data.getNeutralPer() * getHeight());
            //正面
            paint.setColor(Color.parseColor(data.positiveColor));
            canvas.drawRect(0f, 0f, getWidth(), data.getPositivePer() * getHeight(), paint);
            canvas.translate(0f, data.getPositivePer() * getHeight());
        }
    }

    private float fillHeight;

    public float getFillHeight() {
        return fillHeight;
    }

    public void startAnim(int animTime) {
        final ObjectAnimator anim = ObjectAnimator.ofFloat(this, "alpha", 0.0F, 1.0F).setDuration(animTime);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                animTimeCell = (Float) anim.getAnimatedValue();
                invalidate();
            }
        });
    }
}
