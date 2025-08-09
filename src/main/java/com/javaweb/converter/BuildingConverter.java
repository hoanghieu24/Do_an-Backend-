package com.javaweb.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentareaEntity;
import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingResponse;
import com.javaweb.repository.RentareaRepository;
import com.javaweb.utils.BuildingType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // để cho biết rằng đây là một class bean
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private RentareaRepository rentareaRepository;

    public BuildingResponse convertoResponse(BuildingEntity be) {
        BuildingResponse buildingResponse = modelMapper.map(be, BuildingResponse.class);

        if (be.getDistrictId() != null) {
            String districtName = districtCode.valueOf(be.getDistrictId()).getDistrictName();
            if(districtCode.QUAN_0 == districtCode.valueOf(be.getDistrictId())){
                buildingResponse.setAddress(be.getStreet() + " , " + be.getWard() );
            }else{
                buildingResponse.setAddress(be.getStreet() + " , " + be.getWard() + " , " + districtName);
            }

        }

        String rental = be.getRentareaEntity_List().stream()
                .map(i -> i.getValue().toString())
                .collect(Collectors.joining(","));
        buildingResponse.setRental_area(rental);
        buildingResponse.setRentPrice(be.getRentPrice());

        return buildingResponse;
    }
    public BuildingDTO convertToDTO(BuildingEntity buildingEntity) {
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);


        if (buildingEntity.getRentareaEntity_List() != null) {
            List<String> rentAreas = new ArrayList<>();
            for (RentareaEntity rentArea : buildingEntity.getRentareaEntity_List()) {
                rentAreas.add(rentArea.getValue().toString());
            }
            buildingDTO.setRentareaEntity_List(String.join(",", rentAreas));
        }


        if (buildingEntity.getType() != null) {

            String cleanTypeString = buildingEntity.getType().replaceAll("[\\[\\]]", "");

            List<buildingType> buildingTypes = Arrays.asList(cleanTypeString.split(","))
                    .stream()
                    .map(String::trim)
                    .map(buildingType::valueOf)
                    .collect(Collectors.toList());
            buildingDTO.setType(buildingTypes);
        }

        return buildingDTO;

    }


    public List<RentareaEntity> convertPostAndEditRentAreas(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        List<RentareaEntity> rentareaEntities = new ArrayList<>();
        String rentAreasInput = buildingDTO.getRentareaEntity_List().toString();
        if (rentAreasInput != null && !rentAreasInput.trim().isEmpty()) {
            List<String> rentAreas = Arrays.asList(rentAreasInput.split(","));
            for (String value : rentAreas) {
                RentareaEntity rentareaEntity = new RentareaEntity();
                rentareaEntity.setBuilding(buildingEntity);
                rentareaEntity.setValue(Integer.valueOf(value.trim()));
                rentareaEntities.add(rentareaEntity);
                rentareaRepository.save(rentareaEntity);
            }
        }
        return rentareaEntities;
    }






}
