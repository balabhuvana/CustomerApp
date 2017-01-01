package handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import model.ContactModel;


public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ContactModelsManager";

    // ContactModels table name
    private static final String TABLE_ContactModelS = "ContactModels";

    // ContactModels Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ContactModelS_TABLE = "CREATE TABLE " + TABLE_ContactModelS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_ContactModelS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ContactModelS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new ContactModel
    public void addContactModel(ContactModel ContactModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, ContactModel.getName()); // ContactModel Name
        values.put(KEY_PH_NO, ContactModel.getPhoneNumber()); // ContactModel Phone

        // Inserting Row
        db.insert(TABLE_ContactModelS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single ContactModel
    public ContactModel getContactModel(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ContactModelS, new String[]{KEY_ID,
                        KEY_NAME, KEY_PH_NO}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ContactModel ContactModel = new ContactModel(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return ContactModel
        return ContactModel;
    }

    // Getting All ContactModels
    public List<ContactModel> getAllContactModels() {
        List<ContactModel> ContactModelList = new ArrayList<ContactModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ContactModelS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ContactModel ContactModel = new ContactModel();
                ContactModel.setID(Integer.parseInt(cursor.getString(0)));
                ContactModel.setName(cursor.getString(1));
                ContactModel.setPhoneNumber(cursor.getString(2));
                // Adding ContactModel to list
                ContactModelList.add(ContactModel);
            } while (cursor.moveToNext());
        }

        // return ContactModel list
        return ContactModelList;
    }

    // Updating single ContactModel
    public int updateContactModel(ContactModel ContactModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, ContactModel.getName());
        values.put(KEY_PH_NO, ContactModel.getPhoneNumber());

        // updating row
        return db.update(TABLE_ContactModelS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(ContactModel.getID())});
    }

    // Deleting single ContactModel
    public void deleteContactModel(ContactModel ContactModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ContactModelS, KEY_ID + " = ?",
                new String[]{String.valueOf(ContactModel.getID())});
        db.close();
    }


    // Getting ContactModels Count
    public int getContactModelsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_ContactModelS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}