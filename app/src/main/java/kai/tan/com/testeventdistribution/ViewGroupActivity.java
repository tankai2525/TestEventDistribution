package kai.tan.com.testeventdistribution;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import kai.tan.com.testeventdistribution.view.MyButton;
import kai.tan.com.testeventdistribution.view.MyButton2;
import kai.tan.com.testeventdistribution.view.MyLayout;

/**
 * 理解Activity事件分发
 * 理解GroupView事件分发
 * 理解View事件分发
 */
public class ViewGroupActivity extends AppCompatActivity {

    private static final String TAG = "ViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewgroup);
        MyLayout myLayout = (MyLayout) findViewById(R.id.my_layout);
        MyButton button1 = (MyButton) findViewById(R.id.button1);
        MyButton2 button2 = (MyButton2) findViewById(R.id.button2);

//        myLayout.requestDisallowInterceptTouchEvent(true);

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
        //getWindow().superDispatchTouchEvent(ev) 返回false这里才会执行
        Log.d(TAG, "activity-onTouchEvent-start: " + event.getAction());
        boolean b = super.onTouchEvent(event);
        Log.d(TAG, "activity-onTouchEvent-return: " + event.getAction() + "-" + b);
        return b;
    }
}
