package com.rp.courseutil;

import lombok.NoArgsConstructor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@NoArgsConstructor
public class DefaultSubscriber implements Subscriber<Object> {

    private String name;
    private Subscription subscription;

    public DefaultSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Object o) {

        System.out.println(name + "| Next received: " + o + " --- Thread: " + Thread.currentThread()
                .getName());
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(name + "| Error: " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println(name + "| Completed");
    }
}
