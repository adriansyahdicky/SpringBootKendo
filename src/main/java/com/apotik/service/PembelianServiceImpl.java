package com.apotik.service;

import com.apotik.dto.PembelianDTO;
import com.apotik.dto.ReturnRequestPembelian;
import com.apotik.entity.*;
import com.apotik.repository.ObatRepository;
import com.apotik.repository.PembelianDetailRepository;
import com.apotik.repository.PembelianRepository;
import com.apotik.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PembelianServiceImpl implements PembelianService{

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private PembelianRepository pembelianRepository;

    @Autowired
    private PembelianDetailRepository pembelianDetailRepository;

    @Autowired
    private ObatRepository obatRepository;

    @Override
    public void RequestBarang(PembelianDTO pembelianDTO) throws ParseException {
        Supplier supplier = supplierRepository.findById(pembelianDTO.getSupplierId()).get();

        Pembelian pembelian = new Pembelian();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        pembelian.setTanggal(timestamp);
        pembelian.setTotalPrice(pembelianDTO.getPrice());
        pembelian.setSupplier(supplier);
        pembelian.setStatus("Pending");
        pembelianRepository.save(pembelian);

        for(int i=0; i < pembelianDTO.getOrderDetailDTOS().size(); i++){
            PembelianDetail_PK pembelianDetail_pk = new PembelianDetail_PK();
            pembelianDetail_pk.setPembelianId(pembelian.getId());
            pembelianDetail_pk.setObatId(pembelianDTO.getOrderDetailDTOS().get(i).getObatId());

            Obat obat = obatRepository.findById(pembelianDTO.getOrderDetailDTOS().get(i).getObatId()).get();


            PembelianDetail pembelianDetail = new PembelianDetail();
            pembelianDetail.setPembelianDetail_pk(pembelianDetail_pk);
            pembelianDetail.setPembelian(pembelian);
            pembelianDetail.setObats(obat);
            pembelianDetail.setQty(pembelianDTO.getOrderDetailDTOS().get(i).getQty());
            pembelianDetail.setUnitPrice(pembelianDTO.getOrderDetailDTOS().get(i).getUnitPrice());

            pembelianDetailRepository.save(pembelianDetail);
        }
    }

    @Override
    public void Approved(Long id) throws ParseException {
        Pembelian pembelian = pembelianRepository.findById(id).get();
        pembelian.setStatus("Approved");
        pembelianRepository.save(pembelian);

        List<PembelianDetail> pembelianDetails = pembelianDetailRepository.findByPembelianId(id);
        for(int i=0; i < pembelianDetails.size(); i++){

            Obat obat = obatRepository.findById(pembelianDetails.get(i).getPembelianDetail_pk().getObatId()).get();
            int updatejumlahstok = pembelianDetails.get(i).getQty() + obat.getQty();
            obat.setQty(updatejumlahstok);
            obatRepository.save(obat);
        }
    }

    @Override
    public List<Pembelian> getPembelians() {
        return pembelianRepository.findAll();
    }

    @Override
    public ReturnRequestPembelian getRequestPembelian(Long id) {
        ReturnRequestPembelian rp = new ReturnRequestPembelian();
        Pembelian pembelian = pembelianRepository.findById(id).get();
        rp.setPembelian(pembelian);

        List<PembelianDetail> tampungData = new ArrayList<>();
        List<PembelianDetail> pembelianDetails = pembelianDetailRepository.findByPembelianId(id);
        for(int i=0; i<pembelianDetails.size(); i++){
            PembelianDetail pembelianDetail = new PembelianDetail();
            pembelianDetail.setQty(pembelianDetails.get(i).getQty());
            pembelianDetail.setUnitPrice(pembelianDetails.get(i).getUnitPrice());
            Obat obat = obatRepository.findById(pembelianDetails.get(i).getPembelianDetail_pk().getObatId()).get();
            pembelianDetail.setObats(obat);
            tampungData.add(pembelianDetail);
        }
        rp.setPembelianDetails(tampungData);

        return rp;
    }
}
