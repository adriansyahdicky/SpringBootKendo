package com.apotik.service.barang;

import com.apotik.dto.barang.ObatCreateDTO;
import com.apotik.dto.barang.ObatUpdateDTO;
import com.apotik.dto.other.ReturnSearch;
import com.apotik.entity.Kategori;
import com.apotik.entity.Obat;
import com.apotik.entity.Rak;
import com.apotik.exception.ResourceNotFoundException;
import com.apotik.repository.ObatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObatServiceImpl implements ObatService{

    @Autowired
    private ObatRepository obatRepository;

    @Override
    public List<Obat> getObats() {
        return obatRepository.findAll();
    }

    @Override
    public Page<Obat> getObatPages(Pageable pageable) {
        return obatRepository.findAll(pageable);
    }

    @Override
    public Obat findById(Long id) {
        Obat obat =  obatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data Obat Tidak Ditemukan"));
        return obat;
    }

    @Override
    public void SaveObat(ObatCreateDTO obatCreateDTO) {

        Kategori kategori = new Kategori();
        kategori.setId(obatCreateDTO.getIdKategori());

        Rak rak = new Rak();
        rak.setId(obatCreateDTO.getIdRak());

        Obat obat =
                Obat.builder()
                        .nameObat(obatCreateDTO.getNameObat())
                        .qty(obatCreateDTO.getQty())
                        .hargaJual(obatCreateDTO.getHargaJual())
                        .hargaSupplier(obatCreateDTO.getHargaSupplier())
                        .kategori(kategori)
                        .rak(rak)
                        .build();
        obatRepository.save(obat);
    }

    @Override
    public void UpdateObat(ObatUpdateDTO obatUpdateDTO) {
        Obat obat =  obatRepository.findById(obatUpdateDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Data Obat Tidak Ditemukan"));

        Kategori kategori = new Kategori();
        kategori.setId(obatUpdateDTO.getIdKategori());

        Rak rak = new Rak();
        rak.setId(obatUpdateDTO.getIdRak());

        obat.setNameObat(obatUpdateDTO.getNameObat());
        obat.setHargaJual(obatUpdateDTO.getHargaJual());
        obat.setHargaSupplier(obatUpdateDTO.getHargaSupplier());
        obat.setQty(obatUpdateDTO.getQty());
        obat.setKategori(kategori);
        obat.setRak(rak);
        obatRepository.save(obat);
    }

    @Override
    public void DeleteObat(Long id) {
        Obat obat =  obatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data Obat Tidak Ditemukan"));

        if(obat != null){
            obatRepository.delete(obat);
        }
    }

    @Override
    public List<ReturnSearch> findObatByName(String q) {
        List<Obat> listObat =  obatRepository.findByObatByName(q);
        List<ReturnSearch> searchObat= new ArrayList<>();

        for(Obat o : listObat){
            ReturnSearch returnSearch = new ReturnSearch();
            returnSearch.setId(o.getId());
            returnSearch.setText(o.getNameObat() + " - " + o.getKategori().getNameKategori() + " - Harga : " + o.getHargaSupplier() + "- Lokasi " + o.getRak().getNameRak());
            searchObat.add(returnSearch);
        }

        return  searchObat;
    }
}
