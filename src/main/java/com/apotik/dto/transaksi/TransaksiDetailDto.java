package com.apotik.dto.transaksi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransaksiDetailDto {
    private int qty;
    private Long menuId;
    private Long transaksiId;
}
