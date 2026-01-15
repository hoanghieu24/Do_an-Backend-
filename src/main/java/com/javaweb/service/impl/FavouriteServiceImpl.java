package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.FavouriteEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.FavouriteDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.FavouriteRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FavouriteServiceImpl implements FavouriteService {

    @Autowired
    private FavouriteRepository favouriteRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<FavouriteDTO> getFavoritesByUsers(Long UserId) {
        return favouriteRepository.findByUserId(UserId)
                .stream()
                .map(e -> {
                    FavouriteDTO dto = new FavouriteDTO();

                    dto.setUserId(e.getUser().getId());
                    dto.setBuildingId(e.getBuilding().getId());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public void addFavorite(FavouriteDTO dto) {
        if (!favouriteRepository.existsByUserIdAndBuildingId(dto.getUserId(), dto.getBuildingId())) {
            FavouriteEntity entity = new FavouriteEntity();

            UserEntity user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            BuildingEntity building = buildingRepository.findById(dto.getBuildingId())
                    .orElseThrow(() -> new RuntimeException("Building not found"));

            entity.setUser(user);
            entity.setBuilding(building);
            entity.setCreatedDate(new Date());
            favouriteRepository.save(entity);
            System.out.println("DTO: customerId=" + dto.getUserId() + ", buildingId=" + dto.getBuildingId());
        }
    }

    @Override

    public void removeFavorite(FavouriteDTO dto) {
        System.out.println("🧩 User ID: " + dto.getUserId());
        System.out.println("🏢 Building ID: " + dto.getBuildingId());

        if (dto.getUserId() == null || dto.getBuildingId() == null) {
            throw new IllegalArgumentException("User ID hoặc Building ID bị null kìa bro!");
        }

        favouriteRepository.deleteByUser_IdAndBuilding_Id(dto.getUserId(), dto.getBuildingId());
    }


    @Override
    public boolean isFavorite(Long customerId, Long buildingId) {
        return favouriteRepository.existsByUserIdAndBuildingId(customerId, buildingId);
    }
}

