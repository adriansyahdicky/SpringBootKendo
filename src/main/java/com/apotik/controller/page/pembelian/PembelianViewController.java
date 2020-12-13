package com.apotik.controller.page.pembelian;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PembelianViewController {

    @RequestMapping(value = "/requestbarang", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView view = new ModelAndView();
        view.setViewName("page/pembelian/index");
        return view;
    }

    @RequestMapping(value = "/barangkeluarlist", method = RequestMethod.GET)
    public ModelAndView barangkeluarlist(){
        ModelAndView view = new ModelAndView();
        view.setViewName("page/pembelian/barangkeluarlist");
        return view;
    }

    @RequestMapping(value = "/laporanbarangmasuklist", method = RequestMethod.GET)
    public ModelAndView laporanbarangmasuklist(){
        ModelAndView view = new ModelAndView();
        view.setViewName("page/pembelian/laporanbarangmasuklist");
        return view;
    }

}
