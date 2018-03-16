package com.redberrystudios.whatsfordinner.repository.group;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "groups", indices = {
        @Index(value = "joinToken", unique = true)
})
public class GroupEntity {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String name;

    private String joinToken;

    // members: DS.hasMany("member", {inverse: null, async: true}),
    // days: DS.attr(),
    // checklists: DS.hasMany("checklist", {inverse: null, async:true})

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJoinToken() {
        return joinToken;
    }

    public void setJoinToken(String joinToken) {
        this.joinToken = joinToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupEntity groupEntity = (GroupEntity) o;

        if (id != null ? !id.equals(groupEntity.id) : groupEntity.id != null) return false;
        if (name != null ? !name.equals(groupEntity.name) : groupEntity.name != null) return false;
        return joinToken != null ? joinToken.equals(groupEntity.joinToken) : groupEntity.joinToken == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (joinToken != null ? joinToken.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GroupEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", joinToken='" + joinToken + '\'' +
                '}';
    }

}
