package ru.raulakhmedzianov.raulphotos.photogridactivity.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.raulakhmedzianov.raulphotos.R;
import ru.raulakhmedzianov.raulphotos.model.photoload.GlideLoader;
import ru.raulakhmedzianov.raulphotos.photogridactivity.presenter.IRecyclerPresenter;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyRecyclerViewHolder> {

    private static final String TAG = "RecyclerAdapter";

    private IRecyclerPresenter iMyRecyclerPresenter;
    private GlideLoader glideLoader;

    public RecyclerAdapter(Context context, IRecyclerPresenter iRecyclerPresenter) {
        this.iMyRecyclerPresenter = iRecyclerPresenter;
        glideLoader = new GlideLoader(context);
    }

    @NonNull
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_recycler, parent, false);
        return new MyRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewHolder holder, int position) {
        holder.position = position;
        iMyRecyclerPresenter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return iMyRecyclerPresenter.getItemCount();
    }

    public class MyRecyclerViewHolder extends RecyclerView.ViewHolder implements IRecyclerViewHolder {

        private int position = 0;
        private String url;

        @BindView(R.id.card_view)
        CardView cardButton;

        @BindView(R.id.imageView)
        ImageView imageView;

        public MyRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setImage(String url) {
            glideLoader.loadImage(url, imageView);                      //заполнение содержимого cardView
            this.url = url;                                             //запомнить url для передачи в DetailPresenter
        }

        @Override
        public int getPos() {
            return position;
        }

        @OnClick(R.id.card_view)
        public void onCardClick() {
            iMyRecyclerPresenter.setCountIncrementToModel();
            int count = iMyRecyclerPresenter.getCountFromModel();
            Log.d(TAG, " count pressed: " + count);
            iMyRecyclerPresenter.changeActivity(url);
        }
    }
}
