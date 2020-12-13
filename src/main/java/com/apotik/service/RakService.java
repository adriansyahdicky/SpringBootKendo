package com.apotik.service;

import com.apotik.dto.*;
import com.apotik.entity.Rak;

import java.util.List;
import java.util.Optional;

public interface RakService {
    List<Rak> getRaks();
    void SaveRak(RakCreateDTO rakCreateDTO);
    void UpdateRak(RakUpdateDTO rakUpdateDTO);
    void DeleteRak(Long id);
    Optional<Rak> findById(Long id);
    List<ReturnSearch> findRakByName(String q);
}
