package com.aummn.sqs.repository;

import com.aummn.sqs.model.OrderInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
public interface OrderInfoRepository extends CrudRepository<OrderInfo, String> {
}

