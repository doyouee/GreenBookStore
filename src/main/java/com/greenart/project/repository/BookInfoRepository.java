package com.greenart.project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenart.project.entity.BookInfoEntity;

@Repository
public interface BookInfoRepository extends JpaRepository<BookInfoEntity, Long> {
     public Page<BookInfoEntity> findAll(Pageable pageable);
     @Query(value = "select * from book_info where bi_title like %:keyword% limit 8 offset :offset", nativeQuery=true)
     List<BookInfoEntity> searchBookTitle(@Param("keyword") String keyword, @Param("offset") Integer offset);
     @Query(value = "select ceil(count(b)/8) from BookInfoEntity b where b.biTitle like %:keyword%")
     Integer getBookPageCount(@Param("keyword") String keyword);
}
