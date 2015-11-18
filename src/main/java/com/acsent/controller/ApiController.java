package com.acsent.controller;

import com.acsent.api.CartInfo;
import com.acsent.model.AppUser;
import com.acsent.model.CartDetail;
import com.acsent.model.Item;
import com.acsent.repository.CartDetailRepository;
import com.acsent.repository.ItemRepository;
import com.acsent.repository.UserRepository;
import com.acsent.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ApiController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Inject
    RememberMeServices rememberMeServices;

    @RequestMapping(value = "/api/addtocart", method = RequestMethod.POST)
    public @ResponseBody CartInfo addToCart(Long itemId, HttpServletRequest request, HttpServletResponse response) throws Exception{

        AppUser appUser = SecurityUtils.getAppUser();
        if (appUser == null) {

            appUser = new AppUser();
            appUser.setPassword("");
            appUser.setEnabled(true);
            appUser.setTemp(true);
            userRepository.save(appUser);
            
            SecurityUtils.logInUser(appUser, request, response, rememberMeServices);
        }

        Item item = itemRepository.findOne(itemId);
        if (item == null) {
            throw new Exception(String.format("Item with id %n not found", itemId));
        }

        CartDetail cartDetail = new CartDetail();
        cartDetail.setAppUser(appUser);
        cartDetail.setItem(item);
        cartDetail.setQuantity(1);
        cartDetail.setPrice(item.getPrice());
        cartDetail.setSum(item.getPrice());
        cartDetailRepository.save(cartDetail);

        Long cartCount = cartDetailRepository.countByAppUser(appUser);
        Float cartSum = cartDetailRepository.sumByAppUser(appUser);

        CartInfo cartInfo = new CartInfo();
        cartInfo.setCount(cartCount);
        cartInfo.setSum(cartSum);

        return cartInfo;
    }

}
