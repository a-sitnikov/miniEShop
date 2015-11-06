package com.acsent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class ExamplesController {

    @RequestMapping(value = "/1", method = RequestMethod.GET)
    public String index1(Model model, @CookieValue(value = "id", required = false) String id, HttpServletResponse response) {

//        Page<Item> items = itemRepository.findAllByOrderByNameAsc(new PageRequest(0, 12));
//
//        for (Item item: items){
//            System.out.println(item.getName());
//        }

//        model.addAttribute("items", items);

        if (id == null || id.isEmpty()) {
            id = UUID.randomUUID().toString();
        } else {
            System.out.println(id);
        }

        response.addCookie(new Cookie("id", id));

        return "index1";
    }

}
