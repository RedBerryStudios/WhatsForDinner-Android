package com.redberrystudios.whatsfordinner.di.modules;

import com.redberrystudios.whatsfordinner.api.WFDApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ServiceModule {

    @Provides
    @Singleton
    WFDApiService providesWfdService(Retrofit retrofit) {
        return new WFDApiService(retrofit);
    }

}
