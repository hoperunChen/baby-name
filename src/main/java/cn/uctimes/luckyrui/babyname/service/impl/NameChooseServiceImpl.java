package cn.uctimes.luckyrui.babyname.service.impl;

import cn.uctimes.commons.ResultInfo;
import cn.uctimes.commons.utils.AssertUtils;
import cn.uctimes.commons.utils.BeanConvertor;
import cn.uctimes.commons.utils.VerifyUtils;
import cn.uctimes.luckyrui.babyname.dao.LikeNamesMapper;
import cn.uctimes.luckyrui.babyname.dao.NamesMapper;
import cn.uctimes.luckyrui.babyname.dao.UserMapper;
import cn.uctimes.luckyrui.babyname.dto.ChoosePageDataDTO;
import cn.uctimes.luckyrui.babyname.entity.LikeNames;
import cn.uctimes.luckyrui.babyname.entity.Names;
import cn.uctimes.luckyrui.babyname.entity.User;
import cn.uctimes.luckyrui.babyname.service.NameChooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 选名业务实现
 *
 * @author chenrui
 * @date 2019-06-16 10:06
 */
@Service
public class NameChooseServiceImpl implements NameChooseService {

	@Autowired
	NamesMapper namesMapper;

	@Autowired
	UserMapper userMapper;

	@Autowired
	LikeNamesMapper likeNamesMapper;


	@Override
	public ResultInfo<ChoosePageDataDTO> nextName(Integer userId) {
		ResultInfo<ChoosePageDataDTO> choosePageDataDTOResultInfo = checkNotCross(userId);
		if (!choosePageDataDTOResultInfo.isSuccess()) {
			return choosePageDataDTOResultInfo;
		}
		Names names = namesMapper.selectNextByUserId(userId);
		if (VerifyUtils.isNull(names)) {
			// TODO: 2019-06-16
		}
		ChoosePageDataDTO choosePageDataDTO = BeanConvertor.createFrom(names, ChoosePageDataDTO.class);
		return ResultInfo.success(choosePageDataDTO);
	}

	@Override
	public ResultInfo likeName(Integer userId, Integer nameId) {
		// 更新名字喜欢次数;
		AssertUtils.assertNotEmpty("参数异常", userId, nameId);
		int updateLikeCount = namesMapper.updateLikeCount(nameId);
		AssertUtils.assertGt("更新喜欢次数失败", updateLikeCount, 0);

		// 插入喜好名字
		LikeNames likeNames = new LikeNames();
		likeNames.setLikeTime(new Date());
		likeNames.setNameId(nameId);
		likeNames.setUserId(userId);
		int insertCount = likeNamesMapper.insert(likeNames);
		AssertUtils.assertGt("记录喜好失败", insertCount, 0);

		// 更新用户进度
		return updateUserSchedule(userId);
	}

	@Override
	public ResultInfo dislikeName(Integer userId) {
		// 更新用户进度
		return updateUserSchedule(userId);
	}

	@Override
	public Integer selectMaxId() {
		Integer maxId = namesMapper.getMaxId();
		return maxId;
	}

	private ResultInfo updateUserSchedule(Integer userId) {
		// 更新用户进度
		User user = userMapper.selectByPrimaryKey(userId);
		AssertUtils.assertNotEmpty("用户信息不存在", user);
		Integer schedule = user.getSchedule();
		Names names = namesMapper.selectNextNameById(schedule);
		Integer newSchedule = VerifyUtils.isNull(names) ? schedule + 1 : names.getId();
		Map<String, Integer> updateScheduleParams = new HashMap<>(3);
		updateScheduleParams.put("userId", userId);
		updateScheduleParams.put("oldSchedule", schedule);
		updateScheduleParams.put("newSchedule", newSchedule);
		int i = userMapper.updateSchedule(updateScheduleParams);
		AssertUtils.assertGt("更新进度失败", i, 0);
		return VerifyUtils.isNull(names) ? ResultInfo.fail(-1200, "操作成功,已经是最后一个名字啦!", null) : ResultInfo.success("操作成功");
	}


	private ResultInfo<ChoosePageDataDTO> checkNotCross(Integer userId) {
		User user = userMapper.selectByPrimaryKey(userId);
		Integer maxId = namesMapper.getMaxId();
		AssertUtils.assertNotEmpty("数据异常", user, maxId);
		if (user.getSchedule() > maxId) {
			return ResultInfo.fail(-1100, "已经全部看完了", null);
		}
		return ResultInfo.success("");
	}

}
