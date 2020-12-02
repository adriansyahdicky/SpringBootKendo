package com.apotik.controller.api;

import com.apotik.dto.KasirOrderDTO;
import com.apotik.utils.ErrorUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/kasir")
public class KasirOrderController {

    @PostMapping(value = "/orders")
    public String orders(@RequestBody @Valid KasirOrderDTO kategoriCreateDTO,
                                 BindingResult bindingResult) throws Exception {

        JSONObject jsonObject = new JSONObject();
        if (bindingResult.hasErrors()){
            return ErrorUtils.customErrors(bindingResult.getAllErrors());
        }
        else{
            try{
                //kategoriService.SaveKategori(kategoriCreateDTO);
                jsonObject.put("status", "Success");
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }
            return jsonObject.toString();
        }

    }

}
