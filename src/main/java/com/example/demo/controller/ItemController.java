package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
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
						Model model) {
		// カテゴリリストを取得
		List<Category> categoryList = categoryRepository.findAll();
		
		// カテゴリIDによって処理を分岐
		List<Item> itemList = null;
		if (categoryId == null) {
			// 全商品検索
			itemList = itemRepository.findAll();
		} else {
			// カテゴリ検索
			itemList = itemRepository.findByCategoryId(categoryId);
		}
		
		// 価格上限値以下の商品の検索
		if (maxPrice != null) {
			itemList = itemRepository.findByPriceLessThanEqual(maxPrice);
		}
		
		// 価格による並べ替え
		if (sort.equals("priceAsc")) {
			itemList = itemRepository.findAllByOrderByPriceAsc();
		}
		
		// 取得したカテゴリリストと商品リストをスコープに登録
		model.addAttribute("categories", categoryList);
		model.addAttribute("items", itemList);
		model.addAttribute("maxPrice", maxPrice);
		
		// 画面遷移
		return "items";
	}
}
