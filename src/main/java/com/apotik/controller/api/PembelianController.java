package com.apotik.controller.api;

import com.apotik.dto.PembelianDTO;
import com.apotik.dto.ReturnRequestPembelian;
import com.apotik.entity.Pembelian;
import com.apotik.entity.Supplier;
import com.apotik.service.PembelianService;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/pembelian")
public class PembelianController {

    @Autowired
    private PembelianService pembelianService;

    @GetMapping(value = "/getPembelians")
    public List<Pembelian> getPembelians(){
        try{
            return pembelianService.getPembelians();
        }catch (Exception ex){
            throw new RuntimeException("error : "+ex.getMessage());
        }

    }

    @GetMapping(value = "/getReturnRequestPembelian/{id}")
    public ResponseEntity<Map<String, Object>> getReturnRequestPembelian(@PathVariable("id") Long id){
        try{
            ReturnRequestPembelian requestPembelian = pembelianService.getRequestPembelian(id);
            Map<String, Object> response = new HashMap<>();
            response.put("pembelian", requestPembelian.getPembelian());
            response.put("pembelian_detail", requestPembelian.getPembelianDetails());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            throw new RuntimeException("error : "+ex.getMessage());
        }

    }

    @PostMapping(value = "/requestbarang")
    public String requestbarang(@RequestBody PembelianDTO pembelianDTO) throws Exception {

        JSONObject jsonObject = new JSONObject();

        try{
            pembelianService.RequestBarang(pembelianDTO);
            jsonObject.put("status", "Success");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return jsonObject.toString();

    }

    @GetMapping(value = "/approved/{id}")
    public String approved(@PathVariable("id") Long id) throws Exception {

        JSONObject jsonObject = new JSONObject();

        try{
            pembelianService.Approved(id);
            jsonObject.put("status", "Success");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return jsonObject.toString();

    }



}
