package com.javaweb.entity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "vr_scene")
@Data
public class VrSceneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "building_id", nullable = false)

    private BuildingEntity building;

    @Column(nullable = false)
    private String name;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    private Float ath;
    private Float atv;
    private String targetScene;
}
