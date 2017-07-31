package kai.tan.com.testeventdistribution.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * 理解onInterceptTouchEvent
 * 理解onInterceptTouchEvent 什么时候会被调用，什么时候不会在调用了
 *      第一次down事件开始，FLAG_DISALLOW_INTERCEPT标记位会清空（mGroupFlags &= ~FLAG_DISALLOW_INTERCEPT），肯定会被调用
 *      非down事件开始时，mFirstTouchTarget != null 并且 FLAG_DISALLOW_INTERCEPT标记位false, 会被调用
 *
 * 理解这种情况，当是down事件时或者mFirstTouchTarget != null时 ：我们会获取控件的FLAG_DISALLOW_INTERCEPT标记位
 *      FLAG_DISALLOW_INTERCEPT标记位为false, 代表可能要拦截，onInterceptTouchEvent会被调用，最终要不要拦截取决于onInterceptTouchEvent返回值
 *      FLAG_DISALLOW_INTERCEPT标记位为true, 代表不拦截，onInterceptTouchEvent不会调用
 *
 * 理解父控件一旦开始拦截任何一个事件，那么后续的事件都会交给它来处理。onInterceptTouchEvent不会再被调用
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


        /*
         场景1
         return true ：拦截所有事件
         1 子控件dispatchTouchEvent不会被调用
         2 如果当前view拦截了某个事件，那么在同一个事件序列（手指接触屏幕那一刻到手指离开屏幕的那一刻）此方法不会再次被调用。
              比如按下的时候拦截了，移动和抬起事件时就不会再调用这个方法。
          */
//        return true;


        /*
         场景2
         拦截move事件
         */
//        boolean b;
//        if(ev.getAction() == MotionEvent.ACTION_MOVE) {
//            b = true;// 拦截move事件
//        }else {
//            b = false;// 其他事件不拦截
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
