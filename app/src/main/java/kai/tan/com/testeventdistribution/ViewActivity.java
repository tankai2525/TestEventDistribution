package kai.tan.com.testeventdistribution;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 理解view事件分发
 */
public class ViewActivity extends AppCompatActivity {

    private static final String TAG = "ViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Button button = (Button) findViewById(R.id.button);

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TAG", "onTouch execute, action " + event.getAction());
                return false;
                //返回false, 会执行onTouchEvent, 点击和长点击事件才可能被执行；
                //返回true, 不执行onTouchEvent,  onTouch会被调用多次
            }
        });

//        button.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Log.d(TAG, "onLongClick: ");
//                //当手按下去时（MotionEvent.ACTION_DOWN）会检测是否长点事件，超过500毫秒就会触发该方法
//                return true;
//                //如果返回true，则mHasPerformedLongPress=true，onClick就不会执行，请看view.onTouchEvent方法
//            }
//        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
            }
        });

        ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch: " + event.getAction());
                return true;
            }
        });

//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: ");
//            }
//        });
//        img.setClickable(true);
//        img.setEnabled(false);


    }
}
