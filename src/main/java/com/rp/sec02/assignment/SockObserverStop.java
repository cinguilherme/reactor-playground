package com.rp.sec02.assignment;

import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;


public class SockObserverStop {

    public static void main(String[] args) {
        

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux<Integer> priceUpdate = Publisher.getPriceUpdate();

        AtomicReference<Subscription> ref = new AtomicReference<>();
        priceUpdate.subscribeWith(Subcriber.subscriberImpl(ref, countDownLatch));

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
