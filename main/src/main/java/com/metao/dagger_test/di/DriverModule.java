package com.metao.dagger_test.di;

import com.metao.dagger_test.service.DriversService;
import com.metao.dagger_test.mvp.DriversView;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by metao on 3/23/2017.
 */

@Module
public class DriverModule {

    private DriversView mDriversView;

    public DriverModule(DriversView driversView) {
        mDriversView = driversView;
    }

    @PerActivity
    @Provides
    DriversService provideDriversService(Retrofit retrofit) {
        return retrofit.create(DriversService.class);
    }

    @PerActivity
    @Provides
    DriversView provideView() {
        return mDriversView;
    }

}
