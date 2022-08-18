package uz.isystem.dictionary2.core.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import uz.isystem.dictionary2.core.lib.DbHelper;
import uz.isystem.dictionary2.core.models.MyDictionary;

public class DataBase extends DbHelper {

    public static DataBase dataBase;

    public DataBase(@Nullable Context context) {
        super(context, "uzen.db");
    }

    public static void init(Context context) {
        if (dataBase == null) {
            dataBase = new DataBase(context);
        }
    }

    public static DataBase getDataBase() {
        return dataBase;
    }

    public ArrayList<MyDictionary> getDictionary() {
        ArrayList<MyDictionary> myDictionaries = new ArrayList<>();

        String query = "SELECT * FROM collection";

        Cursor cursor = mDataBase.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String Sen0 = cursor.getString(1);
            String Sen1 = cursor.getString(2);

            MyDictionary myDictionary = new MyDictionary(Sen0, Sen1);
            myDictionaries.add(myDictionary);

            cursor.moveToNext();
        }

        return myDictionaries;
    }

    public Long addDictionary(String Sen0, String Sen1) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("Sen0", Sen0);
        contentValues.put("Sen1", Sen1);
        return mDataBase.insert("collection", null, contentValues);
    }

    public int deleteDictionary(MyDictionary myDictionary) {
        String Sen0 = myDictionary.getEng();

        return mDataBase.delete("collection", "Sen0=?", new String[]{Sen0});
    }

    public int updateDictionary(MyDictionary myDictionary) {
        String Sen0 = myDictionary.getEng();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Sen0", myDictionary.getEng());
        contentValues.put("Sen1", myDictionary.getUzb());

        return mDataBase.update("collection", contentValues, "eng=" + Sen0, null);
    }

    public ArrayList<MyDictionary> searchByName(String text) {
        ArrayList<MyDictionary> myDictionaries = new ArrayList<>();

        String query = "SELECT * FROM collection WHERE Sen0 LIKE '" + text + "%'";

        Cursor cursor = mDataBase.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String Sen0 = cursor.getString(1);
            String Sen1 = cursor.getString(2);

            MyDictionary myDictionary = new MyDictionary(Sen0, Sen1);
            myDictionaries.add(myDictionary);

            cursor.moveToNext();

        }
        return myDictionaries;
    }
}

