package com.apotik.service;

import com.apotik.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public Integer totalTransaksiPerDay() {
        return ordersRepository.findByTanggal();
    }
}
