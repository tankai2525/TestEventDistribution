package kai.tan.com.testeventdistribution.view;


import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class MyButton2 extends AppCompatButton {

    private static final String TAG = "MyButton2";

    public MyButton2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        Log.d(TAG, "button-dispatchTouchEvent-start: " + event.getAction());
        boolean b = super.dispatchTouchEvent(event);
        Log.d(TAG, "button-dispatchTouchEvent-return: " + event.getAction() + "-" + b);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "button-onTouchEvent-start: " + event.getAction());
        boolean b = super.onTouchEvent(event);
        Log.d(TAG, "button-onTouchEvent-return: " + event.getAction() + "-" + b);
        return b;
//        return false;
        /*
        return false : 不消耗事件
        1 父控件会间接调用自己的onTouch, 实际过程是先调用自己的super.dispatchTouchEvent
        2 如果不消耗Action_Down事件,那么同一事件序列中的其他事件都不会在交给它来处理。也就是不在调用自己的dispatchTouchEvent
         */

//        boolean b;
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            b = super.onTouchEvent(event);//消耗down事件
//        } else {
//            b = false;//不消耗除down事件以外的事件
//        }
//        Log.d(TAG, "button-onTouchEvent-return: " + event.getAction() + "-" + b);
//        return b;
    }
}
