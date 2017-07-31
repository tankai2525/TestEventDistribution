package kai.tan.com.testeventdistribution;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ScrollerClash3 extends AppCompatActivity {

    private View mHead;
    private ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller_clash3);

        mHead = findViewById(R.id.head);
        mList = (ListView)findViewById(R.id.list);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add("name:" + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.content_list_item, R.id.name, data);
        mList.setAdapter(adapter);
    }
}
