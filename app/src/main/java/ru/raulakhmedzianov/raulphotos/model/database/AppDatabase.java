package ru.raulakhmedzianov.raulphotos.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import ru.raulakhmedzianov.raulphotos.model.photoload.entity.Hit;

@Database(entities = {Hit.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HitDao hitDao();
}
