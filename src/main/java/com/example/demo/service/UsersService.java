package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsersService {

	@Autowired
	UsersRepository repository;

	// データベースよりフォーラム(掲示板)の一覧を取得
	public List<Users> findAll() {
		return repository.findAll();
	}

	// データベースに値を登録
	public void insert(Users users) {
		repository.save(users);
	}

	public List<Users>  loginCheack(Users users) {
		return repository.findLoginUser(users.getUserid(), users.getPassword());
	} 
	
//	public Users getUserData(Users user) {
//		return repository.findById(user.getId());
//	}
	
	//データベースのユーザーデータを更新
	public void update(Users users) {
		repository.save(users);
	}
}

