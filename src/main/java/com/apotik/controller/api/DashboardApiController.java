package com.apotik.controller.api;

import com.apotik.service.order.OrderService;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/dashboard")
public class DashboardApiController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/getTotalTransaksiDateNow")
    public String getTotalTransaksiDateNow(){
        try{
            JSONObject jsonObject = new JSONObject();
            Integer total = orderService.totalTransaksiPerDay();
            jsonObject.put("total_transaksi", total);
            return jsonObject.toString();
        }catch (Exception ex){
            throw new RuntimeException("error : "+ex.getMessage());
        }

    }

}
