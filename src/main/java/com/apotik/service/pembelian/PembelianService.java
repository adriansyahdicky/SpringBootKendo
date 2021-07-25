package com.apotik.service.pembelian;

import com.apotik.dto.pembelian.PembelianDTO;
import com.apotik.dto.pembelian.PembelianDetailUpdateDTO;
import com.apotik.dto.other.ReturnRequestPembelian;
import com.apotik.entity.Pembelian;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;

public interface PembelianService {
    void RequestBarang(PembelianDTO pembelianDTO) throws ParseException;
    void Approved(Long id) throws ParseException;
    void Update(PembelianDetailUpdateDTO pembelianDetailUpdateDTO) throws ParseException;
    List<Pembelian> getPembelians();
    ReturnRequestPembelian getRequestPembelian(Long id);
    Page<Pembelian> reportPembelian(Pageable pageable, String tglMasuk, String tglKeluar);
}
