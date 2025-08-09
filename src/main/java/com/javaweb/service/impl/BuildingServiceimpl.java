package com.javaweb.service.impl;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.constant.SystemConstant;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.entity.*;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.AssigmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDto;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentareaRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.utils.DistrictCode;
import com.javaweb.utils.UploadFileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.tomcat.util.codec.binary.Base64;

@Transactional
@Service
public class BuildingServiceimpl implements BuildingService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private BuildingSearchBuilderConverter builderConverter;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RentareaRepository rentareaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UploadFileUtils uploadFileUtils;
    @Autowired
    private TransactionRepository transactionRepository;

    // tim
    @Override
    public List<BuildingResponse> findAll(Map<String, Object> param, List<String> typeCode) {
        // ✅ Xử lý district từ "QUAN_1" → "Quận 1"
        String districtKey = (String) param.get("district");
        if (districtKey != null && !districtKey.isEmpty()) {
            try {
                districtCode districtEnum = districtCode.valueOf(districtKey);
                String districtValue = districtEnum.getDistrictName(); // Ví dụ: "Quận 1"
                param.put("district", districtValue);
            } catch (IllegalArgumentException e) {
                param.remove("district"); // Tránh crash nếu key không đúng
            }
            System.out.println("🎯 PARAM DISTRICT: " + param.get("district"));
//            System.out.println("🎯 Builder: " + buildingSearchBuilder);

        }

        BuildingSearchBuilder buildingSearchBuilder = builderConverter.toBuildingSearchBuilder(param, typeCode);
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder);

        List<BuildingResponse> buildingResponses = new ArrayList<>();
        for (BuildingEntity be : buildingEntities) {
            buildingResponses.add(buildingConverter.convertoResponse(be));
        }
        return buildingResponses;
    }


    // thêm toà nhà
    @Override
    public void postAll(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
            districtCode districtCodes = districtCode.valueOf(String.valueOf(buildingDTO.getDistrict()));
            buildingEntity.setDistrictId(String.valueOf(districtCodes));

        saveThumbnail(buildingDTO, buildingEntity);
        buildingRepository.save(buildingEntity);
        if (buildingDTO.getRentareaEntity_List() != null && !buildingDTO.getRentareaEntity_List().isEmpty()) {
            buildingConverter.convertPostAndEditRentAreas(buildingDTO, buildingEntity);
        }

    }

    // sửa toà nhà
    @Override
    public void putAll(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingDTO.getId()).orElse(null);
        if (buildingEntity != null) {
            buildingEntity.getRentareaEntity_List().clear();
            List<RentareaEntity> rentareaEntities = buildingConverter.convertPostAndEditRentAreas(buildingDTO, buildingEntity);
            buildingEntity.getRentareaEntity_List().addAll(rentareaEntities);
            modelMapper.map(buildingDTO, buildingEntity);
            DistrictCode district = DistrictCode.valueOf(String.valueOf(buildingDTO.getDistrict()));
            buildingEntity.setDistrictId(district.name());

            System.out.println("gia thue " + buildingEntity.getRentPrice());
            BuildingEntity buildingEntityOld = buildingRepository.findById(buildingEntity.getId()).get();
            if(buildingEntityOld.getImage() != null && !buildingEntityOld.getImage().isEmpty()) {

                buildingEntity.setImage(buildingEntityOld.getImage());
            }
            saveThumbnail(buildingDTO, buildingEntity);
            buildingRepository.save(buildingEntity);
        } else {
            System.out.println("BuildingEntity không tồn tại với ID: " + buildingDTO.getId());
        }
    }





    // xoá toà nhà
    @Override
    public void deleteAll(List<Long> ids) {
        try {
            List<BuildingEntity> buildingEntities = buildingRepository.findByIdIn(ids);
            if (transactionRepository != null) {
                rentareaRepository.deleteAllByBuildingIn(buildingEntities);
            }
            buildingRepository.deleteAll(buildingEntities);
        } catch (Exception e) {
            throw new RuntimeException("Xoá Lỗi rồi", e);
        }
    }




// giao
    @Override
    public Object loadStaff(Long buildingId) {
        List<UserEntity> userEntities = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        Optional<BuildingEntity> buildingEntityOpt = buildingRepository.findById(buildingId);
        if (!buildingEntityOpt.isPresent()) {
            throw new RuntimeException("Toà nhà không tồn tại !!");
        }
        BuildingEntity buildingEntity = buildingEntityOpt.get();
        List<StaffResponseDto> staffResponseDtos = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            StaffResponseDto staffResponseDto = new StaffResponseDto();
            staffResponseDto.setStaffId(userEntity.getId());
            staffResponseDto.setUserName(userEntity.getUserName());
            boolean isAssigned = buildingEntity.getUsers().stream()
                    .anyMatch(assignedUser -> assignedUser.getId().equals(userEntity.getId()));
            staffResponseDto.setChecked(isAssigned ? "checked" : "unchecked");
            staffResponseDtos.add(staffResponseDto);
        }
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(staffResponseDtos);
        responseDTO.setMessage("OKEEE");

        return responseDTO;
    }

    @Override
    public Object loadProduct(Long buildingId) {
        List<UserEntity> userEntities = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        Optional<BuildingEntity> buildingEntityOpt = buildingRepository.findById(buildingId);
        if (!buildingEntityOpt.isPresent()) {
            throw new RuntimeException("Toà nhà không tồn tại !!");
        }
        BuildingEntity buildingEntity = buildingEntityOpt.get();
        List<StaffResponseDto> staffResponseDtos = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            StaffResponseDto staffResponseDto = new StaffResponseDto();
            staffResponseDto.setStaffId(userEntity.getId());
            staffResponseDto.setUserName(userEntity.getUserName());
            boolean isAssigned = buildingEntity.getUsers().stream()
                    .anyMatch(assignedUser -> assignedUser.getId().equals(userEntity.getId()));
            staffResponseDto.setChecked(isAssigned ? "checked" : "unchecked");
            staffResponseDtos.add(staffResponseDto);
        }
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(staffResponseDtos);
        responseDTO.setMessage("OKEEE");

        return responseDTO;
    }






    @Override
    public Object saveStaff(AssigmentBuildingDTO assigmentBuildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(assigmentBuildingDTO.getBuildingId())
                .orElseThrow(() -> new RuntimeException("Toà nhà không tồn tại !!"));
        buildingEntity.getUsers().clear();
        buildingRepository.save(buildingEntity);
        for (Long staffId : assigmentBuildingDTO.getStaffs()) {
            UserEntity userEntity = userRepository.findById(staffId)
                    .orElseThrow(() -> new RuntimeException("Nhân viên không tồn tại"));
            buildingEntity.getUsers().add(userEntity);
        }
        buildingRepository.save(buildingEntity);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Lưu nhân viên thành công");
        return responseDTO;
    }


    @Override
    public int countTotalItems() {
        return buildingRepository.countTotalItem();
    }

    @Override
    public List<BuildingDTO> getBuilding(String searchValue, Pageable pageable) {
        List<BuildingEntity> buildingEntities = buildingRepository.getAllBuilding(pageable);
        List<BuildingDTO> results = new ArrayList<>();
        for (BuildingEntity buildingEntity : buildingEntities) {
            BuildingDTO buildingDTO = buildingConverter.convertToDTO(buildingEntity);

            results.add(buildingDTO);
        }
        return results;
    }

    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {

        String path = "/img/building/" + buildingDTO.getImageName();

        if (buildingDTO.getImageBase64() != null) {
            if (buildingEntity.getImage() != null) {
                if (!path.equals(buildingEntity.getImage())) {
                    File file = new File(SystemConstant.Path_Image + buildingEntity.getImage());
                    file.delete();
                }
            }
            String base64 = buildingDTO.getImageBase64();
            if(base64.contains(",")){
                base64 = base64.split(",")[1];
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes(StandardCharsets.UTF_8));
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setImage(path);
        }
    }
    @Override
    public BuildingDTO getBuildingById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).orElse(null);
        return buildingConverter.convertToDTO(buildingEntity);
    }
}
