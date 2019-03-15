package com.example.roomwordsample.model;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * In the DAO (data access object), you specify SQL queries and associate them with method calls.
 * The compiler checks the SQL and generates queries from convenience annotations for common queries, such as @Insert.
 */

//Annotate the class with @Dao to identify it as a DAO class for Room.
@Dao
public interface WordDao {

    /**
     * Tip: When inserting data, you can provide a conflict strategy.
     *
     * In this codelab, you do not need a conflict strategy, because the word is your primary key,
     * and the default SQL behavior is ABORT, so that you cannot insert two items with the same primary key into the database.
     *
     * If the table has more than one column, you can use
     *
     * @Insert(onConflict = OnConflictStrategy.REPLACE)
     *
     * to replace a row.
     */
    //Annotate the method with @Insert. You don't have to provide any SQL!
    //There are also @Delete and @Update annotations for deleting and updating a row, but you are not using them in this app.
    @Insert
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    /**
     * If you use LiveData independently from Room, you have to manage updating the data.
     * LiveData has no publicly available methods to update the stored data.
     *
     * If you, the developer, want to update the stored data, you must use MutableLiveData instead of LiveData.
     * The MutableLiveData class has two public methods that allow you to set the value of a LiveData object, setValue(T) and postValue(T).
     * Usually, MutableLiveData is used in the ViewModel, and then the ViewModel only exposes immutable LiveData objects to the observers.
     *
     * Later in this codelab, you create an Observer of the data in the onCreate() method of MainActivity
     * and override the observer's onChanged() method. When the LiveData changes, the observer is notified and onChanged() is executed.
     * You will then update the cached data in the adapter, and the adapter will update what the user sees.
     */
    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();
}