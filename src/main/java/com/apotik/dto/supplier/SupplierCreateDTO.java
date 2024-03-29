package com.apotik.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierCreateDTO {

    @NotBlank(message = "Nama Supplier Tidak Boleh Kosong")
    private String nameSupplier;

    @NotBlank(message = "Telepon tidak boleh kosong")
    private String telepon;

    @NotBlank(message = "Alamat tidak boleh kosong")
    private String alamat;

}
