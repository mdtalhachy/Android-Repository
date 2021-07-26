package rocks.talha.contactroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import rocks.talha.contactroom.model.Contact;
import rocks.talha.contactroom.model.ContactViewModel;

public class MainActivity extends AppCompatActivity {

    private ContactViewModel contactViewModel;
    private TextView textView;

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

                textView.setText(builder.toString());
            }
        });
    }
}
