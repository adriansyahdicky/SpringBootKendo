package com.apotik.service;

import com.apotik.dto.ObatCreateDTO;
import com.apotik.dto.ObatUpdateDTO;
import com.apotik.dto.ReturnSearch;
import com.apotik.entity.Obat;

import java.util.List;

public interface ObatService {
    List<Obat> getObats();
    Obat findById(Long id);
    void SaveObat(ObatCreateDTO obatCreateDTO);
    void UpdateObat(ObatUpdateDTO obatUpdateDTO);
    void DeleteObat(Long id);
    List<ReturnSearch> findObatByName(String q);
}
