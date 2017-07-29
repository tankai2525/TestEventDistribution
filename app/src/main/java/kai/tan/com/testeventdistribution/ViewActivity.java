package kai.tan.com.testeventdistribution;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 理解view事件分发（先要看明白ViewGroupActivity例子）
 * 理解OnTouchListener的onTouch调用过程
 * 理解OnTouchEvent的处理
 * 理解view的四中状态：enable、clickable、long_clickable、context_clickable 在事件分发中起到作用
 */
public class ViewActivity extends AppCompatActivity {

    private static final String TAG = "ViewActivity";
    private Button button;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        View root = ((ViewGroup)getWindow().getDecorView().findViewById(android.R.id.content)).getChildAt(0);
//        View root1 = findViewById(R.id.activity_main);//根view
//        Log.d(TAG, "root==root1:" + (root == root1));//是true
        //场景：当父控件clickable=true, 子控件clickable=true，点击子控件时，只会执行子控件onClick方法，为啥？
        /*
            因为
            1 安卓的事件分发最终只会交给一个view去处理
            2 子控件处理点击事件
         */
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick-root");
                /*
                当点击子控件，父控件无拦截，子控件enable=false （子控件onTouch不会执行）时
                    子控件三种点击状态（clickable、long_clickable、context_clickable）有一个为true，onTouchEvent返回true，事件被子控件处理，父控件onClick不会执行，子控件onClick不会执行
                    子控件三种点击状态（clickable、long_clickable、context_clickable）全为false，onTouchEvent返回false, 事件没被子控件处理，父控件onClick会执行，子控件onClick不会执行
                 */
                /*
                当点击子控件，父控件无拦截，子控件enable=true 子控件onTouch返回false 时
                    子控件三种点击状态（clickable、long_clickable、context_clickable）有一个为true，onTouchEvent返回true，事件被子控件处理，父控件onClick不会执行，子控件onClick会执行
                    子控件三种点击状态（clickable、long_clickable、context_clickable）全为false，onTouchEvent返回false，事件没被子控件处理，父控件onClick会执行，子控件onClick不会执行
                 */
            }
        });

        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);//button1 在 button上面

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
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TAG", "onTouch execute, action " + event.getAction());
//                return true;//代表我要在onTouch中处理掉这个事件，不会执行onTouchEvent方法,也就是不会执行点击和长点击处理
                return false;
            }
        });

        // view的onTouchEvent中处理了长点击和点击事件
        /*
        if (((viewFlags & CLICKABLE) == CLICKABLE ||
                (viewFlags & LONG_CLICKABLE) == LONG_CLICKABLE) ||
                (viewFlags & CONTEXT_CLICKABLE) == CONTEXT_CLICKABLE)
                view三种点击状态，有一个为true，就会处理长点击和点击事件，消费这次事件
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
                Log.d(TAG, "onClick: button");
            }
        });

        //打印view三种able状态值
        Log.d(TAG, "clickable:" + button.isClickable() + " long_clickable:" + button.isLongClickable());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d(TAG, "context_clickable:" + button.isContextClickable());
        }
//        button.setEnabled(false);//enable=false时，不会执行onTouch，但是会执行onTouchEvent
//        button.setClickable(false);
//        button.setLongClickable(false);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            button.setContextClickable(false);
//        }

        //下面代码是onTouch返回true，屏蔽onTouchEvent例子
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.d(TAG, "buttonZ: " + button.getZ() + " button1Z:" + button1.getZ());
            //这里button 和 button1 的getZ()都是等于6.0
            //ViewGroup源码中 buildTouchDispatchChildList()集合中的顺序： button在button1前面。

            /*
             final ArrayList<View> preorderedList = buildOrderedChildList();//返回一个按Z属性大小排序的集合，从小到大。
                        final boolean customOrder = preorderedList == null
                                && isChildrenDrawingOrderEnabled();
                        final View[] children = mChildren;
                        for (int i = childrenCount - 1; i >= 0; i--) {
                            final int childIndex = customOrder
                                    ? getChildDrawingOrder(childrenCount, i) : i;
                            final View child = (preorderedList == null)
                                    ? children[childIndex] : preorderedList.get(childIndex);
                            .....省略
                        }
             */
            /*
            上面是ViewGroup给子view下发事件代码，先给谁发呢， for循环是从最后开始遍历
            当前集合preorderedList是这样[imageView, button, button1] 那么就是会从button1开始，所以在我们点击button1的时候会现分发事件给button1。
             */

        }
    }
}
