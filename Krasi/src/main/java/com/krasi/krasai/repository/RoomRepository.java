package com.krasi.krasai.repository;

import com.krasi.krasai.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    Optional<RoomEntity> findByType(String type);

    List<RoomEntity> findAllByArea(Long area);
}
