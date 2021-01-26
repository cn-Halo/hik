package com.hik.util;

import com.hik.enums.CodeEnum;

/**
 * Created by yzm on 2017/8/25.
 */
public class EnumUtil {

    /**
     * 根据code得到enum
     *
     * @param code
     * @param cls
     * @param <T>
     * @return
     */
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> cls) {
        for (T each : cls.getEnumConstants()) {
            if (each.getCode().equals(code)) {
                return each;
            }
        }
        return null;
    }



}
