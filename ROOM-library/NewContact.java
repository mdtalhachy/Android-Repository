package rocks.talha.contactroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import rocks.talha.contactroom.model.Contact;
import rocks.talha.contactroom.model.ContactViewModel;

public class NewContact extends AppCompatActivity {

    public static final String NAME_KEY = "name_key";
    public static final String OCCU_KEY = "occu_key";
    private EditText enterName;
    private EditText enterOccupation;
    private Button saveButton;
    private Button updateButton;
    private Button deleteButton;
    private int contactId = 0;
    private boolean isEdit = false;

    private ContactViewModel contactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(NewContact.this
                .getApplication())
                .create(ContactViewModel.class);

        if(getIntent().hasExtra("contact_id")){
            contactId = getIntent().getIntExtra("contact_id", 0);

            contactViewModel.get(contactId).observe(this, contact -> {
                if(contact != null){
                    enterName.setText(contact.getName());
                    enterOccupation.setText(contact.getOccupation());
                }
            });
            isEdit = true;
        }

        enterName = findViewById(R.id.name_edit_text);
        enterOccupation = findViewById(R.id.occupation_edit_text);
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(view -> {

            Intent replyIntent = new Intent();

            if(!TextUtils.isEmpty(enterName.getText()) && !TextUtils.isEmpty(enterOccupation.getText())){

                String name = enterName.getText().toString();
                String occupation = enterOccupation.getText().toString();

                replyIntent.putExtra(NAME_KEY, name);
                replyIntent.putExtra(OCCU_KEY, occupation);

                setResult(RESULT_OK, replyIntent);
            }else{
                setResult(RESULT_CANCELED, replyIntent);
            }

            finish();
        });


        //Update Button
        updateButton = findViewById(R.id.update_button);
        deleteButton = findViewById(R.id.delete_button);

        updateButton.setOnClickListener(view -> {
            int id = contactId;
            String name = enterName.getText().toString().trim();
            String occupation = enterOccupation.getText().toString().trim();

            if(TextUtils.isEmpty(name) || TextUtils.isEmpty(occupation)){
                Snackbar.make(enterName, "Empty Fields", Snackbar.LENGTH_SHORT).show();
            }else{
                Contact contact = new Contact();
                contact.setId(id);
                contact.setName(name);
                contact.setOccupation(occupation);
                ContactViewModel.update(contact);
                finish();
            }
        });

        //Delete Button Functionality
        deleteButton.setOnClickListener(view -> {
            int id = contactId;
            Contact contact = new Contact();
            contact.setId(contactId);
            ContactViewModel.delete(contact);
            finish();
        });

        if(isEdit){
            saveButton.setVisibility(View.GONE);
        }else{
            updateButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
        }

    }
}