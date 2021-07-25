package com.apotik.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "report_struk")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportStruk {

    private Long id;

    private LocalDateTime tanggal;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "metode_pembayaran")
    private String metodePembayaran;

    private Integer qty;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "full_name")
    private String fullName;

    @Id
    @Column(name = "name_obat")
    private String nameObat;

    @Column(name = "harga_jual")
    private Double hargaJual;

}
