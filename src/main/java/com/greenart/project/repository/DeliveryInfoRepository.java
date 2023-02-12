package com.greenart.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.project.entity.DeliveryInfoEntity;

@Repository
public interface DeliveryInfoRepository extends JpaRepository<DeliveryInfoEntity, Long> {
     public Page<DeliveryInfoEntity> findAll(Pageable pageable);
}
