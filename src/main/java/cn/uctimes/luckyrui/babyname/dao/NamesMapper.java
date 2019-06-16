package cn.uctimes.luckyrui.babyname.dao;

import cn.uctimes.luckyrui.babyname.entity.Names;

import java.util.Map;

public interface NamesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Names record);

    int insertSelective(Names record);

    Names selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Names record);

    int updateByPrimaryKey(Names record);

	Names selectNextByUserId(Integer userId);

    Integer getMaxId();

    Names selectNextNameById(Integer schedule);

    int updateLikeCount(Integer nameId);
}