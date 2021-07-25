package com.apotik.controller.page.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuViewController {

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView view = new ModelAndView();
        view.setViewName("page/menu/index");
        return view;
    }

}
