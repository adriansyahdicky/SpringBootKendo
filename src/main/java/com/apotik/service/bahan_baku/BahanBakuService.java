package com.apotik.service.bahan_baku;

import com.apotik.dto.bahan_baku.BahanBakuCreateDTO;
import com.apotik.dto.bahan_baku.BahanBakuUpdateDTO;
import com.apotik.dto.history.HistoryCreateDto;
import com.apotik.entity.BahanBaku;
import com.apotik.entity.authentication.CustomUserPrincipal;
import com.apotik.exception.ResourceNotFoundException;
import com.apotik.repository.BahanBakuRepository;
import com.apotik.service.history.HistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BahanBakuService {

    @Autowired
    private BahanBakuRepository bahanBakuRepository;

    @Autowired
    private HistoryService historyService;

    public List<BahanBaku> getBahanBakus(){
        return bahanBakuRepository.findAll();
    }

    public BahanBaku getBahanBakuById(Long id){
        return bahanBakuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data Bahan Baku Cannot Find"));
    }

    public void saveBahanBaku(BahanBakuCreateDTO bahanBakuCreateDTO) {
        
        String nameUser = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            CustomUserPrincipal userDetail = (CustomUserPrincipal) auth.getPrincipal();
            nameUser = userDetail.getFirstName() + " " + userDetail.getLastName();
        }
        
        BahanBaku bahanBaku =
                BahanBaku.builder()
                        .namaBahan(bahanBakuCreateDTO.getNamaBahan())
                        .qty(bahanBakuCreateDTO.getQty())
                        .hargaBeli(bahanBakuCreateDTO.getHargaBeli())
                        .tanggalBeli(new Date())
                        .build();
        bahanBakuRepository.save(bahanBaku);

        HistoryCreateDto historyCreateDto =
                HistoryCreateDto.builder()
                .namaUser(nameUser)
                .activity("Memasukan Bahan Baku " + bahanBakuCreateDTO.getNamaBahan())
                .build();
        historyService.saveHistory(historyCreateDto);
    }

    public void updateBahanBaku(BahanBakuUpdateDTO bahanBakuUpdateDTO){

        String nameUser = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            CustomUserPrincipal userDetail = (CustomUserPrincipal) auth.getPrincipal();
            nameUser = userDetail.getFirstName() + " " + userDetail.getLastName();
        }

        Optional<BahanBaku> bahanBaku =
                Optional.ofNullable(bahanBakuRepository.findById(bahanBakuUpdateDTO.getId()))
                        .orElseThrow(() -> new ResourceNotFoundException("Bahan Baku Cannot Found In Database"));
        if(bahanBaku.isPresent()){
            bahanBaku.get().setId(bahanBakuUpdateDTO.getId());
            bahanBaku.get().setNamaBahan(bahanBakuUpdateDTO.getNamaBahan());
            bahanBaku.get().setQty(bahanBakuUpdateDTO.getQty());
            bahanBaku.get().setHargaBeli(bahanBakuUpdateDTO.getHargaBeli());
            bahanBaku.get().setTanggalUpdate(new Date());
            bahanBakuRepository.save(bahanBaku.get());

            HistoryCreateDto historyCreateDto =
                    HistoryCreateDto.builder()
                            .namaUser(nameUser)
                            .activity("Update Bahan Baku " + bahanBakuUpdateDTO.getNamaBahan())
                            .build();
            historyService.saveHistory(historyCreateDto);
        }
    }

    public void deleteBahanBaku(Long id){

        String nameUser = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            CustomUserPrincipal userDetail = (CustomUserPrincipal) auth.getPrincipal();
            nameUser = userDetail.getFirstName() + " " + userDetail.getLastName();
        }

        Optional<BahanBaku> bahanBaku =
                Optional.ofNullable(bahanBakuRepository.findById(id))
                        .orElseThrow(() -> new ResourceNotFoundException("Bahan Baku Cannot Found In Database"));
        if(bahanBaku.isPresent()){

            HistoryCreateDto historyCreateDto =
                    HistoryCreateDto.builder()
                            .namaUser(nameUser)
                            .activity("Delete Bahan Baku " + bahanBaku.get().getNamaBahan())
                            .build();
            historyService.saveHistory(historyCreateDto);

            bahanBakuRepository.delete(bahanBaku.get());


        }
    }

}
