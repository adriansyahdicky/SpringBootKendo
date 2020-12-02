package com.apotik.repository;

import com.apotik.dto.ReturnSearch;
import com.apotik.entity.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KategoriRepositori extends JpaRepository<Kategori, Long> {

    @Query(value = "select * from kategori where name_kategori like %:name%", nativeQuery = true)
    List<Kategori> findByKategoriByName(@Param("name") String name);
}
