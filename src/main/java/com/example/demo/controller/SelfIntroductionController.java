package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
		

		userDto.userSet(user);
		mv.addObject("intoroductionFilterDto", this.intoroductionFilterDto);
		mv.addObject("userData", userDto);
		mv.addObject("userList", sortFilter(this.intoroductionFilterDto));
		
		
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

	
	@PostMapping("/search")
	public ModelAndView search(ModelAndView mv, HttpServletRequest request, @ModelAttribute FilterSearchDto intoroductionFilterDto) {
		List<UserDto> userDtoList = new ArrayList<>();
		userDtoList = sortCategoryFilter(intoroductionFilterDto); 
		mv.addObject("userList", userDtoList);
		mv.addObject("intoroductionFilterDto", intoroductionFilterDto);
		if(this.service.loginCheack(this.user).size() > 0 && 
				this.service.loginCheack(this.user).get(0) != null) {
			//持ち越し様にDIに保存する。
			this.intoroductionFilterDto.setDto(intoroductionFilterDto);
			userDto.userSet(user);
			mv.addObject("userData", userDto);
			
			mv.setViewName("selfIntroduction");
		} else {
		    ModelAndView  model = new ModelAndView("redirect:/");   
		    return model;  
		}
		return mv;
	}

	
	//Ajexの処理
	@PostMapping("/AjexSearch")
	public ModelAndView ajexSearch(ModelAndView mv, HttpServletRequest request,
									@ModelAttribute FilterSearchDto SearchDto) {
		List<UserDto>  userList = new ArrayList<>();
		userList = sortFilter(SearchDto); 
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
	private List<UserDto> sortFilter(FilterSearchDto FilterDto){
		List<Users> userList = new ArrayList<>();
		List<UserDto> userDtoList = new ArrayList<>();
		
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

		for (Users dto : userList) {
			UserDto userDto = new UserDto();
			userDto.userSet(dto);
			userDtoList.add(userDto);
		}
		return userDtoList;
	}

	// カテゴリーソート
	private List<UserDto> sortCategoryFilter(FilterSearchDto FilterDto){
		List<Users> userList = new ArrayList<>();
		List<UserDto> userDtoList = new ArrayList<>();
		
		Optional<Integer> getNumber = Optional.ofNullable(FilterDto.getSortId());
		List<String> words;
		try {
			words = new ArrayList<>(Arrays.asList(FilterDto.getUniqueText()));
		} catch (Exception e) {
			words = new ArrayList<>();
		}
		if ( FilterDto.getSearchText() == null || FilterDto.getSearchText().equals("") ) {
			System.out.println("ssss");
			return sortFilter(FilterDto);
		}
		System.out.println("sads");
		userList = service.findCategoryIntroduction(words, FilterDto.getSearchText());
		System.out.println("axcs");
		switch(getNumber.orElse(1)){
			case 1:
				userList.sort(Comparator.comparing(Users::getAge,Comparator.reverseOrder()));
			break;
			
			case 2:
				userList.sort(Comparator.comparing(Users::getAge,Comparator.naturalOrder()));
			break;
			
			case 3:
				userList.sort(Comparator.comparing(Users::getName,Comparator.reverseOrder()));
			break;
			
			case 4:
				userList.sort(Comparator.comparing(Users::getName,Comparator.naturalOrder()));
			break;
			
			default:
				userList.sort(Comparator.comparing(Users::getAge,Comparator.reverseOrder()));
		}
		for (Users dto : userList) {
			UserDto userDto = new UserDto();
			userDto.userSet(dto);
			userDtoList.add(userDto);
		}
		return userDtoList;
	}

	@ModelAttribute("sortOptions")
    public List<String> sortChecboxList() {
        List<String> list = new ArrayList<String>();
        list.add("Java");
        list.add("Python");
        list.add("PHP");
        list.add("C#");
        list.add("JavaScript");
        list.add("C");
        return list;
    }

	
}
