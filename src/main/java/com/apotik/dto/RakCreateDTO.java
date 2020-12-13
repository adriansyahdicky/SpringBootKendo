package com.apotik.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RakCreateDTO {

    @NotBlank(message = "Nama Rak Tidak Boleh Kosong")
    private String nameRak;

}
