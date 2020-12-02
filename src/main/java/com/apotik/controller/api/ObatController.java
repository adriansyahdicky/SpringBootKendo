package com.apotik.controller.api;

import com.apotik.dto.ObatCreateDTO;
import com.apotik.dto.ObatUpdateDTO;
import com.apotik.dto.ReturnSearch;
import com.apotik.entity.Obat;
import com.apotik.service.ObatService;
import com.apotik.utils.ErrorUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/obat")
public class ObatController {

    @Autowired
    private ObatService obatService;

    @GetMapping(value = "/getObats")
    public List<Obat> getObats(){
        try{
            return obatService.getObats();
        }catch (Exception ex){
            throw new RuntimeException("error : "+ex.getMessage());
        }

    }

    @PostMapping(value = "/createObat")
    public String createObat(@RequestBody @Valid ObatCreateDTO obatCreateDTO,
                                 BindingResult bindingResult) throws Exception {

        JSONObject jsonObject = new JSONObject();
        if (bindingResult.hasErrors()){
            return ErrorUtils.customErrors(bindingResult.getAllErrors());
        }
        else{
            try{
                obatService.SaveObat(obatCreateDTO);
                jsonObject.put("status", "Success");
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }
            return jsonObject.toString();
        }

    }

    @PostMapping(value = "/updateObat")
    public String updateObat(@RequestBody @Valid ObatUpdateDTO obatUpdateDTO,
                             BindingResult bindingResult) throws Exception {

        JSONObject jsonObject = new JSONObject();
        if (bindingResult.hasErrors()){
            return ErrorUtils.customErrors(bindingResult.getAllErrors());
        }
        else{
            try{
                obatService.UpdateObat(obatUpdateDTO);
                jsonObject.put("status", "Success");
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }
            return jsonObject.toString();
        }

    }

    @PostMapping(value = "/deleteObat/{id}")
    public String deleteObat(@PathVariable("id") Long id) throws Exception {

        JSONObject jsonObject = new JSONObject();

        try{
            obatService.DeleteObat(id);
            jsonObject.put("status", "Success");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return jsonObject.toString();
    }

    @GetMapping(value = "/getById/{id}")
    public Obat getObatById(@PathVariable("id")Long id){
        Obat findByObat = obatService.findById(id);
        return findByObat;
    }

    @GetMapping(value = "/searchObatByName")
    public List<ReturnSearch> searchObatByName(String q) throws Exception {

        try{
            return obatService.findObatByName(q);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
