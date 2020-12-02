package com.apotik.controller.api;

import com.apotik.dto.KategoriCreateDTO;
import com.apotik.dto.KategoriUpdateDTO;
import com.apotik.dto.ReturnSearch;
import com.apotik.entity.Kategori;
import com.apotik.service.KategoriService;
import com.apotik.utils.ErrorUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/kategori")
public class KategoriController {

    @Autowired
    private KategoriService kategoriService;

    @GetMapping(value = "/getKategoris")
    public List<Kategori> getKategoris(){
        try{
            return kategoriService.getKategoris();
        }catch (Exception ex){
            throw new RuntimeException("error : "+ex.getMessage());
        }

    }

    @GetMapping(value = "/getById/{id}")
    public Kategori getKategoriById(@PathVariable("id")Long id){
        Kategori findKategori = kategoriService.findById(id).get();
        return findKategori;
    }

    @PostMapping(value = "/createKategori")
    public String createKategori(@RequestBody @Valid KategoriCreateDTO kategoriCreateDTO,
                                    BindingResult bindingResult) throws Exception {

        JSONObject jsonObject = new JSONObject();
        if (bindingResult.hasErrors()){
            return ErrorUtils.customErrors(bindingResult.getAllErrors());
        }
        else{
             try{
                 kategoriService.SaveKategori(kategoriCreateDTO);
                 jsonObject.put("status", "Success");
             }catch (Exception e){
                 throw new Exception(e.getMessage());
             }
             return jsonObject.toString();
        }

    }

    @PostMapping(value = "/updateKategori")
    public String updateKategori(@RequestBody @Valid KategoriUpdateDTO kategoriUpdateDTO,
                                 BindingResult bindingResult) throws Exception {

        JSONObject jsonObject = new JSONObject();
        if (bindingResult.hasErrors()){
            return ErrorUtils.customErrors(bindingResult.getAllErrors());
        }
        else{
            try{
                kategoriService.UpdateKategori(kategoriUpdateDTO);
                jsonObject.put("status", "Success");
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }
            return jsonObject.toString();
        }

    }

    @PostMapping(value = "/deleteKategori/{id}")
    public String deleteKategori(@PathVariable("id") Long id) throws Exception {

            JSONObject jsonObject = new JSONObject();

            try{
                kategoriService.DeleteKategori(id);
                jsonObject.put("status", "Success");
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }
            return jsonObject.toString();
    }

    @GetMapping(value = "/searchKategoriByName")
    public List<ReturnSearch> searchKategoriByName(String q) throws Exception {

        try{
            return kategoriService.findKategoriByName(q);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
