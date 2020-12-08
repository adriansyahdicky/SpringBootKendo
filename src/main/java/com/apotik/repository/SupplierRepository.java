package com.apotik.repository;

import com.apotik.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Page<Supplier> findAll(Pageable pageable);

    @Query(value = "select * from supplier where name_supplier like %:name%", nativeQuery = true)
    List<Supplier> findBySupplierByName(@Param("name") String name);

}
