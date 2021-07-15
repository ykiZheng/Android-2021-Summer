package com.bytedance.camp.chapter4.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.bytedance.camp.chapter4.R;

public class ClockView extends View {

    private static final String TAG = "ClockView";

    private static final int FULL_CIRCLE_DEGREE = 360;
    private static final int UNIT_DEGREE = 6;

    private static final float UNIT_LINE_WIDTH = 8; // 刻度线的宽度
    private static final int HIGHLIGHT_UNIT_ALPHA = 0xFF;
    private static final int NORMAL_UNIT_ALPHA = 0x80;

    private static final float HOUR_NEEDLE_LENGTH_RATIO = 0.4f; // 时针长度相对表盘半径的比例
    private static final float MINUTE_NEEDLE_LENGTH_RATIO = 0.6f; // 分针长度相对表盘半径的比例
    private static final float SECOND_NEEDLE_LENGTH_RATIO = 0.8f; // 秒针长度相对表盘半径的比例
    private static final float HOUR_NEEDLE_WIDTH = 12; // 时针的宽度
    private static final float MINUTE_NEEDLE_WIDTH = 8; // 分针的宽度
    private static final float SECOND_NEEDLE_WIDTH = 4; // 秒针的宽度

    private static final int CIRCLE_RATIO = 20;

    private Calendar calendar = Calendar.getInstance();

    private float radius = 0; // 表盘半径
    private float centerX = 0; // 表盘圆心X坐标
    private float centerY = 0; // 表盘圆心Y坐标

    private int mTextColor = Color.WHITE;
    private int mClockColor = Color.BLUE;
    private int mTextSize = 60;


    private List<RectF> unitLinePositions = new ArrayList<>();
    private Paint unitPaint = new Paint();
    private Paint hourNeedlePaint = new Paint();
    private Paint minuteNeedlePaint = new Paint();
    private Paint secondNeedlePaint = new Paint();
    private Paint numberPaint = new Paint();
    private Paint circlePaint = new Paint();
    private Paint numTextPaint = new Paint();

    private Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
    private Paint.FontMetrics font2Metrics = new Paint.FontMetrics();

    public final int START_CLOCK = 1;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case START_CLOCK:
                    invalidate();
                    handler.sendEmptyMessageDelayed(START_CLOCK, 1000);
                    break;
            }
        }
    };

    public ClockView(Context context) {
        this(context, null);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ClockView);
        int n = ta.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = ta.getIndex(i);
            switch (attr){
                case R.styleable.ClockView_mTexColor:
                    mTextSize = ta.getColor(attr,Color.WHITE);
                    break;
                case R.styleable.ClockView_mClockColor:
                    mClockColor = ta.getColor(attr,Color.WHITE);
                    break;
                case R.styleable.ClockView_mTexSize:
                    mTextSize = ta.getDimensionPixelSize(attr,100);
                    break;
            }
        }
        ta.recycle();
        Log.v(TAG, "textColor: " + mTextColor);
        Log.v(TAG, "clockColor: " + mClockColor);
        Log.v(TAG, "textSize: " + mTextSize);

        unitPaint.setAntiAlias(true);
        unitPaint.setColor(Color.WHITE);
        unitPaint.setStrokeWidth(UNIT_LINE_WIDTH);
        unitPaint.setStrokeCap(Paint.Cap.ROUND);
        unitPaint.setStyle(Paint.Style.STROKE);

        // TODO 设置绘制时、分、秒针的画笔: needlePaint
        hourNeedlePaint.setAntiAlias(true);
        hourNeedlePaint.setAlpha(HIGHLIGHT_UNIT_ALPHA);
        hourNeedlePaint.setColor(mClockColor);
        hourNeedlePaint.setStyle(Paint.Style.FILL);
        hourNeedlePaint.setStrokeWidth(HOUR_NEEDLE_WIDTH);

        minuteNeedlePaint.setAntiAlias(true);
        minuteNeedlePaint.setAlpha(HIGHLIGHT_UNIT_ALPHA);
        minuteNeedlePaint.setColor(mClockColor);
        minuteNeedlePaint.setStyle(Paint.Style.FILL);
        minuteNeedlePaint.setStrokeWidth(MINUTE_NEEDLE_WIDTH);

        secondNeedlePaint.setAntiAlias(true);
        secondNeedlePaint.setAlpha(HIGHLIGHT_UNIT_ALPHA);
        secondNeedlePaint.setColor(Color.RED);
        secondNeedlePaint.setStyle(Paint.Style.FILL);
        secondNeedlePaint.setStrokeWidth(SECOND_NEEDLE_WIDTH);


        circlePaint.setAntiAlias(true);
        circlePaint.setColor(mClockColor);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setStrokeWidth(10);


        // TODO 设置绘制时间数字的画笔:
        numberPaint.setAntiAlias(true);
        numberPaint.setColor(mClockColor);
        numberPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        numberPaint.setTextSize(50);
        numberPaint.setTextAlign(Paint.Align.CENTER);
        numberPaint.setAlpha(HIGHLIGHT_UNIT_ALPHA);

        numTextPaint.setAntiAlias(true);
        numTextPaint.setColor(mTextColor);
        numTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        numTextPaint.setTextSize(mTextSize);
        numTextPaint.setTextAlign(Paint.Align.CENTER);
        numTextPaint.setAlpha(HIGHLIGHT_UNIT_ALPHA);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        configWhenLayoutChanged();
    }

    private void configWhenLayoutChanged() {
        float newRadius = Math.min(getWidth(), getHeight()) / 2f;
        if (newRadius == radius) {
            return;
        }
        radius = newRadius;
        centerX = getWidth() / 2f;
        centerY = getHeight() / 2f;

        // 当视图的宽高确定后就可以提前计算表盘的刻度线的起止坐标了
        for (int degree = 0; degree < FULL_CIRCLE_DEGREE; degree += UNIT_DEGREE) {
            double radians = Math.toRadians(degree);
            float startX = (float) (centerX + (radius * (1 - 0.05f)) * Math.cos(radians));
            float startY = (float) (centerX + (radius * (1 - 0.05f)) * Math.sin(radians));
            float stopX = (float) (centerX + radius * Math.cos(radians));
            float stopY = (float) (centerY + radius * Math.sin(radians));
            unitLinePositions.add(new RectF(startX, startY, stopX, stopY));
        }
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        drawUnit(canvas);
        drawTimeNeedles(canvas);
        drawText(canvas);
        drawCircle(canvas);
        drawTimeNumbers(canvas);
        // TODO 实现时间的转动，每一秒刷新一次
        handler.sendEmptyMessageDelayed(START_CLOCK, 1000);
    }

    // 绘制表盘上的刻度
    private void drawUnit(Canvas canvas) {
        for (int i = 0; i < unitLinePositions.size(); i++) {
            if (i % 5 == 0) {
                unitPaint.setAlpha(NORMAL_UNIT_ALPHA);
            } else {
                unitPaint.setAlpha(NORMAL_UNIT_ALPHA);
            }
            RectF linePosition = unitLinePositions.get(i);
            canvas.drawLine(linePosition.left, linePosition.top, linePosition.right, linePosition.bottom, unitPaint);
        }
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(centerX, centerY, CIRCLE_RATIO, circlePaint);
    }

    void drawText(Canvas canvas) {
        Time time = getCurrentTime();
        int hour = time.getHours();
        int minute = time.getMinutes();
        int second = time.getSeconds();

        String curTime = String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second);


        numTextPaint.setAlpha(NORMAL_UNIT_ALPHA);
        numTextPaint.getFontMetrics(font2Metrics);
        float height = fontMetrics.descent - font2Metrics.ascent;

        canvas.drawText(curTime, centerX, centerY - height / 2 + radius / 2, numTextPaint);

    }

    private void drawTimeNeedles(Canvas canvas) {
        Time time = getCurrentTime();
        int hour = time.getHours();
        int minute = time.getMinutes();
        int second = time.getSeconds();

        // TODO 根据当前时间，绘制时针、分针、秒针
        /*
         * 思路：
         * 1、以时针为例，计算从0点（12点）到当前时间，时针需要转动的角度
         * 2、根据转动角度、时针长度和圆心坐标计算出时针终点坐标（起始点为圆心）
         * 3、从圆心到终点画一条线，此为时针
         * 注1：计算时针转动角度时要把时和分都得考虑进去
         * 注2：计算坐标时需要用到正余弦计算，请用Math.sin()和Math.cos()方法
         * 注3：Math.sin()和Math.cos()方法计算时使用不是角度而是弧度，所以需要先把角度转换成弧度，
         *     可以使用Math.toRadians()方法转换，例如Math.toRadians(180) = 3.1415926...(PI)
         * 注4：Android视图坐标系的0度方向是从圆心指向表盘3点方向，指向表盘的0点时是-90度或270度方向，要注意角度的转换
         */


        int hourDegree = (int) (hour * 1.0 + minute * 1.0 / 60 + second * 1.0 / 3600) * 360 / 12 - 90;
        float endX = (float) (centerX + radius * HOUR_NEEDLE_LENGTH_RATIO * Math.cos(Math.toRadians(hourDegree)));
        float endY = (float) (centerX + radius * HOUR_NEEDLE_LENGTH_RATIO * Math.sin(Math.toRadians(hourDegree)));
        canvas.drawLine(centerX, centerY, endX, endY, hourNeedlePaint);

        int minuteDegree = (int) (minute * 1.0 + second * 1.0 / 60) * 360 / 60 - 90;
        endX = (float) (centerX + radius * MINUTE_NEEDLE_LENGTH_RATIO * Math.cos(Math.toRadians(minuteDegree)));
        endY = (float) (centerX + radius * MINUTE_NEEDLE_LENGTH_RATIO * Math.sin(Math.toRadians(minuteDegree)));
        canvas.drawLine(centerX, centerY, endX, endY, minuteNeedlePaint);

        int secondDegree = second * 360 / 60 - 90;
        endX = (float) (centerX + radius * SECOND_NEEDLE_LENGTH_RATIO * Math.cos(Math.toRadians(secondDegree)));
        endY = (float) (centerX + radius * SECOND_NEEDLE_LENGTH_RATIO * Math.sin(Math.toRadians(secondDegree)));
        canvas.drawLine(centerX, centerY, endX, endY, secondNeedlePaint);

    }

    private void drawTimeNumbers(Canvas canvas) {
        // TODO 绘制表盘时间数字（可选）

        numberPaint.setAlpha(HIGHLIGHT_UNIT_ALPHA);
        numberPaint.getFontMetrics(fontMetrics);
        float height = fontMetrics.descent - fontMetrics.ascent;

        for (int i = 0; i < 12; i++) {
            float Degree = i * 30 - 60;
            String number = String.valueOf(i + 1);
            float width = numberPaint.measureText(number);


            float textX = (float) (centerX + (radius * 0.8f + width / 2f) * Math.cos(Math.toRadians(Degree)));
            float textY = (float) (centerY + (radius * 0.8f + height / 2f) * Math.sin(Math.toRadians(Degree)));

            canvas.drawText(number, textX, textY, numberPaint);
        }
    }

    // 获取当前的时间：时、分、秒
    private Time getCurrentTime() {
        calendar.setTimeInMillis(System.currentTimeMillis());
        return new Time(
                calendar.get(Calendar.HOUR),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND));
    }

    
}
