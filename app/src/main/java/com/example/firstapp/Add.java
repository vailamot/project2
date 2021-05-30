package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.firstapp.data.DBManager;
import com.example.firstapp.model.PersonActivity;

import java.sql.Time;
import java.util.Calendar;
import java.util.List;

public class Add extends AppCompatActivity {
    private DatePicker datePicker;
    private TimePicker timePicker;
    private EditText etAddDate, etAddTime, etAddName, etAddLocate;
    private Switch swAddDate, swAddTime;
    private ImageView imvToolbarAddReturn;
    private TextView tvAddSubmit, tvAddUpdate;

    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String LOCATE = "LOCATE";
    public static final String DATE = "DATE";
    public static final String TIME = "TIME";
    public static final String BUNDLE = "BUNDLE";
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        datePicker = findViewById(R.id.date_picker);
        timePicker = findViewById(R.id.time_picker);
        etAddDate = findViewById(R.id.et_add_date);
        etAddTime = findViewById(R.id.et_add_time);
        etAddName = findViewById(R.id.et_add_name);
        etAddLocate = findViewById(R.id.et_add_locate);
        swAddDate = findViewById(R.id.sw_add_date);
        swAddTime = findViewById(R.id.sw_add_time);
        imvToolbarAddReturn = findViewById(R.id.imv_toolbar_add_return);
        tvAddSubmit = findViewById(R.id.tv_add_submit);
        tvAddUpdate = findViewById(R.id.tv_add_update);

        tvAddUpdate.setVisibility(View.GONE);

        swAddDate.setChecked(true);
        swAddDate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(swAddDate.isChecked()){
                    datePicker.setVisibility(View.VISIBLE);
                }else datePicker.setVisibility(View.GONE);
            }
        });

        swAddTime.setChecked(true);
        swAddTime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(swAddTime.isChecked()){
                    timePicker.setVisibility(View.VISIBLE);
                }else timePicker.setVisibility(View.GONE);
            }
        });

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                etAddDate.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);

            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                etAddTime.setText(hourOfDay + ":" + minute);

            }
        });

        imvToolbarAddReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add.this,MainActivity.class);
                startActivity(intent);
            }
        });


        // Database
//        DBManager dbManager = new DBManager(this);
//        dbManager.Hello();
//        list = dbManager.getAllActivities();

        // Truyền dữ liệu sang main
        tvAddSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add.this,MainActivity.class);
                String name = etAddName.getText().toString();
                String locate = etAddLocate.getText().toString();
                String date = etAddDate.getText().toString();
                String time = etAddTime.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString(NAME, name);
                bundle.putString(LOCATE, locate);
                bundle.putString(DATE, date);
                bundle.putString(TIME, time);
                intent.putExtra(BUNDLE, bundle);
                startActivity(intent);
//                dbManager.addActivity(createActivity());


            }
        });


        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(MainActivity.BUNDLE);
            if (bundle != null) {
                id = bundle.getInt(MainActivity.ID);
                etAddName.setText(bundle.getString(MainActivity.NAME));
                etAddLocate.setText(bundle.getString(MainActivity.LOCATE));
                etAddDate.setText(bundle.getString(MainActivity.DATE));
                etAddTime.setText(bundle.getString(MainActivity.TIME));

                tvAddUpdate.setVisibility(View.VISIBLE);
                tvAddSubmit.setVisibility(View.GONE);

            }
        }

        tvAddUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add.this,MainActivity.class);
                String name = etAddName.getText().toString();
                String locate = etAddLocate.getText().toString();
                String date = etAddDate.getText().toString();
                String time = etAddTime.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString(NAME, name);
                bundle.putString(LOCATE, locate);
                bundle.putString(DATE, date);
                bundle.putString(TIME, time);
                bundle.putInt(ID, id);
                intent.putExtra(BUNDLE, bundle);

                startActivity(intent);
            }
        });

    }
//    private PersonActivity createActivity(){
//        String name = etAddName.getText().toString();
//        String locate = etAddLocate.getText().toString();
//        String date = etAddDate.getText().toString();
//        String time = etAddTime.getText().toString();
//
//        PersonActivity activity = new PersonActivity(name, locate, date, time);
//        return activity;
//    }
}