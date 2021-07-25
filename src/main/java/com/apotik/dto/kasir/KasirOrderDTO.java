package com.apotik.dto.kasir;

import com.apotik.dto.orders.OrderDetailDTO;
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
    private String metodePembayaran;
    private List<OrderDetailDTO> orderDetailDTOS;

}
