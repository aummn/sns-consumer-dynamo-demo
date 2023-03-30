package com.aummn.sqs.service;

import com.aummn.sqs.model.OrderInfo;
import com.aummn.sqs.repository.OrderInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderInfoService {

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    public OrderInfo createOrderInfo(OrderInfo orderInfo) {
        return orderInfoRepository.save(orderInfo);
    }

    public Optional<OrderInfo> getOrderInfo(String orderNo) {
        return orderInfoRepository.findById(orderNo);
    }

    public List<OrderInfo> getAllOrderInfos() {
        List<OrderInfo> orderInfos = new ArrayList<>();
        orderInfoRepository.findAll().forEach(orderInfos::add);
        return orderInfos;
    }

    public OrderInfo updateOrderInfo(String orderNo, OrderInfo orderInfo) {
        orderInfo.setOrderNo(orderNo);
        return orderInfoRepository.save(orderInfo);
    }

    public void deleteOrderInfo(String orderNo) {
        orderInfoRepository.deleteById(orderNo);
    }
}

