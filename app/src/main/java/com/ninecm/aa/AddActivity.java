package com.ninecm.aa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;


public class AddActivity extends AppCompatActivity {

    private DatePicker picker;
    private Button btnGet;
    private TextView tvw;
    private Calendar c;
    private ImageButton btnCancel;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

        init();

        setUp();
    }

    private void init() {
        tvw = (TextView) findViewById(R.id.open_textView);
        picker = (DatePicker) findViewById(R.id.open_datePicker);
        btnGet=(Button)findViewById(R.id.open_button);
        btnCancel = (ImageButton) findViewById(R.id.btn_cancel);
        btnAdd = (Button) findViewById(R.id.btn_add);
    }

    private void setUp() {
        btnCancel.setOnClickListener(closePage);
        btnGet.setOnClickListener(setTextEndDay);
    }

    View.OnClickListener setTextEndDay = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("MyTag", "Click");
            tvw.setText(picker.getYear() + "년 " + (picker.getMonth()+1) + "월 " + picker.getDayOfMonth() + "일");
        }
    };

    View.OnClickListener closePage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AddActivity.this.finish();
            overridePendingTransition(R.anim.anim_stay, R.anim.anim_slide_down);
        }
    };
}
