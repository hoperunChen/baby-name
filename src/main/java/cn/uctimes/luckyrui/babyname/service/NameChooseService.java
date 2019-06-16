package cn.uctimes.luckyrui.babyname.service;

import cn.uctimes.commons.ResultInfo;
import cn.uctimes.luckyrui.babyname.dto.ChoosePageDataDTO;

/**
 * 选名业务类
 *
 * @author chenrui
 * @date 2019-06-15 20:49
 */
public interface NameChooseService {

	ResultInfo<ChoosePageDataDTO> nextName(Integer userId);

	ResultInfo likeName(Integer userId, Integer nameId);

	ResultInfo dislikeName(Integer userId);

	Integer selectMaxId();
}
