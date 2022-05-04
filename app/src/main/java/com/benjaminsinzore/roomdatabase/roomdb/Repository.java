package com.benjaminsinzore.roomdatabase.roomdb;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;


class Repository {

    private Dao mDao;
    private LiveData<List<Word>> mAllWords;

    Repository(Application application) {
        RoomDatabase db = RoomDatabase.getDatabase(application);
        mDao = db.wordDao();
        mAllWords = mDao.getAlphabetizedWords();
    }

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    void insert(Word word) {
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            mDao.insert(word);
        });
    }

    void deleteAll() {
        RoomDatabase.databaseWriteExecutor.execute(() ->{
            mDao.deleteAll();
        });

    }


}
