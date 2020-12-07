package com.apotik.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KasirOrderDTO {

    //private OrderDTO orderDTO;
    private String tanggal;
    private double price;
    private Long userId;
    private List<OrderDetailDTO> orderDetailDTOS;

}
