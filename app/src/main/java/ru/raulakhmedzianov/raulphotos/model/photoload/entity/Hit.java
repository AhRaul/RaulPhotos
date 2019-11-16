package ru.raulakhmedzianov.raulphotos.model.photoload.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_hits")
public class Hit {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @Expose
    @SerializedName("webformatURL")
    public String webformatURL;

    @Override
    public String toString() {
        return "Hit{" +
                "id=" + id +
                ", webformatURL='" + webformatURL + '\'' +
                '}';
    }
}
