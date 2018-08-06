package app.isfaaghyth.dictionary.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

import app.isfaaghyth.dictionary.R;

/**
 * Created by isfaaghyth on 8/7/18.
 * github: @isfaaghyth
 */

public class CircleView extends View
{

    private Paint paint;

    public CircleView(Context context)
    {
        super(context);
        init(context, null);
    }

    public CircleView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs)
    {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public int getRandomColor(){
        TypedArray colors = getResources().obtainTypedArray(R.array.material_colors);
        int index = (int) (Math.random() * colors .length());
        return colors.getColor(index, Color.BLACK);
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        int w = getWidth();
        int h = getHeight();

        int pl = getPaddingLeft();
        int pr = getPaddingRight();
        int pt = getPaddingTop();
        int pb = getPaddingBottom();

        int usableWidth = w - (pl + pr);
        int usableHeight = h - (pt + pb);

        int radius = Math.min(usableWidth, usableHeight) / 2;
        int cx = pl + (usableWidth / 2);
        int cy = pt + (usableHeight / 2);

        paint.setColor(getRandomColor());
        canvas.drawCircle(cx, cy, radius, paint);
    }
}