package com.apotik.dto.kategori;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class KategoriCreateDTO {

    //@NotBlank(message = "Name Kategori Tidak Boleh Kosong")
    @NotBlank(message = "Nama Kategori Tidak Boleh Kosong")
    private String nameKategori;

    //@NotBlank(message = "Satuan Belum Dipilih")
    @NotBlank(message = "Satuan Belum Dipilih")
    private String satuan;

}
