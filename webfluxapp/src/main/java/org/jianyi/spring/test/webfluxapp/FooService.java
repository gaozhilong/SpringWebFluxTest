package org.jianyi.spring.test.webfluxapp;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class FooService {

    private Random random = new Random();

    public long randomSeconds() {
        long leftLimit = 0L;
        long rightLimit = 5L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    public Mono<FooDTO> save(FooDTO foo) {
        try {
            TimeUnit.SECONDS.sleep(randomSeconds());
        } catch (InterruptedException e) {
            //测试使用未打印日志
            e.printStackTrace();
        }
        return Mono.justOrEmpty(FooDTO.random());
    }

    public Mono<FooDTO> getFoo(Long key) {
        try {
            TimeUnit.SECONDS.sleep(randomSeconds());
        } catch (InterruptedException e) {
            //测试使用未打印日志
            e.printStackTrace();
        }
        return Mono.justOrEmpty(FooDTO.random());
    }

    public Flux<FooDTO> getFoos() {
        try {
            TimeUnit.SECONDS.sleep(randomSeconds());
        } catch (InterruptedException e) {
            //测试使用未打印日志
            e.printStackTrace();
        }
        return Flux.fromStream(IntStream.range(1, random.nextInt(100) + 1).mapToObj(it -> {
            return FooDTO.random();
        }));
    }


}
