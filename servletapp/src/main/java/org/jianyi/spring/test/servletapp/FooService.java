package org.jianyi.spring.test.servletapp;

import org.springframework.stereotype.Component;

import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class FooService {

    private Random random = new Random();

    public long randomSeconds() {
        long leftLimit = 0L;
        long rightLimit = 5L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    public FooDTO save(FooDTO foo) {
        try {
            TimeUnit.SECONDS.sleep(randomSeconds());
        } catch (InterruptedException e) {
            //测试使用未打印日志
            e.printStackTrace();
        }
        return FooDTO.random();
    }

    public FooDTO getFoo(Long key) {
        try {
            TimeUnit.SECONDS.sleep(randomSeconds());
        } catch (InterruptedException e) {
            //测试使用未打印日志
            e.printStackTrace();
        }
        return FooDTO.random();
    }

    public List<FooDTO> getFoos() {
        try {
            TimeUnit.SECONDS.sleep(randomSeconds());
        } catch (InterruptedException e) {
            //测试使用未打印日志
            e.printStackTrace();
        }
        return IntStream.range(1, random.nextInt(100) + 1).mapToObj(it -> {
            return FooDTO.random();
        }).collect(Collectors.toList());
    }


}
