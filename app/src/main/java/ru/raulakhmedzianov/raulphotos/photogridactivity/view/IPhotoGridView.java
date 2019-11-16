package ru.raulakhmedzianov.raulphotos.photogridactivity.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndStrategy;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface IPhotoGridView extends MvpView {

    @StateStrategyType(value = SkipStrategy.class)
    void openCardActivity(String url);

    @StateStrategyType(value = AddToEndStrategy.class)
    void updateRecyclerView();
}
