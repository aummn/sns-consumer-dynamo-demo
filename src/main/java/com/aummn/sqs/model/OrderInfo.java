/**
 * 
 */
package com.aummn.sqs.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "order")
public class OrderInfo implements Serializable {
    @DynamoDBHashKey
    private String orderNo;

    @DynamoDBAttribute
    private String item;

    @DynamoDBAttribute
    private int quantity;

    @DynamoDBAttribute
    private BigDecimal price;

    @DynamoDBAttribute
    private String shippingInfo;
}

