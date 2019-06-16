package cn.uctimes.luckyrui.babyname.dto;

import cn.uctimes.commons.base.BaseDto;
import cn.uctimes.commons.utils.BeanConvertor;
import cn.uctimes.luckyrui.babyname.entity.User;
import lombok.Data;

/**
 * 用户信息
 *
 * @author chenrui
 * @date 2019-06-15 20:58
 */
@Data
public class UserDTO extends BaseDto<UserDTO, User> {

	Integer id;

	String phone;

	Integer schedule;


	@Override
	public User convertTo() {
		return BeanConvertor.createFrom(this, User.class);
	}

	@Override
	public UserDTO convertFrom(User fromEntity) {
		return BeanConvertor.convertTo(fromEntity, this);
	}

}