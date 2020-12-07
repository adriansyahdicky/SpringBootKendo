package com.apotik.service;

import com.apotik.dto.KasirOrderDTO;
import com.apotik.entity.*;
import com.apotik.repository.ObatRepository;
import com.apotik.repository.OrderDetailRepository;
import com.apotik.repository.OrdersRepository;
import com.apotik.repository.UserRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class KasirServiceImpl implements KasirService{

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserRepositroy userRepositroy;

    @Autowired
    private ObatRepository obatRepository;

    @Override
    public void SaveKasir(KasirOrderDTO kasirOrderDTO) throws ParseException {
        User user = userRepositroy.findById(kasirOrderDTO.getUserId()).get();

        Orders orders = new Orders();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        orders.setTanggal(timestamp);
        orders.setTotalPrice(kasirOrderDTO.getPrice());
        orders.setUser(user);
        ordersRepository.save(orders);

        for(int i=0; i < kasirOrderDTO.getOrderDetailDTOS().size(); i++){
            OrderDetail_PK orderDetail_pk = new OrderDetail_PK();
            orderDetail_pk.setOrderId(orders.getId());
            orderDetail_pk.setObatId(kasirOrderDTO.getOrderDetailDTOS().get(i).getObatId());

            //upate jumlah stok obat
            Obat obat = obatRepository.findById(kasirOrderDTO.getOrderDetailDTOS().get(i).getObatId()).get();
            int qty = obat.getQty() - kasirOrderDTO.getOrderDetailDTOS().get(i).getQty();
            obat.setQty(qty);
            obatRepository.save(obat);

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderDetail_pk(orderDetail_pk);
            orderDetail.setOrders(orders);
            orderDetail.setObats(obat);
            orderDetail.setQty(kasirOrderDTO.getOrderDetailDTOS().get(i).getQty());
            orderDetail.setUnitPrice(kasirOrderDTO.getOrderDetailDTOS().get(i).getUnitPrice());

            orderDetailRepository.save(orderDetail);
        }
    }
}
