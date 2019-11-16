package ru.raulakhmedzianov.raulphotos.photogridactivity.presenter;

import ru.raulakhmedzianov.raulphotos.photogridactivity.view.IRecyclerViewHolder;

public interface IRecyclerPresenter {
    void bindView(IRecyclerViewHolder iViewHolder);
    int getItemCount();
    void setCountIncrementToModel();
    int getCountFromModel();
    void changeActivity(String url);
}
