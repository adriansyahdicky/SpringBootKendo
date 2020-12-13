package com.apotik.service;

import com.apotik.entity.Orders;
import com.apotik.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public Integer totalTransaksiPerDay() {
        return ordersRepository.findByTanggal();
    }

    @Override
    public Page<Orders> reportBarangKeluar(Pageable pageable, String tglMasuk, String tglKeluar) {
        return ordersRepository.findBytanggal(pageable, tglMasuk, tglKeluar);
    }
}
