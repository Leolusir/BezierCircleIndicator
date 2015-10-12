package com.leo.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by littleming on 10/12/15.
 */
public class BezierCircle extends View {
    public static final float MAGIC_NUMBER = 0.551915024494f;
    public static final int DEFAULT_COLOR = Color.BLUE;
    public static final int DEFAULT_VISIBLE_COUNT = 3;

    private int circleColor;

    private Point leftPoint, rightPoint;
    private Point topPoint, bottomPoint;
    private Point centerPoint;

    private int radius;
    private int maxStretch;
    private int perWidth;
    private int visibleCount;
    private boolean notMeasure;

    private Paint paint;
    private Path path;

    public BezierCircle(Context context) {
        super(context);
        init();
    }

    public BezierCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init();
    }

    public BezierCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        init();
    }

    private void initAttrs(Context context, AttributeSet attrs){
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BezierCircle, 0, 0);
        visibleCount = a.getInteger(R.styleable.BezierCircle_visible_count, DEFAULT_VISIBLE_COUNT);
        circleColor = a.getColor(R.styleable.BezierCircle_circle_color, DEFAULT_COLOR);
        a.recycle();
    }

    private void init(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(circleColor);

        path = new Path();

        leftPoint = new Point();
        rightPoint = new Point();
        topPoint = new Point();
        bottomPoint = new Point();
        centerPoint = new Point();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if(notMeasure){
            return;
        }
        int width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        int height = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

        perWidth = width / visibleCount;
        centerPoint.x = centerPoint.initX = perWidth / 2 + getPaddingLeft();
        centerPoint.y = centerPoint.initY = height / 2 + getPaddingTop();
        radius = height / 2;
        maxStretch = radius;

        leftPoint.x = leftPoint.initX = centerPoint.x - radius;
        leftPoint.y = leftPoint.initY = centerPoint.y;

        rightPoint.x = rightPoint.initX = centerPoint.x + radius;
        rightPoint.y = rightPoint.initY = centerPoint.y;

        topPoint.x = topPoint.initX = centerPoint.x;
        topPoint.y = topPoint.initY = centerPoint.y - radius;

        bottomPoint.x = bottomPoint.initX = centerPoint.x;
        bottomPoint.y = bottomPoint.initY = centerPoint.y + radius;

        drawPath();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }

    private void drawPath(){
        float stretch = maxStretch * MAGIC_NUMBER;
        path.reset();
        path.moveTo(leftPoint.x, leftPoint.y);
        path.cubicTo(leftPoint.x, leftPoint.y - stretch, topPoint.x - stretch, topPoint.y, topPoint.x, topPoint.y);
        path.cubicTo(topPoint.x + stretch, topPoint.y, rightPoint.x, rightPoint.y - stretch, rightPoint.x, rightPoint.y);
        path.cubicTo(rightPoint.x, rightPoint.y + stretch, bottomPoint.x + stretch, bottomPoint.y, bottomPoint.x, bottomPoint.y);
        path.cubicTo(bottomPoint.x - stretch, bottomPoint.y, leftPoint.x, leftPoint.y + stretch, leftPoint.x, leftPoint.y);
    }

    public void transfer(float offset, int position){
        notMeasure = true;
        int preDistance = position * perWidth;
        if(offset >= 0.0f && offset <= 0.2f){
            step1(offset, preDistance);
        }else if(offset > 0.2f && offset <= 0.4f){
            step2(offset - 0.2f, preDistance);
        }else if(offset > 0.4f && offset <= 0.6f){
            step3(offset - 0.4f, preDistance);
        }else if(offset > 0.6f && offset <= 0.8f){
            step4(offset - 0.6f, preDistance);
        }else if(offset > 0.8f && offset <= 1.0f){
            step5(offset - 0.8f, preDistance);
        }

        drawPath();

        BezierCircle.this.invalidate();
    }

    private float calculateStretchLength(float offset){
        return offset * maxStretch * 2;
    }

    private void step1(float offset, float preD){
        leftPoint.transX(preD, 0);
        rightPoint.x = leftPoint.x + radius * 2 + calculateStretchLength(offset);
        topPoint.x = leftPoint.x + radius;
        bottomPoint.x = leftPoint.x + radius;
    }

    private void step2(float offset, float preD){
        leftPoint.transX(preD, 0);
        rightPoint.x = leftPoint.x + radius * 2 + calculateStretchLength(0.2f) + calculateStretchLength(offset);
        topPoint.x = leftPoint.x + radius + calculateStretchLength(offset);
        bottomPoint.x = leftPoint.x + radius + calculateStretchLength(offset);
    }

    private void step3(float offset, float preD){
        float maxTransLength = perWidth - calculateStretchLength(0.2f) * 2;
        leftPoint.transX(offset / 0.2f * maxTransLength + preD, 0);
        rightPoint.x = leftPoint.x + radius * 2 + calculateStretchLength(0.2f) * 2;
        topPoint.x = leftPoint.x + radius + calculateStretchLength(0.2f);
        bottomPoint.x = leftPoint.x + radius + calculateStretchLength(0.2f);
    }

    private void step4(float offset, float preD){
        float maxTransLength = perWidth - calculateStretchLength(0.2f) * 2;
        leftPoint.transX(preD + maxTransLength + calculateStretchLength(offset), 0);
        rightPoint.transX(preD + perWidth, 0);
        topPoint.x = rightPoint.x - radius - calculateStretchLength(0.2f - offset);
        bottomPoint.x = rightPoint.x - radius - calculateStretchLength(0.2f - offset);
    }

    private void step5(float offset, float preD){
        float maxTransLength = perWidth - calculateStretchLength(0.2f) * 2;
        leftPoint.transX(preD + maxTransLength + calculateStretchLength(0.2f) + calculateStretchLength(offset), 0);
        rightPoint.transX(preD + perWidth, 0);
        topPoint.x = rightPoint.x - radius;
        bottomPoint.x = rightPoint.x - radius;
    }

    class Point {
        protected float x;
        protected float y;
        protected float initX;
        protected float initY;

        public Point(){

        }
        public void transX(float distance, float offsetDistance){
            this.x = initX + distance + offsetDistance;
        }
        public String toString(){
            return x + "  -  " + y;
        }
    }
}
