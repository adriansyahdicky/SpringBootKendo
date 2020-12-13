package com.apotik.repository;

import com.apotik.entity.Rak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RakRepository extends JpaRepository<Rak, Long> {

    @Query(value = "select * from rak where name_rak like %:name%", nativeQuery = true)
    List<Rak> findByRakByName(@Param("name") String name);

}
