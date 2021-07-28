package rocks.talha.contactroom.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rocks.talha.contactroom.data.ContactDao;
import rocks.talha.contactroom.model.Contact;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactRoomDatabase extends RoomDatabase {

    public abstract ContactDao contactDao();
    public static final int NUMBER_OF_TREAD = 4;

    /* volatile means we can always remove it if necessary */
    public static volatile ContactRoomDatabase INSTANCE;

    /* Executor service helps us run things in the back thread */
    public static final ExecutorService databaseWriteExecutor
            = Executors.newFixedThreadPool(NUMBER_OF_TREAD);

    public static ContactRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (ContactRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactRoomDatabase.class, "contact_database")
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }

        return INSTANCE;
    }

    public static final RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    databaseWriteExecutor.execute(() ->{
                        ContactDao contactDao = INSTANCE.contactDao();
                        contactDao.deleteAll();

                        Contact contact = new Contact("Talha", "Developer");
                        contactDao.insert(contact);

                        contact = new Contact("Bond", "Spy");
                        contactDao.insert(contact);
                    });
                }
            };
}
