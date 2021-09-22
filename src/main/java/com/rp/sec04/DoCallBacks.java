package com.rp.sec04;

import com.rp.courseutil.DefaultSubscriber;
import reactor.core.publisher.Flux;

public class DoCallBacks {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .handle((integer, synchronousSink) -> {
                    synchronousSink.next(integer);
                })
                .doOnComplete(() -> System.out.println("OnComplete DO"))
                .doOnEach(objectSignal -> {
                    System.out.println("do On Each" + objectSignal);
                })
                .doOnNext(o -> {
                    System.out.println("do On next" + o);
                })
                .doOnSubscribe(subscription -> {
                    System.out.println("on subscribe " + subscription.toString());
                })
                .doOnRequest(value -> {
                    System.out.println("on request " + value);
                })
                .doOnError(throwable -> {
                    System.out.println("Error On: " + throwable.getMessage());
                })
                .doOnTerminate(() -> {
                    System.out.println("Terminantion");
                })
                .doOnCancel(() -> {
                    System.out.println("Cancelad");
                })
                .doFinally(signalType -> {
                    System.out.println("Signal " + signalType);
                })
                .doOnDiscard(Object.class, o -> {
                    System.out.println("discarted " + o);
                })
                .doFirst(() -> System.out.println("Do first 1"))
                .doFirst(() -> System.out.println("Do first 2"))
                .doFirst(() -> System.out.println("Do first 3"))
                .doFirst(() -> System.out.println("Do first 4"))
                .subscribe(new DefaultSubscriber("whatever"));

    }
}
