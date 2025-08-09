package com.javaweb.service;

import com.javaweb.model.dto.FavouriteDTO;

import java.util.List;

// Interface
public interface FavouriteService {
    List<FavouriteDTO> getFavoritesByCustomer(Long customerId);
    void addFavorite(FavouriteDTO dto);
    void removeFavorite(FavouriteDTO dto);
    boolean isFavorite(Long customerId, Long buildingId);


}

