package com.apotik.controller.page.kategori;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KategoriViewController {

    @RequestMapping(value = "/kategori", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView view = new ModelAndView();
        view.setViewName("page/kategori/index");
        return view;
    }

}
