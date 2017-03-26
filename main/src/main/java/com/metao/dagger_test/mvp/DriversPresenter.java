package com.metao.dagger_test.mvp;

import com.metao.dagger_test.models.Response;
import com.metao.dagger_test.service.DriversService;
import rx.Observable;
import rx.Observer;
import rx.functions.Func0;

import javax.inject.Inject;

/**
 * Created by metao on 3/23/2017.
 */
public class DriversPresenter extends MainPresenter<DriversView> implements Observer<Response> {

    @Inject
    public DriversService mApiService;

    @Inject
    DriversPresenter() {

    }

    public void getDrivers() {
        getView().onLoading();
        subscribe(getDriversObserver(), this);
    }

    private Observable<Response> getDriversObserver() {
        return Observable.defer(new Func0<Observable<Response>>() {
            @Override
            public Observable<Response> call() {
                return mApiService.getDrivers();
            }
        });
    }


    @Override
    public void onCompleted() {
        getView().onDriversLoad();
    }

    @Override
    public void onError(Throwable e) {
        getView().onErrorLoadingDrivers(e);
    }

    @Override
    public void onNext(Response response) {
        getView().onNextDriverLoad(response);
    }

}
