package ru.raulakhmedzianov.raulphotos.model.photoload.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.raulakhmedzianov.raulphotos.model.photoload.entity.HitList;

public class ApiHelper {
    public Observable<HitList> requestServer() {

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        //http://pixabay.com/api/?key=9250926-552b631cddef606bad3e807d2
        IApiService api = new Retrofit.Builder()
                .baseUrl("https://pixabay.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(IApiService.class);

        return api.getPhoto("9250926-552b631cddef606bad3e807d2").subscribeOn(Schedulers.io());
    }
}
