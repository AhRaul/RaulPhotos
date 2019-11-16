package ru.raulakhmedzianov.raulphotos.app;

import android.app.Application;

import androidx.room.Room;
import ru.raulakhmedzianov.raulphotos.model.database.AppDatabase;

public class App extends Application {

    private static AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "photo_database").build();
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
