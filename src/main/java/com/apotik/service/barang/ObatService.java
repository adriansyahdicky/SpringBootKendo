package com.apotik.service.barang;

import com.apotik.dto.barang.ObatCreateDTO;
import com.apotik.dto.barang.ObatUpdateDTO;
import com.apotik.dto.other.ReturnSearch;
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
