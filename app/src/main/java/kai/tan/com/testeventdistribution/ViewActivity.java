package kai.tan.com.testeventdistribution;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 理解view事件分发（先要看明白ViewGroupActivity例子）
 * 理解OnTouchListener的onTouch调用过程
 *
 */
public class ViewActivity extends AppCompatActivity {

    private static final String TAG = "ViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        View root = findViewById(R.id.activity_main);//根view
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick-root");
                //当点击子按钮时，不会执行，被
            }
        });

        Button button = (Button) findViewById(R.id.button);

        /*
            这是view dispatchTouchEvent 方法中一段代码完全解释了 onTouch , onTouchEvent 什么时候调用

            如果view设置了OnTouchListener监听并且view是激活状态enable=true, 那么就调用onTouch方法
            if (li != null && li.mOnTouchListener != null
                    && (mViewFlags & ENABLED_MASK) == ENABLED
                    && li.mOnTouchListener.onTouch(this, event)) {
                result = true;
            }

            如果onTouch返回true，那么onTouchEvent就不会执行
            反之会执行onToucheEvent
            if (!result && onTouchEvent(event)) {
                result = true;
            }

         */
//        button.setEnabled(false);//不会执行onTouch，但是会执行onTouchEvent
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TAG", "onTouch execute, action " + event.getAction());
                return true;
            }
        });

        // view的onTouchEvent中处理了长点击和点击事件
        /*
        if (((viewFlags & CLICKABLE) == CLICKABLE ||
                (viewFlags & LONG_CLICKABLE) == LONG_CLICKABLE) ||
                (viewFlags & CONTEXT_CLICKABLE) == CONTEXT_CLICKABLE)
                view的这三个状态，有一个为true，就会处理长点击和点击事件，消费事件
                那也就是如果view拥有clickable、long_clickable、context_clickable其中一个状态，就能消费事件
         */
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d(TAG, "onLongClick: ");
                //当手按下去时（MotionEvent.ACTION_DOWN）会检测是否长点事件，超过500毫秒就会触发该方法
                return true;
                //如果返回true，则mHasPerformedLongPress=true，onClick就不会执行，请看view.onTouchEvent方法
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
            }
        });
//        button.setClickable(false);

        ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch: " + event.getAction());
                return true;
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                //img的onTouch返回true，这里不会执行
            }
        });



    }
}
