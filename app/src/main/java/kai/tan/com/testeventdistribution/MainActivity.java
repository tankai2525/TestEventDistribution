package kai.tan.com.testeventdistribution;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.button:
                intent = new Intent(MainActivity.this, ViewActivity.class);
                break;
            case R.id.button2:
                intent = new Intent(MainActivity.this, ViewGroupActivity.class);
                break;
            case R.id.button3:
                intent = new Intent(MainActivity.this, ScrollerClash1.class);
                break;
            case R.id.button4:
                intent = new Intent(MainActivity.this, ScrollerClash2.class);
                break;
            case R.id.button5:
                intent = new Intent(MainActivity.this, ScrollerClash3.class);
                break;
        }
        if (intent == null) {
            Log.e(TAG, "onClick: intent为空");
            return;
        }
        startActivity(intent);
    }
}
