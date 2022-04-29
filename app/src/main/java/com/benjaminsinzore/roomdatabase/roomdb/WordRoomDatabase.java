package com.benjaminsinzore.roomdatabase.roomdb;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {com.example.android.roomwordssample.Word.class}, version = 1, exportSchema = false)
abstract class WordRoomDatabase extends RoomDatabase {

    abstract com.example.android.roomwordssample.WordDao wordDao();

    private static volatile WordRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {

                com.example.android.roomwordssample.WordDao dao = INSTANCE.wordDao();
                dao.deleteAll();

                com.example.android.roomwordssample.Word word = new com.example.android.roomwordssample.Word("Hello");
                dao.insert(word);
                word = new com.example.android.roomwordssample.Word("World");
                dao.insert(word);
            });
        }
    };
}