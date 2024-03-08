package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.LoginData;
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
	public ModelAndView toMyPage(ModelAndView mv) {
		
		System.out.println("myPage - sessionTest; "+ this.service.loginCheack(user).get(0));
		
//		ログイン情報の有無で分岐処理
		if(this.service.loginCheack(user).get(0) != null && this.service.loginCheack(user).size() > 0) {
			mv.setViewName("myPage");
		}else{
			mv.setViewName("login");
		}
		
		return mv;
	}
	
	@PostMapping("/edit")
	public ModelAndView updataMyData(@ModelAttribute Users user, ModelAndView mv) {
//		service.updata(user);
		mv.setViewName("form_SelfIntroduction");
		
		return mv;
	}
	
}
