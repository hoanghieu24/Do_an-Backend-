package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum districtCode {
    QUAN_0 (" "),
    QUAN_1 ("Quận 1"),
    QUAN_2 ("Quận 2"),
    QUAN_3 ("Quận 3"),
    QUAN_4 ("Quận 4"),
    QUAN_5 ("Quận 5"),
    QUAN_6 ("Quận 6"),
    QUAN_7 ("Quận 7"),
    QUAN_DONG_DA ("Quận Đống Da");
    private final String districtName;
    districtCode(String districtName) {
        this.districtName = districtName;
    }
    public String getDistrictName() {
        return districtName;
    }
    public static Map<String,String> type(){
        Map<String,String> typeMap = new LinkedHashMap<>();
        for(districtCode item : districtCode.values()){
            typeMap.put(item.toString(), item.getDistrictName());
        }
        return typeMap;
    }

}
