package com.acsent.controller;

import com.acsent.model.Category;
import com.acsent.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {

	@Autowired
	CategoryRepository categoryRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {

		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);

		//List<Category> list = session.createCriteria(Category.class).list();

		model.addAttribute("number", 5);
		return "index";
	}
}
