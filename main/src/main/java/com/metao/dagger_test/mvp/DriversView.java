package com.metao.dagger_test.mvp;

import com.metao.dagger_test.models.Response;

/**
 * Created by metao on 3/23/2017.
 */
public interface DriversView {

    public void onDriversLoad();

    public void onLoading();

    public void  onNextDriverLoad(Response response);

    public void onErrorLoadingDrivers(Throwable e);
}
