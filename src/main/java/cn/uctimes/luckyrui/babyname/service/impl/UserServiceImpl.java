package cn.uctimes.luckyrui.babyname.service.impl;

import cn.uctimes.commons.ResultInfo;
import cn.uctimes.commons.utils.BeanConvertor;
import cn.uctimes.commons.utils.VerifyUtils;
import cn.uctimes.luckyrui.babyname.dao.NamesMapper;
import cn.uctimes.luckyrui.babyname.dao.UserMapper;
import cn.uctimes.luckyrui.babyname.dto.UserDTO;
import cn.uctimes.luckyrui.babyname.entity.User;
import cn.uctimes.luckyrui.babyname.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * userimpl
 *
 * @author chenrui
 * @date 2019-06-15 22:56
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public ResultInfo<UserDTO> doLogin(String phone) {
		if (VerifyUtils.isNull(phone) || !VerifyUtils.isMobileForChina(phone)) {
			return ResultInfo.fail("请输入正确的手机号");
		}
		UserDTO userDTO = new UserDTO();
		// 查询用户
		User user = userMapper.selectByPrimaryPhone(phone);
		if (VerifyUtils.isNull(user) || VerifyUtils.isNull(user.getId())) {
			// 创建用户
			user = new User();
			user.setName(phone);
			user.setPhone(phone);
			user.setSchedule(1);
			userMapper.insertSelective(user);
		}
		userDTO = BeanConvertor.convertTo(user, userDTO);
		return ResultInfo.success(userDTO);
	}
}
