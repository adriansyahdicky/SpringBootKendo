package com.apotik.service;

import com.apotik.dto.PembelianDTO;
import com.apotik.dto.PembelianDetailUpdateDTO;
import com.apotik.dto.ReturnRequestPembelian;
import com.apotik.entity.Pembelian;

import java.text.ParseException;
import java.util.List;

public interface PembelianService {
    void RequestBarang(PembelianDTO pembelianDTO) throws ParseException;
    void Approved(Long id) throws ParseException;
    void Update(PembelianDetailUpdateDTO pembelianDetailUpdateDTO) throws ParseException;
    List<Pembelian> getPembelians();
    ReturnRequestPembelian getRequestPembelian(Long id);
}
