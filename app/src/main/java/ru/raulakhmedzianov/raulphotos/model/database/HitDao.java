package ru.raulakhmedzianov.raulphotos.model.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Single;
import ru.raulakhmedzianov.raulphotos.model.photoload.entity.Hit;

@Dao
public interface HitDao {

    @Query("SELECT * FROM table_hits")
    Single<List<Hit>> getAll();

    @Query("SELECT * FROM table_hits WHERE id = :id")
    Single<Hit> getHitById(int id);

    @Insert
    Single<Long> insert(Hit hit);

    @Insert
    Single<List<Long>> insertList(List<Hit> hits);

    @Delete
    Single<Integer> delete(Hit hit);

    @Update
    Single<Integer> update(Hit hit);
}
