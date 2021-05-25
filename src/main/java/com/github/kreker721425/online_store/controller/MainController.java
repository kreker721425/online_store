package com.github.kreker721425.online_store.controller;

import com.github.kreker721425.online_store.model.Person;
import com.github.kreker721425.online_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {

    @Value("${spring.profiles.active}")
    private String profiles;

    @Autowired
    private ProductService productService;

    /*@RequestMapping(*//*path = "&page={page}", *//*method = RequestMethod.GET)
    public String main(Model model,
                       @AuthenticationPrincipal Person person*//*,
                       @PathVariable Long page*//*) {
        Map<Object, Object> data = new HashMap<>();

        //long limit = 100L;
        data.put("profile", person);
        data.put("products", productService.findAll());//.findAny((page-1)*limit, limit));

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profiles));
        return "index";
    }*/
}
