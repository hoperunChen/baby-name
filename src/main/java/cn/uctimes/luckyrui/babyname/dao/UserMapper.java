package cn.uctimes.luckyrui.babyname.dao;

import cn.uctimes.luckyrui.babyname.entity.User;

import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User selectByPrimaryPhone(String phone);

	int updateSchedule(Map<String, Integer> updateScheduleParams);
}