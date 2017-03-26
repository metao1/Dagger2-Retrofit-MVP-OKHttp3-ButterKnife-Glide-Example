package com.metao.dagger_test.di;

import com.metao.dagger_test.app.MainActivity;
import dagger.Component;

/**
 * Created by metao on 3/23/2017.
 */
@PerActivity
@Component(modules = DriverModule.class, dependencies = ApplicationComponent.class)
public interface DriverComponent {

    void inject(MainActivity mainActivity);
}
