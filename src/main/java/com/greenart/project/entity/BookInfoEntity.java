package com.greenart.project.entity;

import java.util.Date;

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
@Table(name = "book_info")
@Entity
public class BookInfoEntity {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "bi_seq") private Long biSeq;
     @Column(name = "bi_title") private String biTitle;
     @Column(name = "bi_sub_title") private String biSubTitle;
     @Column(name = "bi_price") private Integer biPrice;
     @Column(name = "bi_discount") private Double biDiscount;
     @Column(name = "bi_benefit") private Double biBenefit;
     @Column(name = "bi_alarm") private String  biAlarm;
     @Column(name = "bi_public_dt") private Date biPublicDt;
     @Column(name = "bi_publisher") private String biPublisher;
     @Column(name = "bi_author") private String biAuthor;
     @Column(name = "bi_di_seq") private Long biDiSeq;
     @Column(name = "bi_ii_seq") private Long biIiSeq;
}
