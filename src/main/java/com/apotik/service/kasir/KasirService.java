package com.apotik.service.kasir;

import com.apotik.dto.kasir.KasirOrderDTO;

import java.text.ParseException;

public interface KasirService {
    void SaveKasir(KasirOrderDTO kasirOrderDTO) throws ParseException;
}
