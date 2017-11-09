package com.metao.dagger_test.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.metao.dagger_test.R;
import com.metao.dagger_test.adpter.DriverAdapter;
import com.metao.dagger_test.di.ApplicationComponent;
import com.metao.dagger_test.di.DaggerDriverComponent;
import com.metao.dagger_test.di.DriverModule;
import com.metao.dagger_test.models.Driver;
import com.metao.dagger_test.models.Response;
import com.metao.dagger_test.mvp.DriversPresenter;
import com.metao.dagger_test.mvp.DriversView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements DriversView {

    private static final String TAG = MainActivity.class.getSimpleName();
    @Inject
    public DriversPresenter driversPresenter;

    @BindView(R.id.driver_recycle_view)
    RecyclerView driverRecycleView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private List<Driver> driverList;
    private DriverAdapter driverAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        initToolbar();
        driverList = new ArrayList<>();
        driverRecycleView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        driverAdapter = new DriverAdapter(this, driverList);
        driverRecycleView.setNestedScrollingEnabled(false);
        driverRecycleView.setAdapter(driverAdapter);
        createDriversComponent();
    }

    private void initToolbar() {
        toolbar.setTitle(getResources().getString(R.string.all_cars));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void createDriversComponent() {
        DaggerDriverComponent
                .builder()
                .applicationComponent(getApplicationComponent())
                .driverModule(new DriverModule(this))
                .build()
                .inject(this);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((DriverApplication) getApplication()).getApplicationComponent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onPostCreate: ");
        driversPresenter.getDrivers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDriversLoad() {
        Toast.makeText(this, "Drivers loaded completely", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoading() {
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNextDriverLoad(Response response) {
        Log.d(TAG, "onNextDriverLoad: Drivers size:" + response.getPlacemarks().size());
        this.driverList.addAll(response.getPlacemarks());
        this.driverAdapter.setDriversList(response.getPlacemarks());
    }

    @Override
    public void onErrorLoadingDrivers(Throwable e) {
        Log.d(TAG, "onErrorLoadingDrivers: " + e.getMessage());
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
