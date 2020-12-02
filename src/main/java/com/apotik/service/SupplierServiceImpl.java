package com.apotik.service;

import com.apotik.entity.Supplier;
import com.apotik.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService{

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Supplier> getSuppliers() {
        return supplierRepository.findAll();
    }


//    @Override
//    public Page<Supplier> getSuppliers(Pageable pageable) {
//        List<String> d = new ArrayList<>();
//        d.stream().skip(0).limit(20).collect(Collectors.toList());
//        return supplierRepository.findAll(pageable);
//    }
}
