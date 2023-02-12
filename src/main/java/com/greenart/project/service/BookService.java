package com.greenart.project.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.greenart.project.entity.BookInfoEntity;
import com.greenart.project.entity.DeliveryInfoEntity;
import com.greenart.project.entity.ImageInfoEntity;
import com.greenart.project.repository.BookInfoRepository;
import com.greenart.project.repository.DeliveryInfoRepository;
import com.greenart.project.repository.ImageInfoRepository;

@Service
public class BookService {
     @Autowired BookInfoRepository bRepo;
     @Autowired DeliveryInfoRepository dRepo;
     @Autowired ImageInfoRepository iRepo;
     public Map<String, Object> addBookInfo(BookInfoEntity data) {
          Map<String, Object> map = new LinkedHashMap<String, Object>();
          bRepo.save(data);
          map.put("status", true);
          map.put("message", "책이 추가되었습니다.");
          map.put("code", HttpStatus.CREATED);
          return map;
     }
     
     public Map<String, Object> addDeliveryInfo(DeliveryInfoEntity data) {
          Map<String, Object> map = new LinkedHashMap<String, Object>();
          dRepo.save(data);
          map.put("status", true);
          map.put("message", "배송정보가 추가되었습니다.");
          map.put("code", HttpStatus.CREATED);
          return map;
     }

     public Map<String, Object> addImageInfo(ImageInfoEntity data) {
          Map<String, Object> map = new LinkedHashMap<String, Object>();
          iRepo.save(data);
          map.put("status", true);
          map.put("message", "이미지가 추가되었습니다.");
          map.put("code", HttpStatus.CREATED);
          return map;
     }

     public Map<String, Object>  getBookList(BookInfoEntity data) {
          Map<String, Object> map = new LinkedHashMap<String, Object>();
          map.put("status", true);
          map.put("message", "조회하였습니다.");
          map.put("code", HttpStatus.OK);
          return map;
     }

     public Map<String, Object> getDeliveryInfo(DeliveryInfoEntity data) {
          Map<String, Object> map = new LinkedHashMap<String, Object>();
          map.put("status", true);
          map.put("message", "조회하였습니다.");
          map.put("code", HttpStatus.OK);
          map.put("list", data);
          return map;
     }

     public Map<String, Object> getImageInfo(ImageInfoEntity data) {
          Map<String, Object> map = new LinkedHashMap<String, Object>();
          map.put("status", true);
          map.put("message", "조회하였습니다.");
          map.put("code", HttpStatus.OK);
          return map;
     }
     
     public String getIiFileNameByUri (String iiUri) {
          List<ImageInfoEntity> data = iRepo.findByIiUri(iiUri);
          return data.get(0).getIiFileName();
     }
}
