package com.apotik.repository;

import com.apotik.entity.BahanBaku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BahanBakuRepository extends JpaRepository<BahanBaku, Long> {
}
