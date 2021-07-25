package com.apotik.dto.bahan_baku;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BahanBakuUpdateDTO {

    private Long id;

    private String namaBahan;

    private Integer qty;

    private Double hargaBeli;

}
