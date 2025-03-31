package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Item {

	/**
	 * フィールド
	 */
	@Id
	private int id;
	@Column(name = "category_id")
	private int categoryId;
	private String name;
	private int price;
	
	/**
	 * デフォルトコンストラクタ
	 */
	public Item() {}

	/**
	 * コンストラクタ
	 * @param id         商品ID
	 * @param categoryId カテゴリID
	 * @param name       商品名
	 * @param price      価格
	 */
	public Item(int id, int categoryId, String name, int price) {
		this.id = id;
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}
