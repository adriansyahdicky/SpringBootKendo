package com.apotik.controller.api;

import com.apotik.dto.*;
import com.apotik.entity.Kategori;
import com.apotik.entity.Rak;
import com.apotik.service.KategoriService;
import com.apotik.service.RakService;
import com.apotik.utils.ErrorUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/rak")
public class RakController {

    @Autowired
    private RakService rakService;

    @GetMapping(value = "/getRaks")
    public List<Rak> getRaks(){
        try{
            return rakService.getRaks();
        }catch (Exception ex){
            throw new RuntimeException("error : "+ex.getMessage());
        }

    }

    @GetMapping(value = "/getById/{id}")
    public Rak getRakById(@PathVariable("id")Long id){
        Rak findRak = rakService.findById(id).get();
        return findRak;
    }

    @PostMapping(value = "/createRak")
    public String createRak(@RequestBody @Valid RakCreateDTO rakCreateDTO,
                                 BindingResult bindingResult) throws Exception {

        JSONObject jsonObject = new JSONObject();
        if (bindingResult.hasErrors()){
            return ErrorUtils.customErrors(bindingResult.getAllErrors());
        }
        else{
            try{
                rakService.SaveRak(rakCreateDTO);
                jsonObject.put("status", "Success");
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }
            return jsonObject.toString();
        }

    }

    @PostMapping(value = "/updateRak")
    public String updateRak(@RequestBody @Valid RakUpdateDTO rakUpdateDTO,
                                 BindingResult bindingResult) throws Exception {

        JSONObject jsonObject = new JSONObject();
        if (bindingResult.hasErrors()){
            return ErrorUtils.customErrors(bindingResult.getAllErrors());
        }
        else{
            try{
                rakService.UpdateRak(rakUpdateDTO);
                jsonObject.put("status", "Success");
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }
            return jsonObject.toString();
        }

    }

    @PostMapping(value = "/deleteRak/{id}")
    public String deleteKategori(@PathVariable("id") Long id) throws Exception {

        JSONObject jsonObject = new JSONObject();

        try{
            rakService.DeleteRak(id);
            jsonObject.put("status", "Success");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return jsonObject.toString();
    }

    @GetMapping(value = "/searchRakByName")
    public List<ReturnSearch> searchRakByName(String q) throws Exception {

        try{
            return rakService.findRakByName(q);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
