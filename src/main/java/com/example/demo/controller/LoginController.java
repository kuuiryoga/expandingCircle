package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

//login用のコントローラ
@Controller
public class LoginController {
	//PathVariableで userId と password をクエリパラメータから持ってきているので、後ほどフォームクラスを生成して＠RequestParamに変更する。
		@PostMapping("/login")
		public ModelAndView login(@PathVariable String id, @PathVariable String pw, ModelAndView mv) {
			
			mv.setViewName("main1");
			
			return mv;
		}
		
		//とりあえずメソッドだけ用意
		@GetMapping("/logout")
		public ModelAndView logout(ModelAndView mv) {
			
			mv.setViewName("login");
			
			return mv;
		}
}
