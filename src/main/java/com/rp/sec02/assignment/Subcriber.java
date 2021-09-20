package com.rp.sec02.assignment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class Subcriber {

    public static Subscriber<Integer> subscriberImpl(
            AtomicReference<Subscription> ref, CountDownLatch latch) {

        return new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("subscribed");
                subscription.request(Long.MAX_VALUE);
                ref.set(subscription);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("next value update => " + integer);
                if (integer > 150 || integer < 70) {
                    System.out.println("canceling subscription");
                    latch.countDown();
                    ref.get()
                       .cancel();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                latch.countDown();
            }

            @Override
            public void onComplete() {
                latch.countDown();
            }
        };

    }

}
