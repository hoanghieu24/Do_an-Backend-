package com.javaweb.service.impl;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
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
import com.javaweb.repository.*;
import com.javaweb.service.BuildingService;
import com.javaweb.utils.DistrictCode;
import com.javaweb.utils.SecurityUtils;
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
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    // tim
    @Override
    public List<BuildingResponse> findAll(Map<String, Object> param, List<String> typeCode) {

        String districtKey = (String) param.get("district");
        if (districtKey != null && !districtKey.isEmpty()) {
            try {
                districtCode districtEnum = districtCode.valueOf(districtKey);
                param.put("district", districtEnum.name());
            } catch (IllegalArgumentException e) {
                param.remove("district");
            }
        }

        BuildingSearchBuilder buildingSearchBuilder =
                builderConverter.toBuildingSearchBuilder(param, typeCode);

        Long currentUserId = SecurityUtils.getCurrentUserId();
        boolean isAdmin = SecurityUtils.isAdmin();
        boolean isUsers = SecurityUtils.isUser();

        List<BuildingEntity> buildingEntities;


        if (currentUserId == null) {
            buildingEntities = buildingRepository.findAll(buildingSearchBuilder);
        }

        else if (isAdmin) {
            buildingEntities = buildingRepository.findAll(buildingSearchBuilder);
        }

        else if (isUsers){
            buildingEntities = buildingRepository.findAll(buildingSearchBuilder);
        }

        else {
            buildingEntities = buildingRepository.findByStaffAndCondition(
                    currentUserId,
                    buildingSearchBuilder
            );
        }

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

        // 🔥 SET STAFF
        if (buildingDTO.getStaffIds() != null && !buildingDTO.getStaffIds().isEmpty()) {
            List<UserEntity> staffs = userRepository.findAllById(buildingDTO.getStaffIds());
            buildingEntity.setUsers(staffs);
        }

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

    List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");

    List<AssignmentBuildingEntity> assigned =
            assignmentBuildingRepository.findByBuildingId(buildingId);

    Set<Long> assignedStaffIds = assigned.stream()
            .map(a -> a.getStaff().getId())
            .collect(Collectors.toSet());

    List<StaffResponseDto> result = new ArrayList<>();

    for (UserEntity staff : staffs) {
        StaffResponseDto dto = new StaffResponseDto();
        dto.setStaffId(staff.getId());
        dto.setUserName(staff.getUserName());
        dto.setChecked(assignedStaffIds.contains(staff.getId()) ? "checked" : "");
        result.add(dto);
    }

    ResponseDTO responseDTO = new ResponseDTO();
    responseDTO.setData(result);
    responseDTO.setMessage("OK");

    return responseDTO;
}

    @Override
    public Object loadProduct(Long ids) {
        return null;
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
        try {
            if (buildingDTO.getImageBase64() == null || buildingDTO.getImageBase64().isEmpty()) {
                return;
            }

            String fileName = buildingDTO.getImageName();
            String relativePath = "/building/" + fileName;
            String fullPath = SystemConstant.Path_Image + "\\" + relativePath;

            // Nếu có ảnh cũ thì xóa
            if (buildingEntity.getImage() != null) {
                File oldFile = new File(SystemConstant.Path_Image + "\\" + buildingEntity.getImage());
                if (oldFile.exists()) oldFile.delete();
            }

            // Cắt header base64
            String base64 = buildingDTO.getImageBase64();
            if (base64.contains(",")) {
                base64 = base64.split(",")[1];
            }

            byte[] bytes = Base64.decodeBase64(base64);

            // Tạo thư mục nếu chưa tồn tại
            File folder = new File(SystemConstant.Path_Image + "\\building");
            if (!folder.exists()) folder.mkdirs();

            // Ghi file
            Files.write(Paths.get(fullPath), bytes);

            buildingEntity.setImage(relativePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public BuildingDTO getBuildingById(Long id) throws AccessDeniedException {

        Long staffId = SecurityUtils.getCurrentUserId();

        if (!SecurityUtils.isAdmin() ) {
            boolean ok = buildingRepository.isStaffOwner(staffId, id);
            if (!ok) {
                throw new AccessDeniedException("🚫 Không có quyền truy cập toà nhà này");
            }
        }

        BuildingEntity buildingEntity = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Toà nhà không tồn tại"));

        return buildingConverter.convertToDTO(buildingEntity);
    }

    @Override
    public BuildingDTO getBuildingFavouriteById(Long id) throws AccessDeniedException {

        Long staffId = SecurityUtils.getCurrentUserId();

        BuildingEntity buildingEntity = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Toà nhà không tồn tại"));

        return buildingConverter.convertToDTO(buildingEntity);
    }

}
