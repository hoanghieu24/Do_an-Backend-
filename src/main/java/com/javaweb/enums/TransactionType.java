package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum TransactionType {
    CSKH("Chăm Sóc Khách Hàng"),
    DDX("Dẫn đi Xem ");

    private final String type;

    private TransactionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Map<String,String> type(){
        Map<String,String> map = new LinkedHashMap<>();
        for(TransactionType item : TransactionType.values()) {
            map.put(item.toString(), item.getType());
        }
        return map;
    }
}
