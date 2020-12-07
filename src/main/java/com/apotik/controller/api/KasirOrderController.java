package com.apotik.controller.api;

import com.apotik.dto.KasirOrderDTO;
import com.apotik.service.KasirService;
import com.apotik.utils.ErrorUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/kasir")
public class KasirOrderController {

    @Autowired
    private KasirService kasirService;

    @PostMapping(value = "/orders")
    public String orders(@RequestBody KasirOrderDTO kasirOrderDTO) throws Exception {

        JSONObject jsonObject = new JSONObject();

            try{
                kasirService.SaveKasir(kasirOrderDTO);
                jsonObject.put("status", "Success");
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }
            return jsonObject.toString();

    }

}
