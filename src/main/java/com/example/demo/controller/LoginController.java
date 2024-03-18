package com.example.demo.controller;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.annotation.NonAuth;
import com.example.demo.model.Users;
import com.example.demo.service.FormPattern;
import com.example.demo.service.Messege;
import com.example.demo.service.UsersService;

import jakarta.servlet.http.HttpServletRequest;
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

		
		@NonAuth
		@GetMapping("/")
		public ModelAndView init(ModelAndView mv) {
			mv.addObject("loginForm", new Users());
			mv.setViewName("login");
			return mv;
		}

		@PostMapping("/login")
		public ModelAndView login(@ModelAttribute Users users, HttpServletRequest request, ModelAndView mv) {

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
					request.getSession().setAttribute("userId", user.getUserid());
					user.userSet(userList.get(0));
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
				
				//UserIdのエラー
				if(!checkUserId.isFormsCheck()){
					mv.addObject("errorMgeId", Messege.getMessege(messageSource, checkUserId));
				}
				//passwordのエラー
				if(!checkPassWord.isFormsCheck()){
					mv.addObject("errorMgePw", Messege.getMessege(messageSource, checkPassWord));
				}
				
				mv.setViewName("login");
			}
			
			return mv;
		}
		
		//logout
		@GetMapping("/logout")
		public String logout(HttpServletRequest request) {
			
			user = new Users();
			request.getSession().removeAttribute("userId");
			
			return "redirect:/";
		}
}
