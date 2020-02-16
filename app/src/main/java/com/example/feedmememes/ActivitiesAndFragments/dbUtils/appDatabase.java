package com.example.feedmememes.ActivitiesAndFragments.dbUtils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.feedmememes.ActivitiesAndFragments.models.memesDBObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {memesDBObject.class}, version = 1)
public abstract class appDatabase extends RoomDatabase {
    public abstract memesDBObjectDao getMemesDBObjectDao();

    private static volatile appDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static RoomDatabase.Callback sRoomDatabaseCallBack= new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    memesDBObjectDao dao= INSTANCE.getMemesDBObjectDao();
                }
            });
        }
    };
    static appDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (appDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            appDatabase.class, "memes-db")
                            .addCallback(sRoomDatabaseCallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
