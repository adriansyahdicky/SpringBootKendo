package com.apotik.repository;

import com.apotik.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query("Select m from Menu m where m.namaMenu like %:namaMenu%")
    List<Menu> findByNama(@PathVariable(name = "namaMenu") String namaMenu);
}
