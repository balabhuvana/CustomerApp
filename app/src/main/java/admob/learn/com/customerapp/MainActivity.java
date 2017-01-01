package admob.learn.com.customerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import admob.learn.com.databaselibrary.DatabaseModule;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseModule mDatabaseModule = new DatabaseModule();
        mDatabaseModule.createDatabase(this);

        //mDatabaseModule.insertRecord();
        mDatabaseModule.readRecords();
    }
}
