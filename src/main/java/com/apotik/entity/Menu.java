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
@Table(name = "menu")
@Builder
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_menu")
    private String namaMenu;

    @Column(name = "deskripsi_menu")
    private String deskripsiMenu;

    @Column(name = "harga")
    private Double harga;

}
