package kai.tan.com.testeventdistribution;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

public class MyButton extends Button {

    private static final String TAG = "MyButton";

    public MyButton(Context context, AttributeSet attrs) {
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
