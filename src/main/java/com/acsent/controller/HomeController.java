package com.acsent.controller;

import com.acsent.model.Category;
import com.acsent.model.Item;
import com.acsent.repository.CategoryRepository;
import com.acsent.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class HomeController {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ItemRepository itemRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {

		ArrayList<Category> categories = categoryRepository.findAllByOrderByNameAsc();
		model.addAttribute("categories", categories);

        Page<Item> items = itemRepository.findAllByOrderByNameAsc(new PageRequest(0, 10));
        model.addAttribute("items", items);

		return "index";
	}

    @RequestMapping(value = "/1", method = RequestMethod.GET)
    public String index1(Model model) {

        Page<Item> items = itemRepository.findAllByOrderByNameAsc(new PageRequest(0, 10));

        for (Item item: items){
            System.out.println(item.getName());
        }

        model.addAttribute("items", items);

        return "index1";
    }

	@RequestMapping("/category/{id}")
	public String category(@PathVariable("id") Long categoryId, Model model){

		return "index";
	}
	@RequestMapping("/category/{id}/page{page}")
	public String category(@PathVariable("id") Long categoryId, @PathVariable("page") Long pageNumber, Model model){

		return "index";
	}
}
