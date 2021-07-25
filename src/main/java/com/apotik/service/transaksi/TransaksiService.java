package com.apotik.service.transaksi;

import com.apotik.dto.transaksi.TransaksiDetailDto;
import com.apotik.dto.transaksi.TransaksiDto;
import com.apotik.entity.Menu;
import com.apotik.entity.Transaksi;
import com.apotik.entity.TransaksiDetail;
import com.apotik.entity.TransaksiDetail_PK;
import com.apotik.repository.MenuRepository;
import com.apotik.repository.TransaksiDetailRepository;
import com.apotik.repository.TransaksiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class TransaksiService {

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private TransaksiDetailRepository transaksiDetailRepository;

    @Autowired
    private MenuRepository menuRepository;

    public void saveTransaksi(TransaksiDto transaksiDto){
        Transaksi transaksi =
                Transaksi
                .builder()
                .tanggalTransaksi(new Date())
                .catatan(transaksiDto.getCatatan())
                .tipePembayaran(transaksiDto.getTipePembayaran())
                .totalBayar(transaksiDto.getTotalBayar())
                .build();
        transaksiRepository.save(transaksi);

        transaksiDto.getTransaksiDetailDtos().parallelStream().forEach(transaksiDetailDto -> {

            TransaksiDetail_PK transaksiDetail_pk =
                    TransaksiDetail_PK.builder()
                    .transaksiId(transaksi.getId())
                    .menuId(transaksiDetailDto.getMenuId()).build();

            Menu menu = menuRepository.findById(transaksiDetail_pk.getMenuId()).get();

            TransaksiDetail transaksiDetail =
                    TransaksiDetail.builder()
                    .transaksiDetail_pk(transaksiDetail_pk)
                    .transaksi(transaksi)
                    .menu(menu)
                    .jumlah(transaksiDetailDto.getQty())
                    .build();

            transaksiDetailRepository.save(transaksiDetail);
        });


    }

}
