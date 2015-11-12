package com.acsent.controller;

import com.acsent.model.Category;
import com.acsent.model.Item;
import com.acsent.repository.CategoryRepository;
import com.acsent.repository.ItemRepository;
import com.acsent.repository.UserRepository;
import com.acsent.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@SuppressWarnings("WeakerAccess")
@Controller
public class HomeController {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	UserRepository userRepository;

	@SuppressWarnings("SameReturnValue")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {

        model.addAttribute("user", SecurityUtils.getAppUser());

        model.addAttribute("categoryName", "New Products");

        ArrayList<Category> categories = categoryRepository.findAllByOrderByNameAsc();
		model.addAttribute("categories", categories);

        Page<Item> items = itemRepository.findByIsNewOrderByNameAsc(true, new PageRequest(0, 12));
        model.addAttribute("items", items);

		return "index";
	}

	@RequestMapping("/category/{id}")
	public String category(@PathVariable("id") Long categoryId, Model model){

        return category(categoryId, 0, model);

	}

	@RequestMapping("/category/{id}/page{page}")
	public String category(@PathVariable("id") Long categoryId, @PathVariable("page") int pageNumber, Model model){

        model.addAttribute("user", SecurityUtils.getAppUser());

        ArrayList<Category> categories = categoryRepository.findAllByOrderByNameAsc();
		model.addAttribute("categories", categories);

		Category category = categoryRepository.findOne(categoryId);
        model.addAttribute("categoryName", category.getName());

		Page<Item> items = itemRepository.findByCategoryOrderByNameAsc(category, new PageRequest(pageNumber, 12));
		model.addAttribute("items", items);

		return "products";
	}

    @RequestMapping("/item/{id}")
    public String products(@PathVariable("id") Long itemId, Model model){

        model.addAttribute("user", SecurityUtils.getAppUser());

        ArrayList<Category> categories = categoryRepository.findAllByOrderByNameAsc();
        model.addAttribute("categories", categories);

        Item item = itemRepository.findOne(itemId);
        model.addAttribute("item", item);

        return "productdetail";
    }
}
