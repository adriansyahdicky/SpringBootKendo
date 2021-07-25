package com.apotik.service.report;

import com.apotik.dto.report.BarangDetailDto;
import com.apotik.dto.report.ReportStrukDto;
import com.apotik.entity.ReportStruk;
import com.apotik.repository.ReportStrukRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReportStrukServices {

    @Autowired
    private ReportStrukRepository reportStrukRepository;

    public ReportStrukDto cetakReportStruk(Long id){
        Optional<ReportStrukDto> getResponseProduct =
                Optional.ofNullable(reportStrukRepository.findByOrderId(id)
                        .parallelStream().findFirst().map(this::setFirstStruk)).
                        orElse(Optional.of(new ReportStrukDto()));
        return getResponseProduct.isPresent() ? getResponseProduct.get() :
                ReportStrukDto.builder().build();
    }

    private ReportStrukDto setFirstStruk(ReportStruk reportStruk){

        return ReportStrukDto.builder()
                .orderId(reportStruk.getId())
                .kasir(reportStruk.getFullName())
                .metodePembayaran(reportStruk.getMetodePembayaran())
                .tanggal(reportStruk.getTanggal())
                .totalPrice(reportStruk.getTotalPrice())
                .barangDetailDtos(setListDetail(reportStruk.getId()))
                .build();
    }

    private  List<BarangDetailDto> setListDetail(Long id){
       return reportStrukRepository.findByOrderId(id)
                .parallelStream()
                .map(ReportStrukServices::setBarangDetail)
               .collect(Collectors.toList());
    }

    private static BarangDetailDto setBarangDetail(ReportStruk reportStruk){
        return BarangDetailDto.builder()
                .nameBarang(reportStruk.getNameObat())
                .hargaJual(reportStruk.getHargaJual())
                .qty(reportStruk.getQty())
                .unitPrice(reportStruk.getUnitPrice()).build();
    }
}
