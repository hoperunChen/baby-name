package cn.uctimes.luckyrui.babyname.service;

import cn.uctimes.commons.ResultInfo;
import cn.uctimes.luckyrui.babyname.dto.UserDTO;

/**
 * user
 *
 * @author chenrui
 * @date 2019-06-15 22:55
 */
public interface UserService {

	ResultInfo<UserDTO> doLogin(String phone);

}
