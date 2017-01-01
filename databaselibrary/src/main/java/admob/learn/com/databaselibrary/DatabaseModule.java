package admob.learn.com.databaselibrary;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import handler.DatabaseHandler;
import model.ContactModel;

/**
 * Created by bala on 31/12/16.
 */

public class DatabaseModule {
    public DatabaseHandler db;
    public ArrayList<ContactModel> contactModelArrayList;

    public void createDatabase(Activity mActivity) {
        db = new DatabaseHandler(mActivity);
        contactModelArrayList = new ArrayList<>();
    }

    public void insertRecord() {
        // Inserting ContactModels
        Log.d("Insert: ", "Inserting ..");
        db.addContactModel(new ContactModel("Ravi", "9100000000"));
        db.addContactModel(new ContactModel("Srinivas", "9199999999"));
        db.addContactModel(new ContactModel("Tommy", "9522222222"));
        db.addContactModel(new ContactModel("Karthik", "9533333333"));
    }

    public List<ContactModel> readRecords() {
        // Reading all ContactModels
        //Log.d("Reading: ", "Reading all ContactModels..");
        List<ContactModel> ContactModels = db.getAllContactModels();

        /*for (ContactModel cn : ContactModels) {
            contactModelArrayList.add(cn);
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            // Writing ContactModels to log
            Log.d("Name: ", log);
        }*/
        return ContactModels;
    }
}
