package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUntil;
import org.springframework.stereotype.Component;


@Component
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> param, List<String> typeCode) {

        return BuildingSearchBuilder.builder()
                .name(MapUntil.getObject(param, "name", String.class))
                .district(MapUntil.getObject(param, "district", String.class))
                .street(MapUntil.getObject(param, "street", String.class))
                .ward(MapUntil.getObject(param, "ward", String.class))
                .typeCode(typeCode)
                .areaFrom(MapUntil.getObject(param, "areaFrom", Integer.class))
                .areaTo(MapUntil.getObject(param, "areaTo", Integer.class))
                .floorarea(MapUntil.getObject(param, "floorarea", String.class))
                .managername(MapUntil.getObject(param, "managername", String.class))
                .managerphone(MapUntil.getObject(param, "managerphone", String.class))
                .numberOfBasement(MapUntil.getObject(param, "numberofbasement", Integer.class))
                .rentPriceFrom(MapUntil.getObject(param, "rentPriceFrom", Long.class))
                .rentPriceTo(MapUntil.getObject(param, "rentPriceTo", Long.class))
                .staffId(MapUntil.getObject(param, "staffId", Integer.class))
                .direction(MapUntil.getObject(param, "direction", String.class))
                .servicefee(MapUntil.getObject(param, "servicefee", String.class))
                .deposit(MapUntil.getObject(param, "deposit", String.class))
                .renttime(MapUntil.getObject(param, "rentTime", String.class))
                .rentpricedescription(MapUntil.getObject(param, "rentPriceDescription", String.class))
                .note(MapUntil.getObject(param, "note", String.class))
                .build();
    }
}
