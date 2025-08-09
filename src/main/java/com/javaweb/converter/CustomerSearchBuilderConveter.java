package com.javaweb.converter;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.enums.Status;
import com.javaweb.utils.MapUntil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public class CustomerSearchBuilderConveter {
    public CustomerSearchBuilder toCustomerSearchBuilder(Map<String, Object> param, String status) {

        CustomerSearchBuilder customerSearchBuilder = new CustomerSearchBuilder.Builder()
                .setFullName(MapUntil.getObject(param, "fullname", String.class))
                .setEmail(MapUntil.getObject(param, "email", String.class))
                .setNote(MapUntil.getObject(param, "note", String.class))
                .setPhone(MapUntil.getObject(param, "phone", String.class))
                .setStatus((MapUntil.getObject(param, "status", String.class)))
                .setStaffId(MapUntil.getObject(param, "staffId", Integer.class))
                .setCreatedBy(MapUntil.getObject(param, "createdBy", String.class))
                .build();


        return customerSearchBuilder;
    }
}
