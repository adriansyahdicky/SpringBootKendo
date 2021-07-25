package com.apotik.repository;

import com.apotik.entity.ReportStruk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ReportStrukRepository extends JpaRepository<ReportStruk, String> {

    @Query(value = "select * from report_struk where id=:id", nativeQuery = true)
    List<ReportStruk> findByOrderId(@PathVariable("id") Long id);

}
