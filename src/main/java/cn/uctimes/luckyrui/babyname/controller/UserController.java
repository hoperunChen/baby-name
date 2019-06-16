package cn.uctimes.luckyrui.babyname.controller;

import cn.uctimes.commons.ResultInfo;
import cn.uctimes.commons.utils.VerifyUtils;
import cn.uctimes.luckyrui.babyname.dto.UserDTO;
import cn.uctimes.luckyrui.babyname.service.NameChooseService;
import cn.uctimes.luckyrui.babyname.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenrui
 */
@Controller
@RequestMapping("pages/")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	NameChooseService nameChooseService;

	@RequestMapping(value = "/login")
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/do_login")
	public String doLogin(Model model, String phone, HttpServletRequest request) {
		ResultInfo<UserDTO> loginResult = userService.doLogin(phone);
		if (null == loginResult || !loginResult.isSuccess()) {
			model.addAttribute("errMsg", loginResult.getMessage());
			return "login";
		}
		model.addAttribute("user", loginResult.getData());
		request.getSession().setAttribute("user", loginResult.getData());

		Integer maxId = nameChooseService.selectMaxId();
		model.addAttribute("maxId", maxId);

		return "choose";
	}
}