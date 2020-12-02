package com.apotik.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {

    private double unitPrice;
    private int qty;
    private Long obatId;
    private Long orderId;

}
