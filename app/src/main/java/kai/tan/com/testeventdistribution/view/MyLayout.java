package kai.tan.com.testeventdistribution.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * 理解onInterceptTouchEvent 拦截和不拦截在同一事件序列中的调用次数
 * 拦截只会调用一次，不拦截每次事件都会被调用
 */
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
//        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        Log.d(TAG, "viewgroup-onInterceptTouchEvent-start: " + ev.getAction());
        boolean b = super.onInterceptTouchEvent(ev);
        Log.d(TAG, "viewgroup-onInterceptTouchEvent-return: " + ev.getAction() + "-" + b);
        return b;

//        return true;
        /*
         return true ：拦截事件
         1 子控件dispatchTouchEvent不会被调用
         2 如果当前view拦截了某个事件，那么在同一个事件序列（手指接触屏幕那一刻到手指离开屏幕的那一刻）此方法不会再次被调用。
              比如按下的时候拦截了，移动和抬起事件时就不会再调用这个方法。
          */


        /*
         拦截move事件
         button dispatchTouchEvent 出现cancel事件，有时间在深入。
         */
//        boolean b;
//        if(ev.getAction() == MotionEvent.ACTION_MOVE) {
//            b = true;
//        }else {
//            b = super.onInterceptTouchEvent(ev);
//        }
//        Log.d(TAG, "viewgroup-onInterceptTouchEvent-return: " + ev.getAction() + "-" + b);
//        return b;


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "viewgroup-onTouchEvent-start: " + event.getAction());
        boolean b = super.onTouchEvent(event);
        Log.d(TAG, "viewgroup-onTouchEvent-return: " + event.getAction() + "-" + b);
        return b;
    }
}
