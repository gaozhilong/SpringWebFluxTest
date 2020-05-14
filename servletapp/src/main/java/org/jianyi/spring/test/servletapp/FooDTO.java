package org.jianyi.spring.test.servletapp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;

@Data
@AllArgsConstructor
public class FooDTO {

    private Long key;

    private String att1;

    private String att2;

    public FooDTO() {
        //尽管java的文档没说这个东西是线程安全的，但是创造随机数本来就不要求完全线程安全，
        //所以没有换成math random
        Random random = new Random();
        key = random.nextLong();
        att1 = randomString(random);
        att2 = randomString(random);
    }

    public static FooDTO random() {
        return new FooDTO();
    }

    public static String randomString(Random random) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
