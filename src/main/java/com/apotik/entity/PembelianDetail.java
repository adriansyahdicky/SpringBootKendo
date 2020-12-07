package com.apotik.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Pembelian_Detail")
public class PembelianDetail {

    @Column(name = "qty")
    private int qty;
    @Column(name = "unit_price")
    private double unitPrice;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Obat obats;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Pembelian pembelian;
    @EmbeddedId
    private PembelianDetail_PK pembelianDetail_pk;

}
