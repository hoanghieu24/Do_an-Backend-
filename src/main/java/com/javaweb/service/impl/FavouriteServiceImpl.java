package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.FavouriteEntity;
import com.javaweb.model.dto.FavouriteDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.FavouriteRepository;
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

    @Override
    public List<FavouriteDTO> getFavoritesByCustomer(Long customerId) {
        return favouriteRepository.findByCustomerId(customerId)
                .stream()
                .map(e -> {
                    FavouriteDTO dto = new FavouriteDTO();

                    dto.setCustomerId(e.getCustomer().getId());
                    dto.setBuildingId(e.getBuilding().getId());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public void addFavorite(FavouriteDTO dto) {
        if (!favouriteRepository.existsByCustomerIdAndBuildingId(dto.getCustomerId(), dto.getBuildingId())) {
            FavouriteEntity entity = new FavouriteEntity();

            CustomerEntity customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));

            BuildingEntity building = buildingRepository.findById(dto.getBuildingId())
                    .orElseThrow(() -> new RuntimeException("Building not found"));

            entity.setCustomer(customer);
            entity.setBuilding(building);
            entity.setCreatedDate(new Date());
            favouriteRepository.save(entity);
            System.out.println("DTO: customerId=" + dto.getCustomerId() + ", buildingId=" + dto.getBuildingId());
        }
    }

    @Override

    public void removeFavorite(FavouriteDTO dto) {
        favouriteRepository.deleteByCustomerIdAndBuildingId(dto.getCustomerId(), dto.getBuildingId());
    }

    @Override
    public boolean isFavorite(Long customerId, Long buildingId) {
        return favouriteRepository.existsByCustomerIdAndBuildingId(customerId, buildingId);
    }
}

