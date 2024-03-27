package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.UsersService;


//Mainコンテンツのコントローラ
@RestController
@RequestMapping("/main")
public class MainController {

	@Autowired
	Users user;
	
	@Autowired
	UsersService usersService;
	
	private List<Users> users;
	
	
	//遷移用メソッド（非同期処理で内部だけ変更する形にしてもOK）
		@GetMapping("/1")
		public ModelAndView introduction(ModelAndView mv) {
			mv.setViewName("selfIntroduction");
			users = usersService.findAllAscOrderOfName();
			System.out.println(users);
			users.forEach(System.out::println);
			return mv;
		}
		
		
		//遷移用メソッド（非同期処理で内部だけ変更する形にしてもOK）
		@GetMapping("/2")
		public ModelAndView sharing(ModelAndView mv) {
			mv.setViewName("technologySharing");
			return mv;
		}
}
