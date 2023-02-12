package com.greenart.project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.project.entity.ImageInfoEntity;

@Repository
public interface ImageInfoRepository extends JpaRepository<ImageInfoEntity, Long> {
     public Page<ImageInfoEntity> findAll(Pageable pageable);
     public List<ImageInfoEntity> findByIiUri(String iiUri);
}
