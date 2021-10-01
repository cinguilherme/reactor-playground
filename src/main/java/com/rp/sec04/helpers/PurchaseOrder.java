package com.rp.sec04.helpers;

import com.rp.courseutil.Util;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PurchaseOrder {

    private String item;
    private double price;
    private int userId;
    private String category;

    public PurchaseOrder(int userId) {
        this.userId = userId;

        this.item = Util.faker()
                .commerce()
                .productName();

        this.price = Double.parseDouble(Util.faker()
                                                .commerce()
                                                .price(10.00, 599.99));

        this.category = Util.faker()
                .number()
                .numberBetween(1, 10) < 5 ? "kids" : "automotive";
    }
}
