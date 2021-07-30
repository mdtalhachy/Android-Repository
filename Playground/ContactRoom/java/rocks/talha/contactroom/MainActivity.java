package rocks.talha.contactroom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import rocks.talha.contactroom.adapter.RecyclerViewAdapter;
import rocks.talha.contactroom.model.Contact;
import rocks.talha.contactroom.model.ContactViewModel;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnContactClickListener{

    public static final int NEW_CONTACT_ACTIViTY_REQ_CODE = 1;
    private ContactViewModel contactViewModel;
    TextView textView;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    private List<Contact> contactList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        textView = findViewById(R.id.name_textView);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this
                .getApplication())
                .create(ContactViewModel.class);


        contactViewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {

                //setup adapter
                recyclerViewAdapter = new RecyclerViewAdapter(contacts, MainActivity.this);

                //setting adapter to recyclerView
                //It's important that we set adapter inside observer cause all the changes happen
                //inside Observe
                recyclerView.setAdapter(recyclerViewAdapter);

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

    @Override
    public void onContactClick(int position) {

    }
}
