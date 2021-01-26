package com.hik.util;

import java.util.Random;
import java.util.stream.Stream;

/**
 * Created by yzm on 2017/9/13.
 * 获取验证码
 */
public class CodeUtil {

    public synchronized static String getCode() {
        StringBuffer sb = new StringBuffer();
        Stream.generate(() -> new Random().nextInt(10)).limit(4).forEach(o -> sb.append(o));
        return sb.toString();
    }

}
