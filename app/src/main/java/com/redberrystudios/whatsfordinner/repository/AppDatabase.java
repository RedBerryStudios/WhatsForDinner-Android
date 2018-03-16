package com.redberrystudios.whatsfordinner.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.redberrystudios.whatsfordinner.AppExecutors;
import com.redberrystudios.whatsfordinner.repository.group.GroupDao;
import com.redberrystudios.whatsfordinner.repository.group.GroupEntity;
import com.redberrystudios.whatsfordinner.repository.member.MemberDao;
import com.redberrystudios.whatsfordinner.repository.member.MemberEntity;

@Database(version = 1, entities = {GroupEntity.class, MemberEntity.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;

    @VisibleForTesting
    public static final String DATABASE_NAME = "wfd-db";

    //DAOs here
    public abstract GroupDao groupDao();

    public abstract MemberDao memberDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(final Context context, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext(), executors);
                }
            }
        }
        return sInstance;
    }

    private static AppDatabase buildDatabase(final Context appContext,
                                             final AppExecutors executors) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                /*
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(() -> {
                            // Add a delay to simulate a long-running operation
                            addDelay();
                            // Generate the data for pre-population
                            AppDatabase database = AppDatabase.getInstance(appContext, executors);
                            List<ProductEntity> products = DataGenerator.generateProducts();
                            List<CommentEntity> comments =
                                    DataGenerator.generateCommentsForProducts(products);

                            insertData(database, products, comments);
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });
                    }
                })
                */
                .build();
    }

}
