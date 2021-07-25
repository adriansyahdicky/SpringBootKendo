package com.apotik.service.supplier;

import com.apotik.dto.other.ReturnSearch;
import com.apotik.dto.supplier.SupplierCreateDTO;
import com.apotik.dto.supplier.SupplierUpdateDTO;
import com.apotik.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface SupplierService {

    Page<Supplier> getSuppliers(Pageable pageable);
    List<Supplier> getSuppliers();
    List<ReturnSearch> findSupplierByName(String q);
    void SaveSupplier(SupplierCreateDTO supplierCreateDTO);
    void UpdateSupplier(SupplierUpdateDTO supplierUpdateDTO);
    void DeleteSupplier(Long id);

}
