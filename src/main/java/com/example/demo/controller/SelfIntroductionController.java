package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.FilterSearchDto;
import com.example.demo.model.UserDto;
import com.example.demo.model.Users;
import com.example.demo.service.UsersService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/selfIntroduction")
public class SelfIntroductionController {

	@Autowired
	Users user;

	@Autowired
	UserDto userDto;

	@Autowired
	FilterSearchDto intoroductionFilterDto;

	@Autowired
	UsersService service;
	

	@GetMapping("")
	public ModelAndView introduction(ModelAndView mv, HttpServletRequest request) {
		intoroductionFilterDto = new FilterSearchDto();
		List<Users> userList = new ArrayList<>();
		userList.add(new Users());
		userList.add(new Users());
		userList.add(new Users());
		userList.add(new Users());
		userList.add(new Users());
		userList.add(new Users());
		userList.add(new Users());
		userList.add(new Users());
		mv.addObject("userList", userList);
		intoroductionFilterDto = new FilterSearchDto();
		intoroductionFilterDto.setSortId(1);
		mv.addObject("intoroductionFilterDto", intoroductionFilterDto);
		if(this.service.loginCheack(this.user).size() > 0 && 
				this.service.loginCheack(this.user).get(0) != null) {
			mv.setViewName("selfIntroduction");
			request.getSession().setAttribute("userId", user.getUserid());
		} else {
		    ModelAndView  model = new ModelAndView("redirect:/");   
		    return model;  
		}
		return mv;
	}

}
