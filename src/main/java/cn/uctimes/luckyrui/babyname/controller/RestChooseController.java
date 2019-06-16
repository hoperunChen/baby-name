package cn.uctimes.luckyrui.babyname.controller;

import cn.uctimes.commons.ResultInfo;
import cn.uctimes.commons.utils.VerifyUtils;
import cn.uctimes.luckyrui.babyname.dto.ChoosePageDataDTO;
import cn.uctimes.luckyrui.babyname.dto.UserDTO;
import cn.uctimes.luckyrui.babyname.service.NameChooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 选择名字
 * ¸
 *
 * @author chenrui
 * @date 2019-06-16 10:03
 */
@RestController
@RequestMapping("/choose")
public class RestChooseController {

	@Autowired
	NameChooseService nameChooseService;

	@RequestMapping(value = "next_name", method = RequestMethod.POST)
	public ResultInfo<ChoosePageDataDTO> nextName(HttpServletRequest request) {
		UserDTO userDTO = getUserDTO(request);
		ResultInfo<ChoosePageDataDTO> choosePageDataDTOResultInfo = nameChooseService.nextName(userDTO.getId());
		return choosePageDataDTOResultInfo;
	}


	@RequestMapping(value = "like", method = RequestMethod.POST)
	public ResultInfo like(HttpServletRequest request, Integer nameId) {
		UserDTO userDTO = getUserDTO(request);
		ResultInfo likeNameResult = nameChooseService.likeName(userDTO.getId(), nameId);
		return likeNameResult;
	}

	@RequestMapping(value = "dislike", method = RequestMethod.POST)
	public ResultInfo dislike(HttpServletRequest request) {
		UserDTO userDTO = getUserDTO(request);
		ResultInfo likeNameResult = nameChooseService.dislikeName(userDTO.getId());
		return likeNameResult;
	}

	private UserDTO getUserDTO(HttpServletRequest request) {
		Object user = request.getSession().getAttribute("user");
		if (VerifyUtils.isNull(user)) {
			ResultInfo.fail(-1001, "重新登录", null);
		}
		return (UserDTO) user;
	}

}