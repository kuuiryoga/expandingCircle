package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Forum;
import com.example.demo.service.ForumService;

@RequestMapping("")
@Controller
public class ForumController {

    // 特定のアノテーションを付与したクラスのインスタンスを使用可能にする(依存性注入・DI) 
	@Autowired
	ForumService service;

	// トップページ(フォームがある画面)
    // HTTPリクエストの"GET"を受け付けるメソッドに付与するアノテーション
	@GetMapping("/")
	public String top(Model model) {
		model.addAttribute("forum", new Forum());
		return "top";
	}

	// データベースへの登録
    // HTTPリクエストの"POST"を受け付けるメソッドに付与するアノテーション
	@PostMapping("/create")
	public String saveForum(@ModelAttribute Forum forum, Model model) {

        // serviceクラスのinsertメソッドを呼び出し、DBに入力値を登録
		service.insert(forum);
		return "result";
	}

}
