package com.example.firstapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListPopupWindow;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.dynamicanimation.animation.SpringAnimation;

import com.example.firstapp.model.PersonActivity;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "activities_manager";
    private static final String TABLE_NAME = "activities";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String LOCATE = "locate";
    private static final String DATE = "date";
    private static final String TIME = "time";
    private static final int VERSION = 1;
    private String SQLQuery = "CREATE TABLE " + TABLE_NAME + " (" + ID +
            " integer primary key, " +
            NAME + " TEXT, " +
            LOCATE + " TEXT, " +
            DATE + " TEXT, " +
            TIME + " TEXT)";
    Context context;


    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void Hello(){
        Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
    }

    public void addActivity(PersonActivity activity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, activity.getmName());
        values.put(LOCATE, activity.getmLocate());
        values.put(DATE, activity.getmDate());
        values.put(TIME, activity.getmTime());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<PersonActivity> getAllActivities(){
        List<PersonActivity> activityList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        // Hứng
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do {
                PersonActivity activity = new PersonActivity();
                activity.setmId(cursor.getInt(0));
                activity.setmName(cursor.getString(1));
                activity.setmLocate(cursor.getString(2));
                activity.setmDate(cursor.getString(3));
                activity.setmTime(cursor.getString(4));

                activityList.add(activity);
            }while (cursor.moveToNext());
        }
        db.close();
        return activityList;
    }

    public void updateActivity(PersonActivity activity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, activity.getmName());
        contentValues.put(LOCATE, activity.getmLocate());
        contentValues.put(DATE, activity.getmDate());
        contentValues.put(TIME, activity.getmTime());
        db.update(TABLE_NAME, contentValues, "ID = " + activity.getmId(),null);
        db.close();
    }

    public int deleteActivity(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_NAME, "ID = " + id,null);
        return i;
    }
}
