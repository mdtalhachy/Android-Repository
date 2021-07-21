package rocks.talha.contactmanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import rocks.talha.contactmanager.R;
import rocks.talha.contactmanager.util.Util;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    //This is where we create the Database and the table
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY" + Util.KEY_NAME + " TEXT"
                + Util.KEY_PHONE_NUMBER + " TEXT" + ")";

        db.execSQL(CREATE_CONTACT_TABLE); //executing our SQL



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf(R.string.drop_table);
        db.execSQL(DROP_TABLE, new String[]{Util.TABLE_NAME});

        onCreate(db); //recreating table once dropped
        
    }
}
