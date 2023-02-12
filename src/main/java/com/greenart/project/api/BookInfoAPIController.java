package com.greenart.project.api;

import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.greenart.project.entity.BookInfoEntity;
import com.greenart.project.entity.DeliveryInfoEntity;
import com.greenart.project.entity.ImageInfoEntity;
import com.greenart.project.repository.BookInfoRepository;
import com.greenart.project.repository.DeliveryInfoRepository;
import com.greenart.project.repository.ImageInfoRepository;
import com.greenart.project.service.BookService;

@RestController
@RequestMapping("/api")
public class BookInfoAPIController {
     @Autowired BookInfoRepository bRepo;
     @Autowired DeliveryInfoRepository dRepo;
     @Autowired ImageInfoRepository iRepo;
     @Autowired BookService bService;
     @Value("${file.image.book}") String book_img_path;

     @PutMapping("/add/book")
     public ResponseEntity<Object> insertBook(@RequestBody BookInfoEntity data) {
          Map<String, Object> map = bService.addBookInfo(data);
          return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
     }

     @PutMapping("/add/delivery")
     public ResponseEntity<Object> insertDelivery(@RequestBody DeliveryInfoEntity data) {
          Map<String, Object> map = bService.addDeliveryInfo(data);
          return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
     }

     @PutMapping("/upload")
     public ResponseEntity <Object> putImage (@RequestPart MultipartFile file) {
          
          Map < String, Object > map = new LinkedHashMap < String, Object > ();
          System.out.println(file.getOriginalFilename());
          Path folderLocation = Paths.get(book_img_path);
          String originFileName = file.getOriginalFilename();
          String[] split = originFileName.split("\\.");
          String ext = split[split.length - 1];
          String filename = "";
          for(int i=0; i<split.length-1; i++) {
               filename += split[i];
          }
          String saveFilename = "book"+"_";
          Calendar c = Calendar.getInstance();
          saveFilename += c.getTimeInMillis()+"."+ext;
          Path targerFile = folderLocation.resolve(saveFilename);
          try {
               Files.copy(file.getInputStream(), targerFile, StandardCopyOption.REPLACE_EXISTING);
          } catch (Exception e) {
               e.printStackTrace();
          }
          ImageInfoEntity data = new ImageInfoEntity();
               data.setIiFileName(saveFilename); 
               data.setIiUri(filename); 
               bService.addImageInfo(data);
               return new ResponseEntity < > (map, HttpStatus.OK);
     }

          @GetMapping("/listall/book")
          public ResponseEntity<Object> bookList(Pageable pageable) {
               Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
               resultMap.put("list", bRepo.findAll());
               return new ResponseEntity<>(resultMap, HttpStatus.OK);
          }

          @GetMapping("/list/delivery")
          public ResponseEntity<Object> deliveryList(Pageable pageable) {
               Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
               resultMap.put("list", dRepo.findAll());
               return new ResponseEntity<>(resultMap, HttpStatus.OK);
          }

          @GetMapping("/list/image")
          public ResponseEntity<Object> imageList(Pageable pageable) {
               Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
               resultMap.put("list", iRepo.findAll());
               return new ResponseEntity<>(resultMap, HttpStatus.OK);
          }
     
     @GetMapping("/image/{iiUri}")
     public ResponseEntity<Resource> getImage (@PathVariable String iiUri, HttpServletRequest request) throws Exception {
          Path folderLocation = Paths.get(book_img_path);
          String filename = bService.getIiFileNameByUri(iiUri);
          String[] split = filename.split("\\.");
          String ext = split[split.length-1];
          String exportName = iiUri+"."+ext;
          Path targetFile = folderLocation.resolve(filename);
          Resource r = null;
          try {
               r = new UrlResource(targetFile.toUri());
          } catch (Exception e) {
               e.printStackTrace();
          }
          String contentType = null;
          try {
               contentType = request.getServletContext().getMimeType(r.getFile().getAbsolutePath());
               if (contentType == null) {
                    contentType = "application/octet-stream";
               }
          } catch (Exception e) {
               e.printStackTrace();
          }
          return ResponseEntity.ok()
          .contentType(MediaType.parseMediaType(contentType)) 
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=\"" + URLEncoder.encode(exportName, "UTF-8") + "\"")
          .body(r); 
     }
     @GetMapping("/list/book")
     public Map<String, Object> getBookList(@RequestParam @Nullable String keyword, @RequestParam @Nullable Integer page) {
          Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

          if(keyword == null) keyword = "";
          if(page == null) page = 1;

          resultMap.put("totalPage", bRepo.getBookPageCount(keyword));
          resultMap.put("currentPage", page);
          resultMap.put("keyword", keyword);
          List<BookInfoEntity> list = bRepo.searchBookTitle(keyword, (page-1)*8);
          resultMap.put("totalCount", list.size());
          resultMap.put("list", list);

          return resultMap;
     }
}
