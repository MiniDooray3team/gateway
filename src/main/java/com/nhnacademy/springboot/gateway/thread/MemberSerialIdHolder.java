package com.nhnacademy.springboot.gateway.thread;

public class MemberSerialIdHolder {

    private MemberSerialIdHolder() {
    }
    private static final ThreadLocal<Long> serialId = new ThreadLocal<>();

    public static Long getSerialId() {
        return serialId.get();
    }

    public static void setSerialId(Long value) {
        serialId.set(value);
    }

    public static void clear() {
        serialId.remove();
    }
}
