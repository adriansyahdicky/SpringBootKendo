package com.apotik.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Order_Detail")
public class OrderDetail {

    @Column(name = "qty")
    private int qty;
    @Column(name = "unit_price")
    private double unitPrice;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Obat obats;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Orders orders;
    @EmbeddedId
    private OrderDetail_PK orderDetail_pk;

}
