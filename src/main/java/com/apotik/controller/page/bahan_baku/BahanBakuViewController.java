package com.apotik.controller.page.bahan_baku;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BahanBakuViewController {
    @RequestMapping(value = "/bahanbaku", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView view = new ModelAndView();
        view.setViewName("page/bahanbaku/index");
        return view;
    }

    @RequestMapping(value = "/laporanbahanbaku", method = RequestMethod.GET)
    public ModelAndView laporanbahanbaku(){
        ModelAndView view = new ModelAndView();
        view.setViewName("page/bahanbaku/laporan");
        return view;
    }
}
