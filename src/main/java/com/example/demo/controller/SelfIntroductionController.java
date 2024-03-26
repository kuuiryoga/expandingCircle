package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.FilterSearchDto;
import com.example.demo.model.Users;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/selfIntroduction")
public class SelfIntroductionController {

	@Autowired
	Users user;

	@Autowired
	FilterSearchDto intoroductionFilterDto;

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
		if  (user.getUserid() != null)  {
			mv.setViewName("selfIntroduction");
			request.getSession().setAttribute("userId", user.getUserid());
		} else {
		    ModelAndView  model = new ModelAndView("redirect:/");   
		    return model;  
		}
		return mv;
	}

}
