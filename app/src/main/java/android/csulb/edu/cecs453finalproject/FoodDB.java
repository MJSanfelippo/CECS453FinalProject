package android.csulb.edu.cecs453finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


/**
 * Created by Michael on 11/24/2017.
 */

public class FoodDB extends SQLiteOpenHelper {

    public static final String NAME_COLUMN = "name";
    public static final String CALORIES_COLUMN = "calories";
    public static final String FATS_COLUMN = "fats";
    public static final String CARBS_COLUMN = "carbs";
    public static final String PROTEINS_COLUMN = "proteins";
    public static final String DATETIME_COLUMN = "dateTimeEntered";

    public static final String DATABASE_TABLE = "Food";
    public static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "CREATE TABLE " + DATABASE_TABLE + "(" + NAME_COLUMN + " TEXT, "
                                                    + CALORIES_COLUMN + " REAL, " + FATS_COLUMN + " REAL, " + CARBS_COLUMN
                                                    + " REAL, " + PROTEINS_COLUMN + " REAL, " + DATETIME_COLUMN + " TEXT PRIMARY KEY" + ")";

    public FoodDB(Context context){
        super(context, DATABASE_TABLE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public void insertNewFood(ContentValues values){

    }

    public ArrayList<FoodItem> getAllFoodsFromDate(String date){
        ArrayList<FoodItem> foodList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String where = DATETIME_COLUMN + "= ?";
        date = date.substring(0, )
        String[] whereArgs = {date};
        String[] resultColumns = {NAME_COLUMN, CALORIES_COLUMN, FATS_COLUMN, CARBS_COLUMN, PROTEINS_COLUMN, DATETIME_COLUMN};
        Cursor cursor = db.query(DATABASE_TABLE, resultColumns, where, whereArgs, null, null, null);
        while (cursor.moveToNext()){
            String name = cursor.getString(0);
            double calories = cursor.getDouble(1);
            double fats = cursor.getDouble(2);
            double carbs = cursor.getDouble(3);
            double proteins = cursor.getDouble(4);
            String dateTime = cursor.getString(5);
            FoodItem item = new FoodItem(name, calories, proteins, carbs, fats, dateTime);
            foodList.add(item);
        }
        return foodList;
    }





}
