package com.apotik.controller.api;

import com.apotik.entity.Supplier;
import com.apotik.service.SupplierService;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping(value = "/getSuppliers")
    public ResponseEntity<Map<String, Object>> getSuppliers(Pageable pageRequest){

        try{
            Page<Supplier> pageSupplierPakePaging = supplierService.getSuppliers(pageRequest);
            System.out.println("data " + pageSupplierPakePaging.getContent());
            System.out.println("totalElements " + pageSupplierPakePaging.getTotalElements());

            //List<Supplier> pageSupplier = supplierService.getSuppliers();

            //List<Supplier> data_paging = pageSupplier.stream().skip(skip).limit(pageSize).collect(Collectors.toList());
            Map<String, Object> response = new HashMap<>();
            response.put("data", pageSupplierPakePaging.getContent());
            response.put("total_rows", pageSupplierPakePaging.getTotalElements());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            throw new RuntimeException("error : "+ex.getMessage());
        }

    }

}
