package cn.uctimes.luckyrui.babyname.entity;

import cn.uctimes.commons.base.BaseEntity;
import java.util.Date;

public class LikeNames extends BaseEntity {
    private Integer id;

    private Integer nameId;

    private Integer userId;

    private Date likeTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNameId() {
        return nameId;
    }

    public void setNameId(Integer nameId) {
        this.nameId = nameId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(Date likeTime) {
        this.likeTime = likeTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        LikeNames other = (LikeNames) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getNameId() == null ? other.getNameId() == null : this.getNameId().equals(other.getNameId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getLikeTime() == null ? other.getLikeTime() == null : this.getLikeTime().equals(other.getLikeTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNameId() == null) ? 0 : getNameId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getLikeTime() == null) ? 0 : getLikeTime().hashCode());
        return result;
    }
}