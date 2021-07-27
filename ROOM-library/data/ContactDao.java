package rocks.talha.contactroom.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import rocks.talha.contactroom.model.Contact;

@Dao
public interface ContactDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact contact);

    @Query ("DELETE FROM contact_table")
    void deleteAll();

    @Query ("SELECT * FROM contact_table ORDER BY name ASC")
    LiveData<List<Contact>> getAllContacts();
}
