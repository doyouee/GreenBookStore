package com.greenart.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "image_info")
@Entity
public class ImageInfoEntity {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "ii_seq") private Long iiSeq;
     @Column(name = "ii_file_name") private String iiFileName;
     @Column(name = "ii_uri") private String iiUri;
}
