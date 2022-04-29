package com.benjaminsinzore.roomdatabase.roomdb;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class WordViewModel extends AndroidViewModel {

    private com.example.android.roomwordssample.WordRepository mRepository;


    private final LiveData<List<com.example.android.roomwordssample.Word>> mAllWords;

    public WordViewModel(Application application) {
        super(application);
        mRepository = new com.example.android.roomwordssample.WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    LiveData<List<com.example.android.roomwordssample.Word>> getAllWords() {
        return mAllWords;
    }

    void insert(com.example.android.roomwordssample.Word word) {
        mRepository.insert(word);
    }
}
