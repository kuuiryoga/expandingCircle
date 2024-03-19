package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Users;

@RestController
@RequestMapping("/technologySharing")
public class TechnologySharingController {

	@Autowired
	Users user;

	@GetMapping("")
	public ModelAndView introduction(ModelAndView mv) {
		mv.setViewName("technologySharing");
		return mv;
	}
}
