package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

// getter(), setter()を自動生成するアノテーション
@Data
// JPAのエンティティであることを示すアノテーション
@Entity
// エンティティに対応するテーブル名を指定。 "forums"部分はPostgreSQLで作成したテーブル名
@Table(name = "forums")
public class Forum {

    // エンティティの主キーを指定。今回であれば "id" カラムになる
	@Id
    // オートインクリメント。主キー "id" の値を自動採番する
	@GeneratedValue(strategy = GenerationType.IDENTITY)

    // カラムに名前を付与
	@Column(name = "id")
	private Integer id;

	@Column(name = "title")
	private String title;

	@Column(name = "body")
	private String body;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
