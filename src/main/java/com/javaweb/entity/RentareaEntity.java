package com.javaweb.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Data
@Entity
@Table(name = "rentarea")
public class RentareaEntity  extends BaseEntity {


    @Column(name = "value")
    private Integer value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buildingid")
    private BuildingEntity building;


    @Override
    public String toString() {
        return value.toString();
    }
}