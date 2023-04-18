package com.example.mab_group_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static String dbName  = "dailymeal.db";
    private static int dbVersion = 1;
    private static String tableName = "all_meals";
    private static String key_mealType = "Meal_Type";
    private static String key_timestamp = "Date_Taken";
    private static String key_meal = "Meal_Taken";
    private static String key_calories = "Calories";
    private static String key_carbs = "Carbohydrates";
    private static String key_protein = "Protein";
    private static String key_fat = "Fat";
    private static String key_vitaminC = "VitaminC";


    //adds a new row of meal taken
    public void addRow(String meal, String mealType, String dateTime, float calories, float carbs, float protein, float fat, float vitaminc) {
        ContentValues cv = new ContentValues();
        cv.put(key_meal, meal);
        cv.put(key_mealType, mealType);
        cv.put(key_timestamp, dateTime);
        cv.put(key_calories, calories);
        cv.put(key_carbs, carbs);
        cv.put(key_protein, protein);
        cv.put(key_fat, fat);
        cv.put(key_vitaminC, vitaminc);

        SQLiteDatabase db = getWritableDatabase();
        db.insert(tableName, null, cv);
        db.close();
    }


    //get rows based on date -- for display purposes
    public ArrayList<Meal> getTodayMeals(String[] dateTime) {
        ArrayList<Meal> mealList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = key_timestamp + "=?";
        String[] columns = new String[] {key_meal, key_mealType, key_timestamp, key_calories, key_carbs, key_protein, key_fat, key_vitaminC};
        Cursor c = db.query(tableName, columns, whereClause, dateTime, null, null, key_timestamp);

        int mealIndex = c.getColumnIndex(key_meal);
        int mealTypeIndex = c.getColumnIndex(key_mealType);
        int timestampIndex = c.getColumnIndex(key_timestamp);
        int caloriesIndex = c.getColumnIndex(key_calories);
        int carbsIndex = c.getColumnIndex(key_carbs);
        int proteinIndex = c.getColumnIndex(key_protein);
        int fatIndex = c.getColumnIndex(key_fat);
        int vitamincIndex = c.getColumnIndex(key_vitaminC);

        for (c.moveToFirst(); !(c.isAfterLast()); c.moveToNext()) {
            String meal = c.getString(mealIndex);
            String mealType = c.getString(mealTypeIndex);
            String timestamp = c.getString(timestampIndex);
            float calories = c.getFloat(caloriesIndex);
            float protein = c.getFloat(proteinIndex);
            float fat = c.getFloat(fatIndex);
            float vitaminc = c.getFloat(vitamincIndex);
            float carbs = c.getFloat(carbsIndex);

            mealList.add(new Meal(meal, mealType, timestamp, calories, protein, fat, vitaminc, carbs));
        }

        return mealList;
    }

    //constructor
    public DatabaseHandler (Context c) { super(c, dbName, null, dbVersion); };

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + tableName + " (rowid INTEGER PRIMARY KEY NOT NULL, " +
                key_mealType + " TEXT, " +
                key_timestamp + " DATE, " +
                key_meal + " TEXT, " +
                key_calories + " FLOAT, " +
                key_carbs + " FLOAT, " +
                key_protein + " FLOAT, " +
                key_fat + " FLOAT, " +
                key_vitaminC + " FLOAT" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }
}
