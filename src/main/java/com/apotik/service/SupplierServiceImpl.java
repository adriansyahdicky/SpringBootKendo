package com.apotik.service;

import com.apotik.dto.ReturnSearch;
import com.apotik.dto.SupplierCreateDTO;
import com.apotik.dto.SupplierUpdateDTO;
import com.apotik.entity.Supplier;
import com.apotik.exception.ResourceNotFoundException;
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

    @Override
    public List<ReturnSearch> findSupplierByName(String q) {
        List<Supplier> listSupplier =  supplierRepository.findBySupplierByName(q);
        List<ReturnSearch> searchKategori = new ArrayList<>();

        listSupplier.forEach(supplier -> {
            ReturnSearch rs = new ReturnSearch();
            rs.setId(supplier.getId());
            rs.setText(supplier.getNameSupplier());
            searchKategori.add(rs);
        });

        return searchKategori;
    }

    @Override
    public void SaveSupplier(SupplierCreateDTO supplierCreateDTO) {
        Supplier supplier =
                Supplier.builder()
                        .nameSupplier(supplierCreateDTO.getNameSupplier())
                        .telepon(supplierCreateDTO.getTelepon())
                        .alamat(supplierCreateDTO.getAlamat())
                        .build();
        supplierRepository.save(supplier);
    }

    @Override
    public void UpdateSupplier(SupplierUpdateDTO supplierUpdateDTO) {
        Supplier supplier = supplierRepository.findById(supplierUpdateDTO.getId()).get();
        supplier.setNameSupplier(supplierUpdateDTO.getNameSupplier());
        supplier.setTelepon(supplierUpdateDTO.getTelepon());
        supplier.setAlamat(supplierUpdateDTO.getAlamat());
        supplierRepository.save(supplier);
    }

    @Override
    public void DeleteSupplier(Long id) {
        Supplier supplier =  supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data Supplier Tidak Ditemukan"));

        if(supplier != null){
            supplierRepository.delete(supplier);
        }
    }


    @Override
    public Page<Supplier> getSuppliers(Pageable pageable) {
//        List<String> d = new ArrayList<>();
//        d.stream().skip(0).limit(20).collect(Collectors.toList());
        return supplierRepository.findAll(pageable);
    }
}
