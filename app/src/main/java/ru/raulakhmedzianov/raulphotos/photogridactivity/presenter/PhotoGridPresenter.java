package ru.raulakhmedzianov.raulphotos.photogridactivity.presenter;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.raulakhmedzianov.raulphotos.app.App;
import ru.raulakhmedzianov.raulphotos.model.Model;
import ru.raulakhmedzianov.raulphotos.model.database.HitDao;
import ru.raulakhmedzianov.raulphotos.model.photoload.entity.Hit;
import ru.raulakhmedzianov.raulphotos.model.photoload.entity.HitList;
import ru.raulakhmedzianov.raulphotos.model.photoload.retrofit.ApiHelper;
import ru.raulakhmedzianov.raulphotos.photogridactivity.view.IPhotoGridView;
import ru.raulakhmedzianov.raulphotos.photogridactivity.view.IRecyclerViewHolder;

@InjectViewState
public class PhotoGridPresenter extends MvpPresenter<IPhotoGridView> {

    private static final String TAG = "PhotoGridPresenter";

    private RecyclerPresenter recyclerPresenter = new RecyclerPresenter();

    private HitDao hitDao = App.getAppDatabase().hitDao();

    private class RecyclerPresenter implements IRecyclerPresenter {

        private static final String TAG_INSIDE = "RecyclerPresenter";

        private Model recyclerModel= new Model();

        private ApiHelper apiHelper = new ApiHelper();
        private List<Hit> hitList ;

        @Override
        public void bindView(IRecyclerViewHolder iViewHolder) {       //заливка фотографии в cardView
            iViewHolder.setImage(hitList.get(iViewHolder.getPos()).webformatURL);
        }

        @Override
        public int getItemCount() {
            if (hitList != null) {
                return hitList.size();
            }
            return 0;
        }

        //?? стоит ли все методы, которые ниже, вынести из RecyclerPresenter, во внешний класс PhotoGridPresenter, и почему ??

        @Override
        public void setCountIncrementToModel() {
            recyclerModel.setCountIncrement();
        }

        @Override
        public int getCountFromModel() {
            return recyclerModel.getCount();
        }

        @Override
        public void changeActivity(String url) {
            getViewState().openCardActivity(url);
        }

        //взять список URL фотографий из интернета
        public void getAllPhotoFromServer() {
            Observable<HitList> single = apiHelper.requestServer();
            Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(photos -> {
                hitList = photos.hits;
                getViewState().updateRecyclerView();
            }, throwable -> {
                Log.e(TAG_INSIDE, "onError " + throwable);
            });
        }

        //положить Hit в бд
        public void setHitToDb(Hit hit) {
            Log.d(TAG, "putHitToDb: сохраняем Hit в базу");
            Disposable disposable = hitDao.insert(hit).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(id -> {
                        Log.d(TAG, "putHitToDb: Hit сохранён с id " + id);
                        //TODO
                    }, throwable -> {
                        Log.d(TAG, "putData: " + throwable);
                    });
        }

        //взять URL из бд по id
        public String getHitFromDbById(int id) {
            final String[] url = new String[1];
            Disposable disposable = hitDao.getHitById(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(photo -> {
                        Log.d(TAG, "getData: " + photo + " " + Thread.currentThread().getName());
                        url[0] = photo.webformatURL;
                    }, throwable -> {
                        Log.d(TAG, "getData: " + throwable);
                    });
            return url[0];
        }
    }

    public RecyclerPresenter getRecyclerPresenter() {
        return recyclerPresenter;
    }

    @Override
    protected void onFirstViewAttach() {
        recyclerPresenter.getAllPhotoFromServer();
    }

}
