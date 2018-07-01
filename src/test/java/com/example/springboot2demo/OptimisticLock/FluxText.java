package com.example.springboot2demo.OptimisticLock;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

/**
 * @author Lucifer
 * @do 对Flux的原理进行测试
 * @date 2018/07/01 15:51
 */

public class FluxText {

    @Test
    public void test(){
        //建立数组
        String[] strs = {"1", "2", "3"};
        Subscriber subscriber = new Subscriber() {
            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription s) {
                this.subscription = s;
                subscription.request(1);
            }

            @Override
            public void onNext(Object o) {
                System.out.println("接收到的数据 ：" + o);
                subscription.request(1);
            }

            @Override
            public void onError(Throwable t) {
                subscription.cancel();
            }

            @Override
            public void onComplete() {

            }
        };
        Flux.fromArray(strs).map(s -> Integer.parseInt(s)).subscribe(subscriber);
    }

}
