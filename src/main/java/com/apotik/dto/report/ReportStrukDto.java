package com.apotik.dto.report;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ReportStrukDto {
    private Long orderId;
    private String kasir;
    private LocalDateTime tanggal;
    private String metodePembayaran;
    private Double totalPrice;
    private List<BarangDetailDto> barangDetailDtos;
}

