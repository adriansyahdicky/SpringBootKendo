package com.apotik.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchByDateDTO {

    private String tglMasuk;
    private String tglKeluar;

}
