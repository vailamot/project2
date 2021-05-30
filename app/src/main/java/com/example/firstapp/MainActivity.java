package com.example.firstapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.firstapp.adapter.ItemAdapter;
import com.example.firstapp.data.DBManager;
import com.example.firstapp.model.PersonActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton btnToAdd;
    private List<PersonActivity> list;
    private ItemAdapter listAdapter;
    private ListView lvAdapter;

    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String LOCATE = "LOCATE";
    public static final String DATE = "DATE";
    public static final String TIME = "TIME";
    public static final String BUNDLE = "BUNDLE";
    DBManager dbManager = new DBManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnToAdd = findViewById(R.id.btn_to_add);
        lvAdapter = findViewById(R.id.lv_adapter);



        // hiểu thị ds ra màn hình
        list = dbManager.getAllActivities();



        // Thêm 1 activity mới
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(Add.BUNDLE);
            if (bundle != null) {
                boolean check= false;
                String name = bundle.getString(Add.NAME);
                String locate = bundle.getString(Add.LOCATE);
                String date = bundle.getString(Add.DATE);
                String time = bundle.getString(Add.TIME);
                int id = bundle.getInt(Add.ID);
                for (int i = 1; i <= list.size(); i++){
                    if(id==i){
                        check = true;
                    }
                }
                if(check){
                    PersonActivity activity = new PersonActivity(id, name, locate, date, time);
                    dbManager.updateActivity(activity);
                    updateListActivity();

                }else {
                    PersonActivity activity = new PersonActivity(name, locate, date, time);
                    dbManager.addActivity(activity);
                    updateListActivity();
                }


            }
        }




        // chuyển sang add
        btnToAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAdd();
            }
        });

        // Sửa

        lvAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PersonActivity activity = new PersonActivity();
                activity = list.get(position);
                Intent intent = new Intent(MainActivity.this, Add.class);

                Bundle bundle = new Bundle();
                bundle.putInt(ID, activity.getmId());
                bundle.putString(NAME, activity.getmName());
                bundle.putString(LOCATE, activity.getmLocate());
                bundle.putString(DATE, activity.getmDate());
                bundle.putString(TIME, activity.getmTime());
                intent.putExtra(BUNDLE, bundle);
                startActivity(intent);
            }
        });

//        lvAdapter.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                PersonActivity activity = list.get(position);
//                Toast.makeText(MainActivity.this, "1", Toast.LENGTH_LONG).show();
//                int i = dbManager.deleteActivity(activity.getmId());
//                Toast.makeText(MainActivity.this, "2", Toast.LENGTH_LONG).show();
//                if(i>0){
//                    Toast.makeText(MainActivity.this, "Delete successfully", Toast.LENGTH_LONG).show();
//                    updateListActivity();
//                }else {
//                    Toast.makeText(MainActivity.this, "Delete failed", Toast.LENGTH_LONG).show();
//                }
//
//                return false;
//            }
//        });

        lvAdapter.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Hello");
                builder.setMessage("Bạn có muốn xoá hoạt động này không?");
                builder.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        PersonActivity activity = list.get(position);

                        int i = dbManager.deleteActivity(activity.getmId());

                        if(i>0){
                            Toast.makeText(MainActivity.this, "Delete successfully", Toast.LENGTH_LONG).show();
                            updateListActivity();
                        }else {
                            Toast.makeText(MainActivity.this, "Delete failed", Toast.LENGTH_LONG).show();
                        }

                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();





                return false;
            }
        });














        setListAdapter();



        // lấy hết danh sách



//        Bundle bundle = intent.getBundleExtra(Add.BUNDLE);
//        String name = bundle.getString(Add.NAME);
//        String locate = bundle.getString(Add.LOCATE);
//        String date = bundle.getString(Add.DATE);
//        String time = bundle.getString(Add.TIME);
//        PersonActivity activity = new PersonActivity(name, locate, date, time);
//        dbManager.addActivity(activity);

    }


    // Hien thi activity add hoat dong
    private void showAdd(){
        Intent intentAdd = new Intent(MainActivity.this,Add.class);
        startActivity(intentAdd);
    }

    //
    private void setListAdapter(){
        if(listAdapter == null){
            listAdapter = new ItemAdapter(this, R.layout.item_list_activities, list);
            lvAdapter.setAdapter(listAdapter);
        }else {
            listAdapter.notifyDataSetChanged();
           lvAdapter.setSelection(listAdapter.getCount()-1);
        }
    }

    private void showDeleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hello");
        builder.setMessage("Bạn có muốn xoá hoạt động này không?");
        builder.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Delete successfully", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void updateListActivity(){
        list.clear();
        list.addAll(dbManager.getAllActivities());
        listAdapter.notifyDataSetChanged();
    }

}