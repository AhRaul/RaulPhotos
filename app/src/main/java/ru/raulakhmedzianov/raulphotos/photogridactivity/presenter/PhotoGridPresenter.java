package ru.raulakhmedzianov.raulphotos.photogridactivity.presenter;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.raulakhmedzianov.raulphotos.model.Model;
import ru.raulakhmedzianov.raulphotos.model.photoload.entity.Hit;
import ru.raulakhmedzianov.raulphotos.model.photoload.entity.Photo;
import ru.raulakhmedzianov.raulphotos.model.photoload.retrofit.ApiHelper;
import ru.raulakhmedzianov.raulphotos.photogridactivity.view.IPhotoGridView;
import ru.raulakhmedzianov.raulphotos.photogridactivity.view.IRecyclerViewHolder;

@InjectViewState
public class PhotoGridPresenter extends MvpPresenter<IPhotoGridView> {

    RecyclerPresenter recyclerPresenter = new RecyclerPresenter();

    private class RecyclerPresenter implements IRecyclerPresenter {

        private static final String TAG = "RecyclerPresenter";

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

        public void getAllPhoto() {
            Observable<Photo> single = apiHelper.requestServer();
            Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(photos -> {
                hitList = photos.hits;
                getViewState().updateRecyclerView();
            }, throwable -> {
                Log.e(TAG, "onError " + throwable);
            });
        }
    }


    public RecyclerPresenter getRecyclerPresenter() {
        return recyclerPresenter;
    }

    @Override
    protected void onFirstViewAttach() {
        recyclerPresenter.getAllPhoto();
    }

}
