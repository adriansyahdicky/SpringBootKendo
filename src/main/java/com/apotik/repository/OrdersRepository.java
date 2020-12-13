package com.apotik.repository;

import com.apotik.entity.Orders;
import com.apotik.entity.Pembelian;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query(value = "select sum(total_price) from orders where DATE(tanggal) = curdate();", nativeQuery = true)
    Integer findByTanggal();

    @Query(value = "select * from orders where DATE(tanggal) between :tglMasuk and :tglKeluar", nativeQuery = true)
    Page<Orders> findBytanggal(Pageable pageable, @Param("tglMasuk") String tglMasuk, @Param("tglKeluar") String tglKeluar);

}
