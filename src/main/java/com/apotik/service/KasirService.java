package com.apotik.service;

import com.apotik.dto.KasirOrderDTO;

import java.text.ParseException;

public interface KasirService {
    void SaveKasir(KasirOrderDTO kasirOrderDTO) throws ParseException;
}
