package com.apotik.controller.api;

import com.apotik.dto.ObatCreateDTO;
import com.apotik.dto.ReturnSearch;
import com.apotik.dto.SupplierCreateDTO;
import com.apotik.dto.SupplierUpdateDTO;
import com.apotik.entity.Supplier;
import com.apotik.service.SupplierService;
import com.apotik.utils.ErrorUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping(value = "/searchSupplierByName")
    public List<ReturnSearch> searchSupplierByName(String q) throws Exception {

        try{
            return supplierService.findSupplierByName(q);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping(value = "/createSupplier")
    public String createSupplier(@RequestBody @Valid SupplierCreateDTO supplierCreateDTO,
                             BindingResult bindingResult) throws Exception {

        JSONObject jsonObject = new JSONObject();
        if (bindingResult.hasErrors()){
            return ErrorUtils.customErrors(bindingResult.getAllErrors());
        }
        else{
            try{
                supplierService.SaveSupplier(supplierCreateDTO);
                jsonObject.put("status", "Success");
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }
            return jsonObject.toString();
        }

    }

    @PostMapping(value = "/updateSupplier/{id}")
    public String updateSupplier(@PathVariable("id") Long id, @RequestBody @Valid SupplierUpdateDTO supplierUpdateDTO,
                                 BindingResult bindingResult) throws Exception {

        JSONObject jsonObject = new JSONObject();
        if (bindingResult.hasErrors()){
            return ErrorUtils.customErrors(bindingResult.getAllErrors());
        }
        else{
            try{
                supplierUpdateDTO.setId(id);
                supplierService.UpdateSupplier(supplierUpdateDTO);
                jsonObject.put("status", "Success");
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }
            return jsonObject.toString();
        }

    }

    @PostMapping(value = "/deleteSupplier/{id}")
    public String deleteSupplier(@PathVariable("id") Long id) throws Exception {

        JSONObject jsonObject = new JSONObject();

        try{
            supplierService.DeleteSupplier(id);
            jsonObject.put("status", "Success");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return jsonObject.toString();
    }

}
