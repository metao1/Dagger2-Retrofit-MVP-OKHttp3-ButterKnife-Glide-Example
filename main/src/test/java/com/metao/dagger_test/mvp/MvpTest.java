package com.metao.dagger_test.mvp;

import android.os.Looper;
import com.metao.dagger_test.models.Driver;
import com.metao.dagger_test.models.Response;
import com.metao.dagger_test.service.DriversService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.Schedulers;

import java.util.ArrayList;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by metao on 3/24/2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Observable.class, AndroidSchedulers.class, Looper.class, Response.class})
public class MvpTest {
    public static final String TEST_ERROR_MESSAGE = "Error loading drivers";

    private RxJavaSchedulersHook mRxJavaSchedulersHook = new RxJavaSchedulersHook() {
        @Override
        public Scheduler getIOScheduler() {
            return Schedulers.immediate();
        }

        @Override
        public Scheduler getNewThreadScheduler() {
            return Schedulers.immediate();
        }
    };
    @Mock
    private DriversService mApiService;
    @Mock
    private Observable<Response> mObservable;
    @InjectMocks
    private DriversPresenter presenter;
    @Mock
    private DriversView mView;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getDrivers() {
        PowerMockito.mockStatic(Looper.class);
        when(AndroidSchedulers.mainThread()).thenReturn(mRxJavaSchedulersHook.getComputationScheduler());
        when(mApiService.getDrivers()).thenReturn(mObservable);
    }

    @Test
    public void onNext() throws Exception {
        Response response = mock(Response.class);
        assertNotNull(response);
        ArrayList<Driver> drivers = response.placemarks;
        when(response.getPlacemarks()).thenReturn(drivers);
        presenter.onNext(response);
        verify(mView, atLeastOnce()).onNextDriverLoad(response);
    }

    @Test
    public void onError() throws Exception {
        presenter.onError(new Throwable(TEST_ERROR_MESSAGE));
        verify(mView, times(0))
                .onErrorLoadingDrivers(new Throwable(TEST_ERROR_MESSAGE));
    }

    @Test
    public void onCompleted() throws Exception {
        presenter.onCompleted();
        verify(mView, times(1)).onDriversLoad();
    }
}
