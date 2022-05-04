package com.benjaminsinzore.roomdatabase.roomdb;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class ViewModel extends AndroidViewModel {

    private Repository mRepository;


    private final LiveData<List<Word>> mAllWords;

    public ViewModel(Application application) {
        super(application);
        mRepository = new Repository(application);
        mAllWords = mRepository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }

    public void deleteAll(){
        mRepository.deleteAll();
    }
}
