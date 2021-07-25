package com.apotik.controller.api;

import com.apotik.dto.kasir.KasirOrderDTO;
import com.apotik.entity.Orders;
import com.apotik.service.kasir.KasirService;
import com.apotik.service.order.OrderService;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/kasir")
public class KasirOrderController {

    @Autowired
    private KasirService kasirService;

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/orders")
    public String orders(@RequestBody KasirOrderDTO kasirOrderDTO) throws Exception {

        JSONObject jsonObject = new JSONObject();

            try{
                kasirService.SaveKasir(kasirOrderDTO);
                jsonObject.put("status", "Success");
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }
            return jsonObject.toString();

    }

    @PostMapping(value = "/laporanbarangkeluar/{tglMasuk}/{tglKeluar}")
    public ResponseEntity<Map<String, Object>> laporanbarangkeluar(@PathVariable("tglMasuk") String tglMasuk, @PathVariable("tglKeluar") String tglKeluar, Pageable pageRequest){

        try{
            Page<Orders> pembelianPage = orderService.reportBarangKeluar(pageRequest, tglMasuk, tglKeluar);

            Map<String, Object> response = new HashMap<>();
            response.put("data", pembelianPage.getContent());
            response.put("total_rows", pembelianPage.getTotalElements());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            throw new RuntimeException("error : "+ex.getMessage());
        }

    }
}
