package com.apotik.dto.pembelian;

import com.apotik.dto.orders.OrderDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PembelianDTO {

    private String tanggal;
    private double price;
    private Long supplierId;
    private List<OrderDetailDTO> orderDetailDTOS;

}
