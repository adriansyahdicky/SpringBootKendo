package com.apotik.controller.api;

import com.apotik.dto.bahan_baku.BahanBakuCreateDTO;
import com.apotik.dto.bahan_baku.BahanBakuUpdateDTO;
import com.apotik.entity.BahanBaku;
import com.apotik.entity.Obat;
import com.apotik.entity.authentication.CustomUserPrincipal;
import com.apotik.service.bahan_baku.BahanBakuService;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class BahanBakuController {

    @Autowired
    private BahanBakuService bahanBakuService;

    @GetMapping(value = "/api/bahan-bakus")
    public List<BahanBaku> getBahanBakus(){
        return bahanBakuService.getBahanBakus();
    }

    @PostMapping(value = "/api/bahan-bakus/grid")
    public ResponseEntity<Map<String, Object>> bahanBakus(Pageable pageRequest){

        try{
            Page<BahanBaku> pagingBahanBaku = bahanBakuService.getBahanBakusGrid(pageRequest);
            Map<String, Object> response = new HashMap<>();
            response.put("data", pagingBahanBaku.getContent());
            response.put("total_rows", pagingBahanBaku.getTotalElements());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            throw new RuntimeException("error : "+ex.getMessage());
        }

    }

    @GetMapping(value = "/api/bahan-bakus/{id}/by-id")
    public BahanBaku getById(@PathVariable("id") Long id){
        return bahanBakuService.getBahanBakuById(id);
    }

    @PostMapping(value = "/api/bahan-bakus")
    public String saveBahanBaku(@RequestBody BahanBakuCreateDTO bahanBakuCreateDTO) throws Exception {
        JSONObject jsonObject = new JSONObject();

        try{
            bahanBakuService.saveBahanBaku(bahanBakuCreateDTO);
            jsonObject.put("status", "Success");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return jsonObject.toString();
    }

    @PutMapping(value = "/api/bahan-bakus")
    public String updateBahanBaku(@RequestBody BahanBakuUpdateDTO bahanBakuUpdateDTO) throws Exception {
        JSONObject jsonObject = new JSONObject();

        try{
            bahanBakuService.updateBahanBaku(bahanBakuUpdateDTO);
            jsonObject.put("status", "Success");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return jsonObject.toString();
    }

    @DeleteMapping(value = "/api/bahan-bakus/{id}")
    public String deleteMenu(@PathVariable("id") Long id) throws Exception {

        JSONObject jsonObject = new JSONObject();
        try{
            bahanBakuService.deleteBahanBaku(id);
            jsonObject.put("status", "Success");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return jsonObject.toString();

    }

}
