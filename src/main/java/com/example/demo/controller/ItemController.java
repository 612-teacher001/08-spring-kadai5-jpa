package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	/**
	 * 商品一覧画面表示
	 * @return 商品一覧画面のThymeleafテンプレート名
	 */
	@GetMapping("/items")
	public String index(@RequestParam(required = false) Integer categoryId,
						@RequestParam(required = false) Integer maxPrice,
						@RequestParam(defaultValue = "") String sort,
						@RequestParam(defaultValue = "") String keyword,
						Model model) {
		
		// カテゴリIDによって処理を分岐
		List<Item> itemList = null;
		
		// キーワードと価格上限値の複合検索
		if (keyword.isEmpty()) {
			// キーワードが送信されない場合
			if (maxPrice == null) {
				// 価格上限が送信されない場合
				if (sort.isEmpty()) {
					// 並べ替え指定がない場合
					itemList = itemRepository.findAll();
				} else {
					// 並べ替え指定がある場合
					itemList = itemRepository.findAllByOrderByPriceAsc();
				}
			} else {
				// 価格上限値が送信される場合
				if (sort.isEmpty()) {
					// 並べ替えが指定されていない場合
					itemList = itemRepository.findByPriceLessThanEqual(maxPrice);
				} else {
					// 並べ替えが指定されている場合
					itemList = itemRepository.findByPriceLessThanEqualOrderByPriceAsc(maxPrice);
				}
			}
		} else {
			// キーワードが送信された場合
			if (maxPrice == null) {
				// 価格上限が送信されない場合
				if (sort.isEmpty()) {
					// 並べ替えが指定されていない場合
					itemList = itemRepository.findByNameLike("%" + keyword + "%");
				} else {
					// 並べ替えが指定されている場合
					itemList = itemRepository.findByNameLikeOrderByPriceAsc("%" + keyword + "%");
				}
			} else {
				// 価格上限値が送信される場合
				if (sort.isEmpty()) {
					itemList = itemRepository.findByNameLikeAndPriceLessThanEqual("%" + keyword + "%", maxPrice);
				} else {
					itemList = itemRepository.findByNameLikeAndPriceLessThanEqualOrderByPriceAsc("%" + keyword + "%", maxPrice);
				}
			}
		}
		
		// 取得したカテゴリリストと商品リストをスコープに登録
		model.addAttribute("items", itemList);
		model.addAttribute("maxPrice", maxPrice);
		model.addAttribute("keyword", keyword);
		
		// 画面遷移
		return "items";
	}
}
