package com.rp.sec05.assignment;

import lombok.NoArgsConstructor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class RevenueService implements Subscriber<Order> {

    private Subscription subscription;

    private Map<String, BigDecimal> dbRevenue = new HashMap<>();
    private BigDecimal rev = BigDecimal.ZERO;

    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("RevenueService is now subscribed, init state flow");
        this.subscription = subscription;
        this.subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Order item) {
        System.out.println("Next item: Revenue: " + item);
        this.rev = item.getTotalAmount()
                .add(rev);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Error: " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Completed processing flux of orders for Revenue, display final results");
        System.out.println("####### Total Revenue: " + this.rev);
    }
}
