package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.FilterSearchDto;
import com.example.demo.model.UserDto;
import com.example.demo.model.Users;

@RestController
@RequestMapping("/technologySharing")
public class TechnologySharingController {

	@Autowired
	Users user;
	
	@Autowired
	UserDto userDto;

	@Autowired
	FilterSearchDto intoroductionFilterDto;

	@GetMapping("")
	public ModelAndView introduction(ModelAndView mv) {
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
		mv.setViewName("technologySharing");
		return mv;
	}
}
