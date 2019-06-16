package cn.uctimes.luckyrui.babyname.dao;

import cn.uctimes.luckyrui.babyname.entity.LikeNames;

public interface LikeNamesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LikeNames record);

    int insertSelective(LikeNames record);

    LikeNames selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LikeNames record);

    int updateByPrimaryKey(LikeNames record);
}