package com.redberrystudios.whatsfordinner.di.modules;

import android.app.Application;

import com.redberrystudios.whatsfordinner.AppExecutors;
import com.redberrystudios.whatsfordinner.repository.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    AppExecutors providesAppExecutors() {
        return new AppExecutors();
    }

}
