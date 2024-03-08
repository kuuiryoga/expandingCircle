package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
@NamedQuery(name = "findLoginUser", query = "select u from Users u where u.userid = :userid and u.password = :password and deleteFlg = false order by u.id asc")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "userid")
	private String userid;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "sex")
	private String sex;

	@Column(name = "mail")
	private String mail;

	@Column(name = "age")
	private String  age;

	@Column(name = "text")
	private String text;

	@Column(name = "uniqueWord")
	private String  uniqueWord;

	@Column(name = "deleteFlg")
	private boolean  deleteFlg;

	@Column(name = "created")
	private LocalDateTime  created;

	@Column(name = "updated")
	private LocalDateTime  updated;

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
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
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


}
