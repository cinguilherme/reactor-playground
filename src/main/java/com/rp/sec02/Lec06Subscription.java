package com.rp.sec02;

import com.rp.courseutil.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

public class Lec06Subscription {

    public static void main(String[] args) {

        AtomicReference<Subscription> ref = new AtomicReference<>();

        Subscriber<Integer> subscriber = Flux.range(0, 20)
                                             .log()
                                             .subscribeWith(new Subscriber<Integer>() {

                                                 @Override
                                                 public void onSubscribe(
                                                         Subscription subscription) {

                                                     System.out.println(
                                                             "received the subscription");
                                                     ref.set(subscription);
                                                 }

                                                 @Override
                                                 public void onNext(
                                                         Integer integer) {
                                                     System.out.println("onNext: " + integer);
                                                 }

                                                 @Override
                                                 public void onError(
                                                         Throwable throwable) {
                                                     System.out.println(
                                                             "OnError: " + throwable.getMessage());
                                                 }

                                                 @Override
                                                 public void onComplete() {
                                                     System.out.println("Completed");
                                                 }
                                             });

        Util.sleepSeconds(2);

        ref.get()
           .request(3);

        Util.sleepSeconds(2);
        ref.get()
           .request(3);

        ref.get()
           .cancel();

        Util.sleepSeconds(2);
    }

}
