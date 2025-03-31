package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
	
	/**
	 * フィールド
	 */
	@Id
	private int id;
	private String name;
	
	/**
	 * デフォルトコンストラクタ
	 */
	public Category() {}

	/**
	 * コンストラクタ
	 * @param id   カテゴリID
	 * @param name カテゴリ名
	 */
	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
