package com.heruijun.baselibrary.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;

import com.github.florent37.mylittlecanvas.ShapeAnimator;
import com.github.florent37.mylittlecanvas.shape.RoundRectShape;

/**
 * Created by heruijun on 2018/3/2.
 */

public class UnderlinedTextView extends android.support.v7.widget.AppCompatTextView {

    final RoundRectShape roundRectShape = new RoundRectShape();
    final ShapeAnimator shapeAnimator = new ShapeAnimator(this);

    public UnderlinedTextView(Context context) {
        this(context, null);
    }

    public UnderlinedTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnderlinedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        roundRectShape
                .setCorderRadius(10)
                .setColor(Color.parseColor("#3F51B5"));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        roundRectShape
                .setWidth(w)
                .setHeight(10)
                .alignBottom(h);

        //clear olds anims
        shapeAnimator.clear();

        //start animation
        shapeAnimator
                .setRepeatCount(ValueAnimator.INFINITE)
                .setDuration(1000)
                .playTogether(
                        roundRectShape.animateLeft(roundRectShape.getLeft(), roundRectShape.getLeft() + 10, roundRectShape.getLeft()),
                        roundRectShape.animateRight(roundRectShape.getRight(), roundRectShape.getRight() - 10, roundRectShape.getRight())
                )
                .start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        roundRectShape.onDraw(canvas);
    }
}