package com.rp.sec05.assignment;

import lombok.NoArgsConstructor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class InventoryService implements Subscriber<Order> {

    private Subscription subscription;
    private Map<String, Long> invetoryCount = new HashMap<>();

    {
        invetoryCount.put("small", 100L);
        invetoryCount.put("med", 100L);
        invetoryCount.put("big", 100L);
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("InventoryService is now subscribed, init state flow");
        this.subscription = subscription;
        this.subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Order item) {
        System.out.println("Next item: Inventory: " + item);
        String level = levelPerPrice(item);
        if (invetoryCount.containsKey(level)) {
            long prev = invetoryCount.get(level);
            invetoryCount.put(level, prev - 1);
        } else {
            invetoryCount.put(level, 0L);
        }
    }

    private String levelPerPrice(Order order) {
        BigDecimal totalAmount = order.getTotalAmount();
        if (totalAmount.compareTo(BigDecimal.valueOf(100)) < 0) {
            return "small";
        }
        if (totalAmount.compareTo(BigDecimal.valueOf(1000)) < 0) {
            return "med";
        }
        return "big";
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Error: " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println(
                "Completed processing flux of orders for Inventory, display final results");
        System.out.println("Inventory counts: " + invetoryCount);
    }
}
