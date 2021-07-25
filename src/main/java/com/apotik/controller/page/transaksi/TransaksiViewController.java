package com.apotik.controller.page.transaksi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TransaksiViewController {
    @RequestMapping(value = "/transaksi", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView view = new ModelAndView();
        view.setViewName("page/transaksi/index");
        return view;
    }
}
