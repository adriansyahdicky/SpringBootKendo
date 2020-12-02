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
    public ResponseEntity<Map<String, Object>> getSuppliers(int pageSize,
                                            int skip){

        //Pageable paging = PageRequest.of(skip, pageSize);

        try{
            //Page<Supplier> pageSupplier = supplierService.getSuppliers(paging);
            List<Supplier> pageSupplier = supplierService.getSuppliers();

            List<Supplier> data_paging = pageSupplier.stream().skip(skip).limit(pageSize).collect(Collectors.toList());
            Map<String, Object> response = new HashMap<>();
            response.put("data", data_paging);
            response.put("total_rows", pageSupplier.size());

//            jsonObject.put("data", pageSupplier.getContent());
//            jsonObject.put("total_rows", pageSupplier.getTotalPages());
//            return jsonObject.toString();

            //System.out.println();
            //return jsonObject;
            //return new JSONObject("data" = pageSupplier.getContent());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            throw new RuntimeException("error : "+ex.getMessage());
        }

    }

}
