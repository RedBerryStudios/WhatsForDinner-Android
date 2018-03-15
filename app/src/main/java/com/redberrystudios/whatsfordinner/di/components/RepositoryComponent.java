package com.redberrystudios.whatsfordinner.di.components;

import com.redberrystudios.whatsfordinner.di.modules.AppModule;
import com.redberrystudios.whatsfordinner.di.modules.DbModule;
import com.redberrystudios.whatsfordinner.di.modules.NetModule;
import com.redberrystudios.whatsfordinner.di.modules.ServiceModule;
import com.redberrystudios.whatsfordinner.repository.group.GroupRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetModule.class, DbModule.class, AppModule.class, ServiceModule.class})
public interface RepositoryComponent {

    void inject(GroupRepository groupRepository);

}
