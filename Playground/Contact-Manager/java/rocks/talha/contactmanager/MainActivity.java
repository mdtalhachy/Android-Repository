package rocks.talha.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import rocks.talha.contactmanager.data.DatabaseHandler;
import rocks.talha.contactmanager.model.Contact;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);

        //testing getCount()
        //getting the total number of contacts
        Log.d("Count", "onCreate: " + db.getCount());

        Contact mahony = new Contact();
        mahony.setName("Mahony");
        mahony.setPhoneNumber("01755669988");

        Contact megh = new Contact();
        megh.setName("Megh");
        megh.setPhoneNumber("3231213232");

        //db.addContact(mahony);
        //db.addContact(megh);


        //Get one contact
        Contact c = db.getContact(3);
        Log.d("ContactC", "onCreate: " + c.getName() + ", " + c.getPhoneNumber());

        //Updating contact test
        c.setName("Bollov");
        c.setPhoneNumber("0155500");

        /*int updatedRow = db.updateContact(c);
        Log.d("updates", "onCreate: " + updatedRow);*/

        /* testing deleteContact */
        db.deleteContact(c);

        //get all contacts
        List<Contact> contactList = db.getAllContacts();
        for(Contact contact: contactList){
            Log.d("ContactI", "onCreate: " + contact.getPhoneNumber());
        }

    }
}
