package com.oracle.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Slf4j
public class IndexController {

    @GetMapping(value = "/")
    public String selectRegionList(Model model) {
        return "redirect:/main/regions/goList";
    }
}
