package com.krasi.krasai.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "furnitures")
public class FurnitureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @ManyToOne()
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    public FurnitureEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
