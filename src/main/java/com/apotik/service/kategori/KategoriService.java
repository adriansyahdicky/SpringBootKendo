package com.apotik.service.kategori;

import com.apotik.dto.kategori.KategoriCreateDTO;
import com.apotik.dto.kategori.KategoriUpdateDTO;
import com.apotik.dto.other.ReturnSearch;
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
