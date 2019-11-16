package ru.raulakhmedzianov.raulphotos.detailactivity.view;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import ru.raulakhmedzianov.raulphotos.R;
import ru.raulakhmedzianov.raulphotos.detailactivity.presenter.DetailPresenter;
import ru.raulakhmedzianov.raulphotos.model.photoload.GlideLoader;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class DetailActivity extends MvpAppCompatActivity implements IDetailView {

    private static final String TAG = "DetailActivity";

    @InjectPresenter
    DetailPresenter detailPresenter;

    @BindView(R.id.imageViewDetail)
    ImageView imageView;

    private GlideLoader glideLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            String url = arguments.get("url").toString();
            this.glideLoader = new GlideLoader(this);
            glideLoader.loadImage(url, imageView);
        } else {
            Log.d(TAG, "argument is null");
        }
    }
}
