package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.UsersService;

@Controller
@RequestMapping("/myPage")
public class MyPageController {
	
//  @Autowired
//	LoginSession loginSession;  //ログイン情報を保持する（予定） 
	
	@Autowired
	UsersService userData;
	
	@GetMapping()
	public ModelAndView toMyPage(/*@ModelAttribute LoginSession loginSession,*/ModelAndView mv) {
		
//		ログイン情報の有無で分岐処理
//		if(loginSession != null) {
//			mv.setViewName("myPage");
//		}else{
			mv.setViewName("login");
//		}
		
		return mv;
	}
	
	@PostMapping("/edit")
	public ModelAndView updataMyData(ModelAndView mv) {
		return mv;
	}
	
}
