package com.apotik.service;

import com.apotik.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface SupplierService {

    //Page<Supplier> getSuppliers(Pageable pageable);
    List<Supplier> getSuppliers();

}
