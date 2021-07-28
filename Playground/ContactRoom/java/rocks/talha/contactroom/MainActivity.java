package rocks.talha.contactroom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import rocks.talha.contactroom.model.Contact;
import rocks.talha.contactroom.model.ContactViewModel;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_CONTACT_ACTIViTY_REQ_CODE = 1;
    private ContactViewModel contactViewModel;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this
                .getApplication())
                .create(ContactViewModel.class);


        contactViewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                StringBuilder builder = new StringBuilder();

                for(Contact contact : contacts){
                    builder.append(" - ").append(contact.getName()).append(" ").append(contact.getOccupation());
                    Log.d("CTEST", "onChanged: " + contact.getName());
                }

                textView.setText(builder);
            }
        });

        FloatingActionButton floab = findViewById(R.id.add_contact_fab);

        floab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewContact.class);
            startActivityForResult(intent, NEW_CONTACT_ACTIViTY_REQ_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_CONTACT_ACTIViTY_REQ_CODE && resultCode == RESULT_OK){
            /*Log.d("Bull", "onActivityResult: " + data.getStringExtra(NewContact.NAME_KEY));
            Log.d("Bulls", "onActivityResult: " + data.getStringExtra(NewContact.OCCU_KEY));*/

            String name = data.getStringExtra(NewContact.NAME_KEY);
            String occupation = data.getStringExtra(NewContact.OCCU_KEY);
            Contact contact = new Contact(name, occupation);

            ContactViewModel.insert(contact);
        }

    }
}
