package com.apotik.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuCreateDto {

    @NotBlank(message = "Nama Menu Tidak Boleh Kosong")
    private String namaMenu;
    @NotBlank(message = "Deskripsi Menu Tidak Boleh Kosong")
    private String deskripsiMenu;
    @NotNull(message = "Harga Barang Tidak Boleh Kosong")
    private Double harga;

}
