package com.redberrystudios.whatsfordinner.repository.member;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.redberrystudios.whatsfordinner.repository.group.GroupEntity;

@Entity(tableName = "members",
        indices = {
                @Index(value = "email", unique = true)
        },
        foreignKeys = {
                @ForeignKey(entity = GroupEntity.class,
                        parentColumns = {"id"},
                        childColumns = {"groupId"}
                )
        }
)
public class MemberEntity {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String name;

    private String email;

    private String pictureLink;

    private Long groupId;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberEntity that = (MemberEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (pictureLink != null ? !pictureLink.equals(that.pictureLink) : that.pictureLink != null)
            return false;
        return groupId != null ? groupId.equals(that.groupId) : that.groupId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (pictureLink != null ? pictureLink.hashCode() : 0);
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MemberEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pictureLink='" + pictureLink + '\'' +
                ", groupId=" + groupId +
                '}';
    }
}
