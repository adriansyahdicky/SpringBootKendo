package com.apotik.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "obat")
@Builder
public class Obat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_obat")
    private String nameObat;

    @Column(name = "qty")
    private int qty;

    @Column(name = "harga_jual")
    private Double hargaJual;

    @Column(name = "harga_supplier")
    private Double hargaSupplier;

    @ManyToOne
    @JoinColumn(name = "kategori_id", nullable = false)
    private Kategori kategori;

    @ManyToOne
    @JoinColumn(name = "rak_id", nullable = false)
    private Rak rak;

}
