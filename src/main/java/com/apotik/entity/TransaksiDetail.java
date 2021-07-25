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
@Table(name = "transaksi_detail")
@Builder
public class TransaksiDetail {

    @Column(name = "jumlah")
    private Integer jumlah;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Transaksi transaksi;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Menu menu;
    @EmbeddedId
    private TransaksiDetail_PK transaksiDetail_pk;

}
