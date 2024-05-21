package com.company.project.model.repository;

import com.company.project.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Promotion findByName(String name);
}
