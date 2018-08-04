package knnbon009.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dataDB.db";
    public static final String TABLE_DATA = "data";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ACTIVITYNAME = "activityname";
    public static final String COLUMN_LITRES = "litres";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_DATA + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ACTIVITYNAME + " TEXT, " +
                COLUMN_LITRES + " DOUBLE " +
                ");";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
        onCreate(sqLiteDatabase);
    }

    //Add a new row to the database
    public void addProduct(Data product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ACTIVITYNAME, product.getActivity());
        values.put(COLUMN_LITRES, product.getLitres());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_DATA, null, values);
        db.close();
    }

    public ArrayList<Data> getDatabaseContent(){
        ArrayList<Data> data = new ArrayList<Data>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_DATA + " WHERE 1";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {

            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex(COLUMN_ACTIVITYNAME)) != null) {
                String activity = recordSet.getString(recordSet.getColumnIndex(COLUMN_ACTIVITYNAME));
                double litres = recordSet.getDouble(recordSet.getColumnIndex(COLUMN_LITRES));
                int id = recordSet.getInt(recordSet.getColumnIndex(COLUMN_ID));
                data.add(new Data(id,activity,litres));
            }
            recordSet.moveToNext();
        }
        db.close();
        return data;
    }

}
