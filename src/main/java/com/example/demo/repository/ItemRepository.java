package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	/**
	 * カテゴリ検索：SELECT * FROM items WHERE categoryId = ?
	 * @param categoryId カテゴリID
	 * @return 商品リスト
	 */
	List<Item> findByCategoryId(Integer categoryId);

}
