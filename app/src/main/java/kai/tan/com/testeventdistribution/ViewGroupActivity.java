package kai.tan.com.testeventdistribution;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * 理解事件分发
 */
public class ViewGroupActivity extends AppCompatActivity {

    private static final String TAG = "ViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewgroup);
        LinearLayout myLayout = (LinearLayout) findViewById(R.id.my_layout);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);

        myLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch: myLayout-" + event.getAction());
                return false;
            }
        });

        myLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: mylaout");
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: button1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: button2");
            }
        });

        button2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch: button2-"+ event.getAction());
                return false;
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "activity-dispatchTouchEvent-start: " + ev.getAction());
        boolean b = super.dispatchTouchEvent(ev);
        //Activity内部dispathcTouchEvent(ev)调用流程：
        //Activity.dispatchTouchEvent(ev) -> PhoneWindow.superDispatchTouchEvent(ev) -> DecorView.superDispatchTouchEvent(ev)
        //-> DecorView是继承Framelayout -> 最后调用ViewGroup.dispatchTouchEvent
        Log.d(TAG, "activity-dispatchTouchEvent-return: " + ev.getAction() + "-" + b);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "activity-onTouchEvent-start: " + event.getAction());
        boolean b = super.onTouchEvent(event);
        Log.d(TAG, "activity-onTouchEvent-return: " + event.getAction() + "-" + b);
        return b;
    }
}
