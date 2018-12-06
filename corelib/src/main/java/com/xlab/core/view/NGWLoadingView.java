package com.xlab.core.view;


import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.xlab.base.utils.DisplayUtils;
import com.xlab.core.R;

/**
 * Created by zhxh on 2018/11/12
 */
public class NGWLoadingView extends View {

    public static final int PROGRESS = 0;
    public static final int LOADING = 1;
    private static final int LINE_COUNT = 12;
    private static final int DEGREE_PER_LINE = 360 / LINE_COUNT;
    private int mSize;
    private int mPaintColor;
    private int mAnimateValue = 0;
    private ValueAnimator mAnimator;
    private Paint mPaint;
    private RectF oval;
    private int progressStyle;
    private ValueAnimator.AnimatorUpdateListener mUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            mAnimateValue = (int) animation.getAnimatedValue();
            invalidate();
        }
    };

    public NGWLoadingView(Context context) {
        this(context, null);
    }

    public NGWLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.common_NGWLoadingStyle);
    }

    public NGWLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DisplayUtils.init(context);
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.common_NGWLoadingView, defStyleAttr, 0);
        mSize = array.getDimensionPixelSize(R.styleable.common_NGWLoadingView_common_ngw_loading_view_size, DisplayUtils.dp2px(40));
        mPaintColor = array.getInt(R.styleable.common_NGWLoadingView_common_ngw__android_color, Color.WHITE);
        progressStyle = array.getInt(R.styleable.common_NGWLoadingView_common_ngw_loading_progress_style, PROGRESS);
        array.recycle();
        initPaint();
    }

    public NGWLoadingView(Context context, int size, int color) {
        super(context);
        mSize = size;
        mPaintColor = color;
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(mPaintColor);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        int strikeWith = 8;
        mPaint.setStrokeWidth(strikeWith);
        oval = new RectF(strikeWith, strikeWith, mSize - strikeWith, mSize - strikeWith);

    }

    public void setColor(int color) {
        mPaintColor = color;
        mPaint.setColor(color);
        invalidate();
    }

    public void setSize(int size) {
        mSize = size;
        requestLayout();
    }

    public void start() {
        if (mAnimator == null) {
            mAnimator = ValueAnimator.ofInt(0, LINE_COUNT - 1);
            mAnimator.addUpdateListener(mUpdateListener);
            mAnimator.setDuration(600);
            mAnimator.setRepeatMode(ValueAnimator.RESTART);
            mAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mAnimator.setInterpolator(new LinearInterpolator());
            mAnimator.start();
        } else if (!mAnimator.isStarted()) {
            mAnimator.start();
        }
    }

    public void stop() {
        if (mAnimator != null) {
            mAnimator.removeUpdateListener(mUpdateListener);
            mAnimator.removeAllUpdateListeners();
            mAnimator.cancel();
            mAnimator = null;
        }
    }

    private void drawLoading(Canvas canvas, int rotateDegrees) {
        int width = mSize / 12, height = mSize / 6;
        mPaint.setStrokeWidth(width);

        canvas.rotate(rotateDegrees, mSize / 2, mSize / 2);
        canvas.translate(mSize / 2, mSize / 2);

        for (int i = 0; i < LINE_COUNT; i++) {
            canvas.rotate(DEGREE_PER_LINE);
            mPaint.setAlpha((int) (255f * (i + 1) / LINE_COUNT));
            canvas.translate(0, -mSize / 2 + width / 2);
            canvas.drawLine(0, 0, 0, height, mPaint);
            canvas.translate(0, mSize / 2 - width / 2);
        }
    }

    private void drawProgressLoading(Canvas canvas, int rotateDegrees) {
        canvas.rotate(rotateDegrees, mSize / 2, mSize / 2);

        canvas.drawArc(oval, 200, 120, false, mPaint);
        canvas.drawArc(oval, 20, 120, false, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mSize, mSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int saveCount = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        if (progressStyle == PROGRESS) {
            drawProgressLoading(canvas, mAnimateValue * DEGREE_PER_LINE);
        } else if (progressStyle == LOADING) {
            drawLoading(canvas, mAnimateValue * DEGREE_PER_LINE);
        }
        canvas.restoreToCount(saveCount);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == VISIBLE) {
            start();
        } else {
            stop();
        }
    }

    public void setStyle(@LoadingStyle int style) {
        progressStyle = style;
    }

    @IntDef(flag = true, value = {PROGRESS, LOADING})
    public @interface LoadingStyle {
    }
}