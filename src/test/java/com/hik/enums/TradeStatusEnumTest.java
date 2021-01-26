package com.hik.enums;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by yzm on 2017/9/7.
 */
@RunWith(SpringRunner.class)
public class TradeStatusEnumTest {

    @FunctionalInterface
    public interface Enums {
        TradeStatusEnum getByName(String name);
    }

    @Test
    public void test1() {
        Enums enums = TradeStatusEnum::valueOf;
        TradeStatusEnum statusEnum = enums.getByName("WAIT_BUYER_PAY");
        System.out.println(statusEnum.getCode() + "||" + statusEnum.name() + "||" + statusEnum.getNote());
    }

}