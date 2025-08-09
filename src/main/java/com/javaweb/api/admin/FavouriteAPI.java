package com.javaweb.api.admin;

import com.javaweb.model.dto.FavouriteDTO;
import com.javaweb.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buildings")
public class FavouriteAPI {

    @Autowired
    private FavouriteService favouriteService;

    // ✅ 1. Hiển thị danh sách yêu thích của một user
    @GetMapping("/favorites/{userId}")
    public ResponseEntity<List<FavouriteDTO>> getFavorites(@PathVariable Long userId) {
        return ResponseEntity.ok(favouriteService.getFavoritesByCustomer(userId));
    }

    // ✅ 2. Thêm một toà nhà vào danh sách yêu thích
    @PostMapping("/favorites")
    public ResponseEntity<?> addFavorite(@RequestBody FavouriteDTO favouriteDTO) {
        favouriteService.addFavorite(favouriteDTO);
        return ResponseEntity.ok("Đã thêm vào yêu thích");
    }

    // ✅ 3. Xoá một toà nhà khỏi danh sách yêu thích
    @PostMapping("/favorites/remove")
    public ResponseEntity<?> removeFavorite(@RequestBody FavouriteDTO favouriteDTO) {
        favouriteService.removeFavorite(favouriteDTO);
        return ResponseEntity.ok("Đã xoá khỏi yêu thích");
    }


    // ✅ 4. Kiểm tra xem user đã yêu thích toà nhà đó chưa
    @GetMapping("/favorites/check")
    public ResponseEntity<Boolean> checkFavorite(@RequestParam Long userId, @RequestParam Long buildingId) {
        boolean isFav = favouriteService.isFavorite(userId, buildingId);
        return ResponseEntity.ok(isFav);
    }



}
