package com.apotik.controller.page.history;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HistoryViewController {
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView view = new ModelAndView();
        view.setViewName("page/history/index");
        return view;
    }
}
