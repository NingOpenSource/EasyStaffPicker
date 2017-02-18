package org.ning.EasyStaffPicker.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.ning.EasyStaffPicker.EasyScrollStaffPicker;

public class Main1Activity extends AppCompatActivity {
    private TextView tv_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        tv_value=(TextView)findViewById(R.id.tv_value);
        findViewById(R.id.bt_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main1Activity.this,Main2Activity.class));
            }
        });
        EasyScrollStaffPicker easyScrollStaffPicker=(EasyScrollStaffPicker)findViewById(R.id.scrollStaffPickerView);
        easyScrollStaffPicker.setOnStaffValueChangeListener(new EasyScrollStaffPicker.OnStaffValueChangeListener() {

            @Override
            public void onChange(int unit_index) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onChangeValue(String unit_tag_name, double unit_tag_value) {
                tv_value.setText("当前身高："+unit_tag_name);
            }
        });
        easyScrollStaffPicker.scroll2ByUnitValue( 175);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
