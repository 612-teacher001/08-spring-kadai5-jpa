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

	/**
	 * 価格検索：SELECT * FROM items WHERE price <= ?
	 * @param maxPrice 価格の上限値
	 * @return 商品リスト
	 */
	List<Item> findByPriceLessThanEqual(Integer maxPrice);

	/**
	 * 価格の昇順に並べ替え：SELECT * FROM items ORDER BY price ASC
	 * @return 商品リスト
	 */
	List<Item> findAllByOrderByPriceAsc();


}
