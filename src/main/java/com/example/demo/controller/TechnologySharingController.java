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
import com.example.demo.repository.SharesRepository;
import com.example.demo.service.SharesService;
import com.example.demo.service.UsersService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/technologySharing")
public class TechnologySharingController {

	@Autowired
	Users user;
	
	@Autowired
	FilterSearchDto technologySharingFilterDto;

	@Autowired
	SharesService sharesService;
	
	@Autowired
	UsersService usersService;
	
	
	@GetMapping("")
	public ModelAndView introduction(ModelAndView mv, HttpServletRequest request) {
		
		
		List<Users> userList = new ArrayList<>();
		this.technologySharingFilterDto = new FilterSearchDto();
		mv.addObject("userList", userList);
		mv.addObject("technologySharingFilterDto", this.technologySharingFilterDto);
		
		//ログインチェック
		if(this.usersService.loginCheack(this.user).size() > 0 && 
			this.usersService.loginCheack(this.user).get(0) != null) {
			mv.setViewName("technologySharing");
			request.getSession().setAttribute("userId", user.getUserid());
		} else {
		    mv.setViewName("redirect:/");
		}
		return mv;
	}
}
