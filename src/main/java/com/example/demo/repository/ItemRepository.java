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

	/**
	 * 商品名の部分一致検索：SELECT * FROM items WHERE name LIKE ?
	 * @param keyword 検索キーワード
	 * @return 商品リスト
	 */
	List<Item> findByNameContaining(String keyword);

	/**
	 * 商品名の部分一致検索：SELECT * FROM items WHERE name LIKE ?
	 * @param keyword プレースホルダ付き検索キーワード
	 * @return 商品リスト
	 */
	List<Item> findByNameLike(String keyword);

	/**
	 * 商品名の部分一致検索かつ価格の上限値以下の商品検索
	 * SELECT * FROM items WHERE name LIKE :keyword AND price <= :maxPrice
	 * @param keyword  検索キーワード
	 * @param maxPrice 価格上限値
	 * @return 商品リスト
	 */
	List<Item> findByNameLikeAndPriceLessThanEqual(String keyword, Integer maxPrice);

}
