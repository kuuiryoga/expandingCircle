package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Users;
import com.example.demo.service.FormPattern;
import com.example.demo.service.Messege;
import com.example.demo.service.UsersService;

import lombok.RequiredArgsConstructor;

//login用のコントローラ
@RequestMapping("")
@Controller
@RequiredArgsConstructor
public class LoginController {
	
		@Autowired
		Users user;
		
		@Autowired
		UsersService service;

		@Autowired
		MessageSource messageSource;
		
		@GetMapping("/")
		public ModelAndView init(ModelAndView mv) {
			mv.addObject("loginForm", new Users());
			mv.setViewName("login");
			return mv;
		}

		@PostMapping("/login")
		public ModelAndView login(@ModelAttribute Users users, ModelAndView mv) {

			
			
			//patternで英数字２０文字以内か確認する
			FormPattern checkUserId = new FormPattern(users.getUserid());
			checkUserId.notBlank()
			.formLimit(3, 20)
			.notFullWidthCharacter();
			
			//patternで英数字２０文字以内か確認する
			FormPattern checkPassWord = new FormPattern(users.getPassword());						
			checkPassWord.notBlank()
			.formLimit(3, 20)
			.notFullWidthCharacter();
			
			//Patternチェック
			if(checkUserId.isFormsCheck() && checkPassWord.isFormsCheck()) {
				
				List<Users> userList = service.loginCheack(users);
				// ログインできるユーザーが存在するか
				if ( userList != null && userList.size() > 0 ) {
					// ログイン成功時
					//未完成
					user.userSet(users);
					mv.setViewName("selfIntroduction");
				} else {
					// ログイン失敗時
					mv.addObject("loginForm", users);
					mv.addObject("errorMge", Messege.getMessege(messageSource, "login.wrongInput"));
					mv.setViewName("login");
				}
			}else {
				//Patternでfalse場合
				mv.addObject("loginForm", users);
				//入力フォームごとにエラーメッセージを別々に送る
				mv.addObject("errorMgeId", Messege.getMessege(messageSource, checkUserId));
				mv.addObject("errorMgePw", Messege.getMessege(messageSource, checkPassWord));
				mv.setViewName("login");
			}
			
			return mv;
		}
		
		//logout
		@GetMapping("/logout")
		public String logout() {
			
			user.userSet(null);
			
			return "redirect:/";
		}
}
