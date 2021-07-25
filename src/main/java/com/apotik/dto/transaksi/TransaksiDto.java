package com.apotik.dto.transaksi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransaksiDto {

    private Double totalBayar;

    private String tipePembayaran;

    private String catatan;

    private List<TransaksiDetailDto> transaksiDetailDtos;

}
