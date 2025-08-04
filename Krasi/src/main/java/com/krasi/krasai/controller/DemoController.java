package com.krasi.krasai.controller;

import com.krasi.krasai.entity.RoomEntity;
import com.krasi.krasai.repository.RoomRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    private final RoomRepository roomRepository;

    public DemoController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/rooms")
    public List<RoomEntity> getAllRooms() {
        return roomRepository.findAll();

    }


}
