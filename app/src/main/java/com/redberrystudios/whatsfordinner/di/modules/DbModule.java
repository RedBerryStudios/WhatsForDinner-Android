package com.redberrystudios.whatsfordinner.di.modules;

import android.app.Application;

import com.redberrystudios.whatsfordinner.AppExecutors;
import com.redberrystudios.whatsfordinner.repository.AppDatabase;
import com.redberrystudios.whatsfordinner.repository.group.GroupDao;
import com.redberrystudios.whatsfordinner.repository.group.GroupRepository;
import com.redberrystudios.whatsfordinner.api.WFDApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    @Provides
    @Singleton
    AppDatabase providesAppDatabase(Application application, AppExecutors appExecutors) {
        return AppDatabase.getInstance(application, appExecutors);
    }

    @Provides
    @Singleton
    public GroupDao providesGroupDao(AppDatabase appDatabase) {
        return appDatabase.groupDao();
    }

    @Provides
    @Singleton
    public GroupRepository providesGroupRepository(GroupDao groupDao, WFDApiService wfdApiService) {
        return new GroupRepository(groupDao, wfdApiService);
    }

}
