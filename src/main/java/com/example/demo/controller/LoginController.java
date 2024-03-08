package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Forum;
import com.example.demo.model.LoginData;
import com.example.demo.model.Users;
import com.example.demo.service.UsersService;

//login用のコントローラ
@RequestMapping("")
@Controller
public class LoginController {
	
		@Autowired
		Users user;
		
		@Autowired
		UsersService service;

		@GetMapping("/")
		public ModelAndView init(ModelAndView mv) {
			mv.addObject("loginForm", new Users());
			mv.setViewName("login");
			return mv;
		}

		@PostMapping("/login")
		public ModelAndView login(@ModelAttribute Users users, ModelAndView mv) {

			List<Users> userList = service.loginCheack(users);
			// ログインできるユーザーが存在するか
			if ( userList != null && userList.size() > 0 ) {
				// ログイン成功時
				user.setUserid(userList.get(0).getUserid());
				user.setPassword(userList.get(0).getPassword());
//				mv.addObject(loginData);
				mv.setViewName("form_SelfIntroduction");
			} else {
				// ログイン失敗時
				mv.addObject("loginForm", users);
				mv.setViewName("login");
			}
			
			return mv;
		}
		
		//とりあえずメソッドだけ用意
		@GetMapping("/logout")
		public ModelAndView logout(ModelAndView mv) {
			
			mv.setViewName("login");
			
			return mv;
		}
}
