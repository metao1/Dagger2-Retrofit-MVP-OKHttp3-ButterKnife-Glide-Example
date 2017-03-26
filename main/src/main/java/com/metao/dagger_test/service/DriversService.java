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

    @GET("/metao1/322bf21f2666773ccbca4e05c779c38f/raw/757acbce75f0ec8fc19838f58f803e8cd0cf210e/drivers.json")
    public Observable<Response> getDrivers();
}
