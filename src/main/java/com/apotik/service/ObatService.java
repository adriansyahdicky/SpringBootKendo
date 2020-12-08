package com.apotik.service;

import com.apotik.dto.ObatCreateDTO;
import com.apotik.dto.ObatUpdateDTO;
import com.apotik.dto.ReturnSearch;
import com.apotik.entity.Obat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ObatService {
    List<Obat> getObats();
    Page<Obat> getObatPages(Pageable pageable);
    Obat findById(Long id);
    void SaveObat(ObatCreateDTO obatCreateDTO);
    void UpdateObat(ObatUpdateDTO obatUpdateDTO);
    void DeleteObat(Long id);
    List<ReturnSearch> findObatByName(String q);
}
