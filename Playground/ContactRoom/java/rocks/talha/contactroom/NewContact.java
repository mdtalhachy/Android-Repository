package rocks.talha.contactroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import rocks.talha.contactroom.model.Contact;
import rocks.talha.contactroom.model.ContactViewModel;

public class NewContact extends AppCompatActivity {

    public static final String NAME_KEY = "name_key";
    public static final String OCCU_KEY = "occu_key";
    private EditText enterName;
    private EditText enterOccupation;
    private Button saveButton;

    private ContactViewModel contactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(NewContact.this
                .getApplication())
                .create(ContactViewModel.class);

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
    }
}
