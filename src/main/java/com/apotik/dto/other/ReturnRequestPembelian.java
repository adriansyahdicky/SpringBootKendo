package com.apotik.dto.other;

import com.apotik.entity.Pembelian;
import com.apotik.entity.PembelianDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnRequestPembelian {

    private Pembelian pembelian;
    private List<PembelianDetail> pembelianDetails;

}
