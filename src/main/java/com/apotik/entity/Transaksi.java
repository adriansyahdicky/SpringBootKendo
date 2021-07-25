package com.apotik.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaksi")
@Builder
public class Transaksi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tanggal_transaksi")
    private Date tanggalTransaksi;

    @Column(name = "total_bayar")
    private Double totalBayar;

    @Column(name = "tipe_pembayaran")
    private String tipePembayaran;

    @Column(name = "catatan")
    private String catatan;

}
