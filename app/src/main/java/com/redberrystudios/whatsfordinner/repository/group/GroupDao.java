package com.redberrystudios.whatsfordinner.repository.group;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@Dao
public interface GroupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(GroupEntity group);

    @Query("SELECT * FROM groups WHERE id = :groupId")
    LiveData<GroupEntity> loadProduct(long groupId);

    @Query("SELECT * FROM groups WHERE joinToken = :joinToken")
    LiveData<GroupEntity> loadGroupByJoinToken(String joinToken);

}
