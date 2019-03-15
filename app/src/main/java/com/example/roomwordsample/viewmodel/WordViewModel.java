package com.example.roomwordsample.viewmodel;

import android.app.Application;

import com.example.roomwordsample.model.Word;
import com.example.roomwordsample.repository.WordRepository;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * A ViewModel holds your app's UI data in a lifecycle-conscious way that survives configuration changes.
 * Separating your app's UI data from your Activity and Fragment classes lets you better follow the single
 * responsibility principle: Your activities and fragments are responsible for drawing data to the screen,
 * while your ViewModel can take care of holding and processing all the data needed for the UI.
 */


public class WordViewModel extends AndroidViewModel {

    //Add a private member variable to hold a reference to the repository.
    private WordRepository mRepository;

    //Add a private LiveData member variable to cache the list of words.
    private LiveData<List<Word>> mAllWords;

    //Add a constructor that gets a reference to the repository and gets the list of words from the repository.
    public WordViewModel (Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    //Add a "getter" method for all the words. This completely hides the implementation from the UI.
    public LiveData<List<Word>> getAllWords() {

        return mAllWords;
    }

    //Create a wrapper insert() method that calls the Repository's insert() method. In this way,
    //the implementation of insert() is completely hidden from the UI.
    public void insert(Word word) {

        mRepository.insert(word);
    }

}
