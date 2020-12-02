package com.apotik.service;

import com.apotik.dto.KategoriCreateDTO;
import com.apotik.dto.KategoriUpdateDTO;
import com.apotik.dto.ReturnSearch;
import com.apotik.entity.Kategori;

import java.util.List;
import java.util.Optional;

public interface KategoriService {
    List<Kategori> getKategoris();
    void SaveKategori(KategoriCreateDTO kategoriCreateDTO);
    void UpdateKategori(KategoriUpdateDTO kategoriCreateDTO);
    void DeleteKategori(Long id);
    Optional<Kategori> findById(Long id);
    List<ReturnSearch> findKategoriByName(String q);
}
