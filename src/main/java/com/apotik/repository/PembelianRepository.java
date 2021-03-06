package com.apotik.repository;

import com.apotik.entity.Pembelian;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PembelianRepository extends JpaRepository<Pembelian, Long> {

    @Override
    @Query(value = "select * from pembelian where status='Pending'", nativeQuery = true)
    List<Pembelian> findAll();

    @Query(value = "select * from pembelian where DATE(tanggal) between :tglMasuk and :tglKeluar", nativeQuery = true)
    Page<Pembelian> findBytanggal(Pageable pageable, @Param("tglMasuk") String tglMasuk, @Param("tglKeluar") String tglKeluar);
}
