package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Users;
import com.example.demo.service.UsersService;

@Controller
@RequestMapping("/myPage")
public class MyPageController {
	
	
	@Autowired
	Users user;
	
	@Autowired
	UsersService service;
	
	@GetMapping()
	public ModelAndView toMyPage(@ModelAttribute Users user, ModelAndView mv) {
		
		
//		ログイン情報の有無で分岐処理
		if(this.service.loginCheack(this.user).size() > 0 && 
			this.service.loginCheack(this.user).get(0) != null) {
			mv.setViewName("myPage");
			mv.addObject("userData", this.user);
		}else{
			mv.setViewName("redirect:/");
		}
		
		return mv;
	}
	
	@PostMapping("/edit")
	public ModelAndView updataMyData(@ModelAttribute Users user, ModelAndView mv) {
		
		service.update(user);
		this.user = service.loginCheack(user).get(0);
		
		mv.setViewName("redirect:/main/1");
		
		return mv;
	}
	
}
