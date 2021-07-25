package com.apotik.dto.report;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BarangDetailDto {
    private String nameBarang;
    private Integer qty;
    private Double hargaJual;
    private Double unitPrice;
}
