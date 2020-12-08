package com.apotik.controller.api;

import com.apotik.entity.Obat;
import com.apotik.entity.Supplier;
import com.apotik.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/laporan")
public class LaporanController {

    @Autowired
    private ObatService obatService;

    @PostMapping(value = "/stockobat")
    public ResponseEntity<Map<String, Object>> stockobat(Pageable pageRequest){

        try{
            Page<Obat> pagingObat = obatService.getObatPages(pageRequest);
            Map<String, Object> response = new HashMap<>();
            response.put("data", pagingObat.getContent());
            response.put("total_rows", pagingObat.getTotalElements());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            throw new RuntimeException("error : "+ex.getMessage());
        }

    }

}
