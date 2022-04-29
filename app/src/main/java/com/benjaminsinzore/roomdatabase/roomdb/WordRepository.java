package com.benjaminsinzore.roomdatabase.roomdb;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;


class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<com.example.android.roomwordssample.Word>> mAllWords;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }

    LiveData<List<com.example.android.roomwordssample.Word>> getAllWords() {
        return mAllWords;
    }

    void insert(com.example.android.roomwordssample.Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.insert(word);
        });
    }
}
