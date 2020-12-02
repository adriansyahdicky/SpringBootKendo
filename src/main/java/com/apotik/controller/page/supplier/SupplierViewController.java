package com.apotik.controller.page.supplier;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SupplierViewController {

    @RequestMapping(value = "/supplier", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView view = new ModelAndView();
        view.setViewName("page/supplier/index");
        return view;
    }

}
