package kai.tan.com.testeventdistribution.view;


import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * 这个按钮用来测试，多指触摸时，事件分发
 */
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
    }
}
