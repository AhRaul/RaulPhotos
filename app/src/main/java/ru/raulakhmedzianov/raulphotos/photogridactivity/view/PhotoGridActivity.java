package ru.raulakhmedzianov.raulphotos.photogridactivity.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.raulakhmedzianov.raulphotos.R;
import ru.raulakhmedzianov.raulphotos.detailactivity.view.DetailActivity;
import ru.raulakhmedzianov.raulphotos.photogridactivity.presenter.PhotoGridPresenter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class PhotoGridActivity extends MvpAppCompatActivity implements IPhotoGridView {

    private static final String TAG = "PhotoGridActivity";

    private RecyclerAdapter recyclerAdapter;

    @BindView(R.id.activity_recycler)
    RecyclerView recyclerView;

    @InjectPresenter
    PhotoGridPresenter mainPresenter;

    @ProvidePresenter
    public PhotoGridPresenter providePresenter() {
        return new PhotoGridPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_grid);
        ButterKnife.bind(this);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(this, mainPresenter.getRecyclerPresenter());
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void openCardActivity(String url) {
        Intent intent = new Intent(PhotoGridActivity.this, DetailActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    @Override
    public void updateRecyclerView() {
        Log.d(TAG, "updateRecyclerView: ");
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPhotoFromStorage(String fileName) {
        Log.d(TAG, "showPhotoFromStorage: получение фотографии с хранилища телефона по fileName " + fileName);
        //TODO
    }
}
