package com.apotik.controller.api;

import com.apotik.dto.transaksi.TransaksiDto;
import com.apotik.service.transaksi.TransaksiService;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransaksiController {

    @Autowired
    private TransaksiService transaksiService;

    @PostMapping(value = "/saveTransaksi")
    public String saveTransaksi(@RequestBody TransaksiDto transaksiDto) throws Exception {

        JSONObject jsonObject = new JSONObject();

        try{
            transaksiService.saveTransaksi(transaksiDto);
            jsonObject.put("status", "Success");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return jsonObject.toString();

    }

}
