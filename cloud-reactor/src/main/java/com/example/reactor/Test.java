package com.example.reactor;

import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Test {
    public static void main(String[] args) {

//        flux();

        sink();
    }

    private static void flux() {
        Flux.just("hello", "world")
               .map(s -> s.replaceAll(".", "*"))
               .subscribe(System.out::println);
    }

    private static void sink() {
        final Sinks.Many<String> objectMany = Sinks.many().multicast().directBestEffort();
        final Flux<String> stringFlux = objectMany.asFlux();
        stringFlux.subscribe(System.out::println);
        stringFlux.subscribe(new CoreSubscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(3);
            }

            @Override
            public void onNext(String o) {
                System.out.println(o + "limit 3 ");
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
        objectMany.tryEmitNext("hzs");
        objectMany.tryEmitNext("hzs1");
        objectMany.tryEmitNext("hzs2");
        objectMany.tryEmitNext("hzs3");
//        objectMany.tryEmitComplete();
    }
}
