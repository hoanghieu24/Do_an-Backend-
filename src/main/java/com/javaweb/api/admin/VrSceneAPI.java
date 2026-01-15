package com.javaweb.api.admin;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.VrSceneEntity;
import com.javaweb.model.dto.VrSceneDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.VrSceneRepository;
import com.javaweb.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vr")
public class VrSceneAPI {

    @Autowired
    private VrSceneRepository vrSceneRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private BuildingRepository buildingRepository;

    @GetMapping("/{buildingId}")
    public List<VrSceneDTO> getScenesByBuilding(@PathVariable Long buildingId) {
        return vrSceneRepository.findByBuildingId(buildingId)
                .stream()
                .map(v -> new VrSceneDTO(
                        v.getId(),
                        v.getName(),
                        v.getImageUrl(),
                        v.getAth(),
                        v.getAtv(),
                        v.getTargetScene(),
                        v.getBuilding().getName()
                ))
                .collect(Collectors.toList());
    }




    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addScene(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("buildingId") Long buildingId
    ) {
        try {
            BuildingEntity building = buildingRepository.findById(buildingId)
                    .orElseThrow(() -> new RuntimeException("Building not found"));

            String filePath = storageService.save(file);

            VrSceneEntity scene = new VrSceneEntity();
            scene.setName(name);
            scene.setBuilding(building);
            scene.setImageUrl(filePath);

            VrSceneEntity saved = vrSceneRepository.save(scene);

            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScene(@PathVariable Long id) {
        try {
            VrSceneEntity scene = vrSceneRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("VR Scene not found"));

            // Xóa file ảnh vật lý (nếu có lưu đường dẫn)
            if (scene.getImageUrl() != null) {
                storageService.delete(scene.getImageUrl());
            }

            // Xóa record trong DB
            vrSceneRepository.delete(scene);

            return ResponseEntity.ok(Map.of("message", "Xóa VR scene thành công"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }



}
