package com.apotik.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObatCreateDTO {

    @NotBlank(message = "Nama Obat Tidak Boleh Kosong")
    private String nameObat;

    @NotNull(message = "Harga Jual Tidak Boleh Kosong")
    private Double hargaJual;

    @NotNull(message = "Harga Supplier Tidak Boleh Kosong")
    private Double hargaSupplier;

    @JsonProperty
    @Min(message = "Jumlah Barang Minimum Harus 0", value = 0)
    @NotNull(message = "Qty Tidak Boleh Kosong")
    private int qty;

    @NotNull(message = "Kategori Belum Dipilih")
    private Long idKategori;

}
