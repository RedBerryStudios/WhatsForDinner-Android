package com.redberrystudios.whatsfordinner.repository.member;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MemberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MemberEntity member);

    @Query("SELECT * FROM members WHERE groupId = :groupId")
    LiveData<List<MemberEntity>> loadMembersForGroup(long groupId);

}
