
package com.javaweb.service;

import com.javaweb.model.dto.AssigmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.BuildingResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BuildingService {
    List<BuildingResponse> findAll(Map<String, Object> param, List<String> typeCode);
    void postAll(BuildingDTO buildingDTO);
    void putAll(BuildingDTO buildingDTO);
    void deleteAll(List<Long> ids);
    BuildingDTO getBuildingById(Long id);
    Object loadStaff(Long ids);
    Object loadProduct(Long ids);
    Object saveStaff(AssigmentBuildingDTO assigmentBuildingDTO);
    int countTotalItems();
    List<BuildingDTO> getBuilding(String searchValue, Pageable pageable);

}