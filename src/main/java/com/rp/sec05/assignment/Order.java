package com.rp.sec05.assignment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Data
@EqualsAndHashCode
public class Order {

    private UUID id;
    private String details;
    private BigDecimal totalAmount;

}
