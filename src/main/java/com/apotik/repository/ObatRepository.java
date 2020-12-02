package com.apotik.repository;

import com.apotik.entity.Obat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ObatRepository extends JpaRepository<Obat, Long> {

    @Query(value = "select * from obat where name_obat like %:name%", nativeQuery = true)
    List<Obat> findByObatByName(@Param("name") String name);

}
