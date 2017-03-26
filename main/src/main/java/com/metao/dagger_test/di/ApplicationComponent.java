package com.metao.dagger_test.di;

import android.content.Context;
import dagger.Component;
import retrofit2.Retrofit;

import javax.inject.Singleton;

/**
 * Created by metao on 3/23/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Retrofit exposeRetrofit();

    Context exposeContext();
}
