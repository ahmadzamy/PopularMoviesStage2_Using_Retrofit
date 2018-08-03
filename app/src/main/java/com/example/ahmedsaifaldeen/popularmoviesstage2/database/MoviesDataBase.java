package com.example.ahmedsaifaldeen.popularmoviesstage2.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

@Database(entities = MoviesEntry.class, version = 1, exportSchema = false)
public abstract class MoviesDataBase extends RoomDatabase {
    private static final String LOG_TAG = MoviesDataBase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "todolist";
    private static MoviesDataBase sInstance;

    public static MoviesDataBase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        MoviesDataBase.class, MoviesDataBase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract MoviesDao moviesDao();
}
