package ru.raulakhmedzianov.raulphotos.model.photoload.retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.raulakhmedzianov.raulphotos.model.photoload.entity.Photo;

public interface IApiService {
    @GET("api")
    Observable<Photo> getPhoto(@Query("key") String key);
}
