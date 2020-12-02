package com.apotik.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @NotNull(message = "Tanggal Tidak Boleh Kosong")
    private String tanggal;
    private double price;
    private Long userId;

}
