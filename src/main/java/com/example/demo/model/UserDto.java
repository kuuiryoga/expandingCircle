package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserDto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String userid;

	private String password;

	private String name;

	private Integer sex;

	private String mail;

	private Integer age;

	private String text;

	private String  uniqueWord;

	private boolean  deleteFlg;

	private LocalDateTime  created;

	private LocalDateTime  updated;
	
	private String[] uniqueWords;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return String.valueOf(this.sex);
	}

	public void setSex(String sex) {
		this.sex = Integer.valueOf(sex);
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAge() {
		return String.valueOf(this.age);
	}

	public void setAge(String age) {
		this.age = Integer.valueOf(age);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUniqueWord() {
		return uniqueWord;
	}

	public void setUniqueWord(String uniqueWord) {
		this.uniqueWord = uniqueWord;
	}

	public boolean isDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(boolean deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	//usersはinit（）で先に生成されるのでコンストラクタでは無く、別枠で更新する
	public void userSet(Users user) {
		this.id = user.getId();
		this.userid = user.getUserid();
		this.password = user.getPassword();
		this.name = user.getName();
		this.age = Integer.valueOf(user.getAge());
		this.sex = Integer.valueOf(user.getSex());
		this.mail = user.getMail();
		this.text = user.getText();
		this.uniqueWord = user.getUniqueWord();
		this.updated = user.getUpdated();
		try {
			this.uniqueWords = user.getUniqueWord().split(",");
		} catch (Exception e) {
			this.uniqueWords = new String[]{""};
		}
	}

	public String[] getUniqueWords() {
		return uniqueWords;
	}

	public void setUniqueWords(String[] uniqueWords) {
		this.uniqueWords = uniqueWords;
	}

}
