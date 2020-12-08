package com.apotik.repository;

import com.apotik.entity.PembelianDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PembelianDetailRepository extends JpaRepository<PembelianDetail, Long> {

    @Query(value = "select * from pembelian_detail pd where pd.pembelian_id=:idpembelian", nativeQuery = true)
    public List<PembelianDetail> findByPembelianId(@Param("idpembelian") Long idpembelian);

}
