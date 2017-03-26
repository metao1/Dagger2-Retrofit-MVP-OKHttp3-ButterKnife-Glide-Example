package com.metao.dagger_test.service;

import com.metao.dagger_test.models.Response;
import retrofit2.http.GET;
import rx.Observable;

import javax.inject.Singleton;

/**
 * Created by metao on 3/23/2017.
 */
@Singleton
public interface DriversService {

    @GET("/website/v5/drivers.php")
    public Observable<Response> getDrivers();
}
