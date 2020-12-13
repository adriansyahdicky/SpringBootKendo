package com.apotik.controller.page.kasir;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KasirViewController {

    @RequestMapping(value = "/barangkeluar", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView view = new ModelAndView();
        view.setViewName("page/kasir/index");
        return view;
    }

    @RequestMapping(value = "/laporanbarangkeluarlist", method = RequestMethod.GET)
    public ModelAndView laporanbarangkeluarlist(){
        ModelAndView view = new ModelAndView();
        view.setViewName("page/kasir/laporanbarangkeluar");
        return view;
    }

}
