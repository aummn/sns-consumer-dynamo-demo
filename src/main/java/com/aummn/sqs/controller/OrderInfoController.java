package com.aummn.sqs.controller;

import com.aummn.sqs.model.OrderInfo;
import com.aummn.sqs.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderinfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @PostMapping
    public ResponseEntity<OrderInfo> createOrderInfo(@RequestBody OrderInfo orderInfo) {
        return new ResponseEntity<>(orderInfoService.createOrderInfo(orderInfo), HttpStatus.CREATED);
    }

    @GetMapping("/{orderNo}")
    public ResponseEntity<OrderInfo> getOrderInfo(@PathVariable String orderNo) {
        return orderInfoService.getOrderInfo(orderNo)
                .map(orderInfo -> new ResponseEntity<>(orderInfo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<OrderInfo>> getAllOrderInfos() {
        return new ResponseEntity<>(orderInfoService.getAllOrderInfos(), HttpStatus.OK);
    }

    @PutMapping("/{orderNo}")
    public ResponseEntity<OrderInfo> updateOrderInfo(@PathVariable String orderNo, @RequestBody OrderInfo orderInfo) {
        return new ResponseEntity<>(orderInfoService.updateOrderInfo(orderNo, orderInfo), HttpStatus.OK);
    }

    @DeleteMapping("/{orderNo}")
    public ResponseEntity<Void> deleteOrderInfo(@PathVariable String orderNo) {
        orderInfoService.deleteOrderInfo(orderNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

