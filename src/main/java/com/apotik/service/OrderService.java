package com.apotik.service;

import com.apotik.entity.Orders;
import com.apotik.entity.Pembelian;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    public Integer totalTransaksiPerDay();
    Page<Orders> reportBarangKeluar(Pageable pageable, String tglMasuk, String tglKeluar);

}
