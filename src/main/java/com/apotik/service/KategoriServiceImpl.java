package com.apotik.service;

import com.apotik.dto.KategoriCreateDTO;
import com.apotik.dto.KategoriUpdateDTO;
import com.apotik.dto.ReturnSearch;
import com.apotik.entity.Kategori;
import com.apotik.exception.ResourceNotFoundException;
import com.apotik.repository.KategoriRepositori;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class KategoriServiceImpl implements KategoriService{

    @Autowired
    private KategoriRepositori kategoriRepository;

    @Override
    public List<Kategori> getKategoris() {
        return kategoriRepository.findAll();
    }

    @Override
    public void SaveKategori(KategoriCreateDTO kategoriCreateDTO) {
        Kategori kategori = new Kategori();
        BeanUtils.copyProperties(kategoriCreateDTO, kategori);
        kategoriRepository.save(kategori);
    }

    @Override
    public void UpdateKategori(KategoriUpdateDTO kategoriUpdateDTO) {
        Kategori kategori = kategoriRepository.findById(kategoriUpdateDTO.getId()).get();
        BeanUtils.copyProperties(kategoriUpdateDTO, kategori);
        kategoriRepository.save(kategori);
    }

    @Override
    public void DeleteKategori(Long id) {
        Kategori kategori =  kategoriRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data Kategori Tidak Ditemukan"));

        if(kategori != null){
            kategoriRepository.delete(kategori);
        }
    }

    @Override
    public Optional<Kategori> findById(Long id) {
        return kategoriRepository.findById(id);
    }

    @Override
    public List<ReturnSearch> findKategoriByName(String q) {

        List<Kategori> listkategori =  kategoriRepository.findByKategoriByName(q);
        List<ReturnSearch> searchKategori = new ArrayList<>();

        //old version
//        for(Kategori k : listkategori){
//            ReturnSearch returnSearch = new ReturnSearch();
//            returnSearch.setId(k.getId());
//            returnSearch.setText(k.getNameKategori() + " - " + k.getSatuan());
//            searchKategori.add(returnSearch);
//        }

        //new version
        listkategori.forEach(kategori -> {
           ReturnSearch rs = new ReturnSearch();
           rs.setId(kategori.getId());
           rs.setText(kategori.getNameKategori() + " - " + kategori.getSatuan());
           searchKategori.add(rs);
        });

        if (searchKategori.isEmpty() && searchKategori.size() <0){
            if (log.isInfoEnabled()){
                log.debug("{\"method\" : \"findKategoriByName\", " +
                        "\"error\" : \"error because data is null\"}");
            }
        }

        return  searchKategori;
    }
}
