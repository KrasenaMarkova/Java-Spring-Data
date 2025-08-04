package com.krasi.krasai.repository;

import com.krasi.krasai.entity.FurnitureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FurnitureRepository extends JpaRepository<FurnitureEntity, Long> {
}
