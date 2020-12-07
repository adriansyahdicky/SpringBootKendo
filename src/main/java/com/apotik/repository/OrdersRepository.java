package com.apotik.repository;

import com.apotik.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query(value = "select sum(total_price) from orders where DATE(tanggal) = curdate();", nativeQuery = true)
    Integer findByTanggal();

}
