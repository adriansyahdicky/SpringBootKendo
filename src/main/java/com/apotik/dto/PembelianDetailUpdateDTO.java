package com.apotik.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PembelianDetailUpdateDTO {

    private Long id_obat;
    private Long id_pembelian;
    private int stok;
    private double unite_price;
    private double total_price;
    private String keterangan;

}
