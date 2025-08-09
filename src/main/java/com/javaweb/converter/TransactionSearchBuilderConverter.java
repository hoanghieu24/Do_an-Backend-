package com.javaweb.converter;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.builder.TransationSearchBuilder;
import com.javaweb.utils.MapUntil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TransactionSearchBuilderConverter {
    public TransationSearchBuilder toTransationSearchBuilder(Map<String, Object> param, List<String> code) {

        TransationSearchBuilder transationSearchBuilder = new TransationSearchBuilder.Builder()
                .setCreateDate(MapUntil.getObject(param, "createddate", java.util.Date.class))
                .setModifyDate(MapUntil.getObject(param, "modifieddate", java.util.Date.class))
                .setModifiedBy(MapUntil.getObject(param, "modifiedby", java.util.Date.class))
                .setModifiedDate(MapUntil.getObject(param, "modifieddate", java.util.Date.class))
                .build();


        return transationSearchBuilder;
    }
}
