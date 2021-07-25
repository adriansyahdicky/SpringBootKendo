package com.apotik.service.rak;

import com.apotik.dto.rak.RakCreateDTO;
import com.apotik.dto.rak.RakUpdateDTO;
import com.apotik.dto.other.ReturnSearch;
import com.apotik.entity.Rak;
import com.apotik.exception.ResourceNotFoundException;
import com.apotik.repository.RakRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RakServiceImpl implements RakService{

    @Autowired
    private RakRepository rakRepository;

    @Override
    public List<Rak> getRaks() {
        return rakRepository.findAll();
    }

    @Override
    public void SaveRak(RakCreateDTO rakCreateDTO) {
        Rak rak = new Rak();
        BeanUtils.copyProperties(rakCreateDTO, rak);
        rakRepository.save(rak);
    }

    @Override
    public void UpdateRak(RakUpdateDTO rakUpdateDTO) {
        Rak rak = rakRepository.findById(rakUpdateDTO.getId()).get();
        BeanUtils.copyProperties(rakUpdateDTO, rak);
        rakRepository.save(rak);
    }

    @Override
    public void DeleteRak(Long id) {
        Rak rak =  rakRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data Rak Tidak Ditemukan"));

        if(rak != null){
            rakRepository.delete(rak);
        }
    }

    @Override
    public Optional<Rak> findById(Long id) {
        return rakRepository.findById(id);
    }

    @Override
    public List<ReturnSearch> findRakByName(String q) {
        List<Rak> listrak =  rakRepository.findByRakByName(q);
        List<ReturnSearch> searchRak = new ArrayList<>();

        //new version
        listrak.forEach(rak -> {
            ReturnSearch rs = new ReturnSearch();
            rs.setId(rak.getId());
            rs.setText(rak.getNameRak());
            searchRak.add(rs);
        });


        return  searchRak;
    }
}
