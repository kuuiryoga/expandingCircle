package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.FilterSearchDto;
import com.example.demo.model.UserDto;
import com.example.demo.model.Users;
import com.example.demo.service.UsersService;
import com.fasterxml.jackson.datatype.jdk8.OptionalDoubleSerializer;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/selfIntroduction")
public class SelfIntroductionController {

	@Autowired
	Users user;

	@Autowired
	UserDto userDto;

	@Autowired
	FilterSearchDto intoroductionFilterDto;

	@Autowired
	UsersService service;
	
	//遷移時処理
	@GetMapping("")
	public ModelAndView introduction(ModelAndView mv, HttpServletRequest request) {
		List<Users> userList = new ArrayList<>();
		
		userList = sortFilter(this.intoroductionFilterDto, userList);
		
		System.out.println(user.getName());
		
		System.out.println(service.findByIntercepterSearch(this.user));
		
		mv.addObject("userList", userList);
		mv.addObject("intoroductionFilterDto", this.intoroductionFilterDto);
		
		
		//ログインチェック
		if(this.service.loginCheack(this.user).size() > 0 && 
				this.service.loginCheack(this.user).get(0) != null) {
			mv.setViewName("selfIntroduction");
			request.getSession().setAttribute("userId", user.getUserid());
		} else {
		    ModelAndView  model = new ModelAndView("redirect:/");   
		    return model;  
		}
		return mv;
	}
	
	//Ajexの処理
	@GetMapping("/AjexSearch")
	public ModelAndView ajexSearch(ModelAndView mv, HttpServletRequest request,
									@ModelAttribute FilterSearchDto SearchDto) {
		List<Users> userList = new ArrayList<>();
		userList = sortFilter(SearchDto, userList); 
		mv.addObject("userList", userList);
		mv.addObject("intoroductionFilterDto", SearchDto);
		if(this.service.loginCheack(this.user).size() > 0 && 
				this.service.loginCheack(this.user).get(0) != null) {
			//持ち越し様にDIに保存する。
			this.intoroductionFilterDto.setDto(SearchDto);
			
			mv.setViewName("selfIntroduction");
			request.getSession().setAttribute("userId", user.getUserid());
		} else {
		    ModelAndView  model = new ModelAndView("redirect:/");   
		    return model;  
		}
		return mv;
	}
	
	
	//DTOのソート番号を用いてソートしたデータをDBから取得する
	private List<Users> sortFilter(FilterSearchDto FilterDto, List<Users> userList){
		
		Optional<Integer> getNumber = Optional.ofNullable(FilterDto.getSortId());
		switch(getNumber.orElse(1)){
			case 1:
				userList = service.findAllDescOrderOfAge();
			break;
			
			case 2:
				userList = service.findAllAscOrderOfAge();
			break;
			
			case 3:
				userList = service.findAllAscOrderOfName();
			break;
			
			case 4:
				userList = service.findAllDescOrderOfName();
			break;
			
			default:
				userList = service.findAllDescOrderOfAge();
		}
		
		return userList;
	}
	

	
}
