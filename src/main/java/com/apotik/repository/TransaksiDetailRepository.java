package com.apotik.repository;

import com.apotik.entity.TransaksiDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaksiDetailRepository extends JpaRepository<TransaksiDetail, Long> {
}
