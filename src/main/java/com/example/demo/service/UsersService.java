package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsersService {

	@Autowired
	UsersRepository repository;

	
	public List<Users> findByIntercepterSearch(Users user){
		return repository.findByIntercepterSearch(
				user.getName()
				);
	}
	
	
	// データベースよりフォーラム(掲示板)の一覧を年齢の昇順に並び替えて取得する
	public List<Users> findAllAscOrderOfAge() {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "age"));
	}
	
	// データベースよりフォーラム(掲示板)の一覧を年齢の降順に並び替えて取得する
	public List<Users> findAllDescOrderOfAge() {
		return repository.findAll(Sort.by(Sort.Direction.DESC, "age"));
	}
	
	
	// データベースよりフォーラム(掲示板)の一覧を名前の昇順に並び替えて取得する
	public List<Users> findAllAscOrderOfName() {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}
	
	// データベースよりフォーラム(掲示板)の一覧を名前の降順に並び替えて取得する
	public List<Users> findAllDescOrderOfName() {
		return repository.findAll(Sort.by(Sort.Direction.DESC, "name"));
	}
	
	// データベースに値を登録
	public void insert(Users users) {
		repository.save(users);
	}

	public List<Users>  loginCheack(Users users) {
		return repository.findLoginUser(users.getUserid(), users.getPassword());
	} 
	
	//データベースのユーザーデータを更新
	public void update(Users users) {
		repository.updataUser(users.getId()
								,users.getUserid()
								,users.getPassword()
								,users.getName()
								,Integer.valueOf(users.getSex())
								,users.getMail()
								,Integer.valueOf(users.getAge())
								,users.getText()
								,users.getUniqueWord()
								,users.getUpdated());
	}
}

