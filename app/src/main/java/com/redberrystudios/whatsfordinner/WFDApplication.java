package com.redberrystudios.whatsfordinner;

import android.app.Application;

import com.redberrystudios.whatsfordinner.di.components.DaggerNetComponent;
import com.redberrystudios.whatsfordinner.di.components.DaggerRepositoryComponent;
import com.redberrystudios.whatsfordinner.di.components.RepositoryComponent;
import com.redberrystudios.whatsfordinner.di.modules.AppModule;
import com.redberrystudios.whatsfordinner.di.modules.DbModule;
import com.redberrystudios.whatsfordinner.di.modules.NetModule;
import com.redberrystudios.whatsfordinner.di.modules.ServiceModule;

public class WFDApplication extends Application {

    private static final String BASE_URL = "https://wfdapi.herokuapp.com";

    private RepositoryComponent mRepositoryComponent;

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.mRepositoryComponent = createRepositoryComponent();

        this.mNetComponent = createNetComponent();
    }

    private RepositoryComponent createRepositoryComponent() {
        return DaggerRepositoryComponent.builder()
                .appModule(new AppModule(this))
                .dbModule(new DbModule())
                .serviceModule(new ServiceModule())
                .netModule(new NetModule(BASE_URL))
                .build();
    }

    private NetComponent createNetComponent() {
        return DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BASE_URL))
                .build();
    }

    public RepositoryComponent getmRepositoryComponent() {
        return mRepositoryComponent;
    }

    public NetComponent getmNetComponent() {
        return mNetComponent;
    }
}
