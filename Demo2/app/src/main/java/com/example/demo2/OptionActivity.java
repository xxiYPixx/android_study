package com.example.demo2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class OptionActivity extends AppCompatActivity {
    private TextView mtextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        mtextView = findViewById(R.id.title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mtextView.setText(item.getTitle());
        switch (item.getItemId()){
            case R.id.item_text_size:
                mtextView.setBackground(R.color.gray);
                break;
            case R.id.item_normal:
                mtextView.setText("普通菜单项");
                break;
            case R.id.item_text_color:
                mtextView.setText("文本颜色");
                break;
        }
    }
}
