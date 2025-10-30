package com.example.demo2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListviewActivity extends AppCompatActivity{
    private ListView listView;
    private NotificationManager notificationManager;
    private static final String CHANNEL_ID = "listview_channel";
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        listView = findViewById(R.id.listView);

        createNotificationChannel();

        List<Map<String, Object>> data = new ArrayList<>();

        String[] items = {"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};
        int[] images = {
                R.drawable.lion, R.drawable.tiger, R.drawable.monkey,
                R.drawable.dog, R.drawable.cat, R.drawable.elephant
        };

        for (int i = 0; i < 6; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("text", items[i]);
            map.put("image", images[i]);
            data.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                R.layout.list_item,
                new String[]{"text", "image"},
                new int[]{R.id.textItem, R.id.imageItem}
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) ((Map<String, Object>) parent.getItemAtPosition(position)).get("text");

                String toastMessage = "选中了: " + selectedItem;
                Toast.makeText(ListviewActivity.this, toastMessage, Toast.LENGTH_SHORT).show();

                sendNotification(selectedItem);

                view.setSelected(true);
            }
        });

        // 可选：点击其他地方时取消选中状态
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 可以在这里处理选中状态
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 清除所有选中状态
                for (int i = 0; i < parent.getChildCount(); i++) {
                    parent.getChildAt(i).setSelected(false);
                }
            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "ListView通知渠道",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("用于ListView项选择通知");

            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        } else {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
    }

    private void sendNotification(String itemText) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(itemText)
                .setContentText("您刚刚选择了这个列表项")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public void clearSelection() {
        for (int i = 0; i < listView.getChildCount(); i++) {
            listView.getChildAt(i).setSelected(false);
        }
    }
}
