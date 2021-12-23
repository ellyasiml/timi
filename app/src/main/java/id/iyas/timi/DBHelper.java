package id.iyas.timi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, name TEXT, password TEXT, gender TEXT)");
        MyDB.execSQL("create Table list(title TEXT primary key, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists list");
    }

    public Boolean insertData(String username, String name, String password, String gender){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("name", name);
        contentValues.put("password", password);
        contentValues.put("gender", gender);
        long results = MyDB.insert("users", null, contentValues);
        if(results==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public void dml(String query){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        MyDB.execSQL(query);
        MyDB.close();
    }

    public String cari(String query){
        String hasil = "";
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery(query, null);
        while (cursor.moveToNext()){
            hasil = cursor.getString(0);
        }
        MyDB.close();
        return hasil;
    }

    public String[][] cari_array(String query, int baris, int kolom){
        String data[][] = new String[baris][kolom];
        SQLiteDatabase MyDB = this.getReadableDatabase();

        int counter_baris = 0;
        Cursor cursor = MyDB.rawQuery(query, null);
        while (cursor.moveToNext()){
            for (int i=0; i<kolom; i++){
                data[counter_baris][i] = cursor.getString(i);
            }
            counter_baris++;
        }
        MyDB.close();
        return data;
    }
}