package com.example.roomwordsample.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Room is a database layer on top of an SQLite database. Room takes care of mundane tasks that you used to handle with an SQLiteOpenHelper.
 * Room uses the DAO to issue queries to its database.
 * By default, to avoid poor UI performance, Room doesn't allow you to issue database queries on the main thread.
 * LiveData applies this rule by automatically running the query asynchronously on a background thread, when needed.
 * Room provides compile-time checks of SQLite statements.
 * Your Room class must be abstract and extend RoomDatabase.
 * Usually, you only need one instance of the Room database for the whole app.
 */

//Annotate the class to be a Room database, declare the entities that belong in the database and set the version number.
// Listing the entities will create tables in the database.
@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

    //Define the DAOs that work with the database. Provide an abstract "getter" method for each @Dao.
    public abstract WordDao wordDao();
    private static volatile WordRoomDatabase INSTANCE;

    //Make the WordRoomDatabase a singleton to prevent having multiple instances of the database opened at the same time.
    public static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    //To delete all content and repopulate the database whenever the app is started,
    //you create a RoomDatabase.Callback and override onOpen(). Because you cannot do Room database
    //operations on the UI thread, onOpen() creates and executes an AsyncTask to add content to the database.
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };



    //Here is the code for the AsyncTask that deletes the contents of the database,
    //then populates it with the two words "Hello" and "World". Feel free to add more words!
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;

        PopulateDbAsync(WordRoomDatabase db) {

            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
//            mDao.deleteAll();
//            Word word = new Word("Hello");
//            mDao.insert(word);
//            word = new Word("World");
//            mDao.insert(word);
            return null;
        }
    }
}
