package kai.tan.com.testeventdistribution;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;


public class MyLayout extends LinearLayout {

    private static final String TAG = "MyLayout";

    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "viewgroup-dispatchTouchEvent-start: " + ev.getAction());
        boolean b = super.dispatchTouchEvent(ev);
        Log.d(TAG, "viewgroup-dispatchTouchEvent-return: " + ev.getAction() + "-" + b);
        return b;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "viewgroup-onInterceptTouchEvent-start: " + ev.getAction());
        boolean b = super.onInterceptTouchEvent(ev);
        Log.d(TAG, "viewgroup-onInterceptTouchEvent-return: " + ev.getAction() + "-" + b);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "viewgroup-onTouchEvent-start: " + event.getAction());
        boolean b = super.onTouchEvent(event);
        Log.d(TAG, "viewgroup-onTouchEvent-return: " + event.getAction() + "-" + b);
        return b;
    }
}
