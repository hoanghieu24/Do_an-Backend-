package com.javaweb.api.admin;


import com.javaweb.model.dto.AssigmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/buildings")
public class BuildingAPI {

    @Autowired
    private BuildingService buildingService;
    @PostMapping
    private ResponseEntity<?> createBuilding(@RequestBody BuildingDTO buildingDTO , BindingResult bindingResult){

        try {
            if(bindingResult.hasErrors()){
                List<String > errors = bindingResult.getFieldErrors().stream()
                                                    .map(FieldError::getDefaultMessage).collect(Collectors.toList());
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setMessage("Faild");
                responseDTO.setDetail(errors);
                return ResponseEntity.badRequest().body(responseDTO);
            }
            buildingService.postAll(buildingDTO);
        }
        catch (Exception ex ){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return ResponseEntity.ok("");
    }
    @PutMapping
    private void UpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        buildingService.putAll(buildingDTO);
    }

    @DeleteMapping("/delete/{ids}")
    private void deleteBuilding(@PathVariable List<Long> ids){
        buildingService.deleteAll(ids);
    }

    @GetMapping("/staffs/{ids}")
    private Object loadStaff(@PathVariable Long ids){
        return buildingService.loadStaff(ids);
    }

    @PutMapping("/staffs")
    private Object updateStaff(@RequestBody AssigmentBuildingDTO assigmentBuildingDTO){

        return buildingService.saveStaff(assigmentBuildingDTO);
    }

}
