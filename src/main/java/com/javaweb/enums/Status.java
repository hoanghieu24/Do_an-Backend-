package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum Status {
    CHUA_XU_LY("Chưa xử lý"),
    DANG_XU_LY("Đang xử lý "),
    DA_XU_LY("Đã xử lý ");

    private final String statusName;

    Status(String statusName) {
        this.statusName = statusName;
    }
    public String getStatusName() {
        return statusName;
    }
    public static Map<String,String> type(){
        Map<String,String> map = new LinkedHashMap<>();
        for(Status item : Status.values()) {
            map.put(item.toString(), item.getStatusName());
        }
        return map;
    }
    public static String getStatusNames(String Code){
        for(Status item : Status.values()) {
            if(item.toString().equals(Code)) {
                return item.getStatusName();
            }
        }
        return null;
    }
    public static Status getStatusByName(String statusName) {
        for (Status status : Status.values()) {
            if (status.getStatusName().equals(statusName)) {
                return status;
            }
        }
        return null; // Trả về null nếu không tìm thấy trạng thái tương ứng
    }


}
