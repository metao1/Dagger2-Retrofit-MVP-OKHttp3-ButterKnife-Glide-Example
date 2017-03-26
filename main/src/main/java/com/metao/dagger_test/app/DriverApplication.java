package com.metao.dagger_test.app;

import android.app.Application;
import com.metao.dagger_test.di.ApplicationComponent;
import com.metao.dagger_test.di.ApplicationModule;
import com.metao.dagger_test.di.DaggerApplicationComponent;

/**
 * Created by metao on 3/23/2017.
 */
public class DriverApplication extends Application {

    private static final String BASE_URL = "http://192.168.1.2";
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(getBaseContext(), BASE_URL))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
