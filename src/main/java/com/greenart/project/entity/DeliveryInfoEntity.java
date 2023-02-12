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
@Table(name = "delivery_info")
@Entity
public class DeliveryInfoEntity {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "di_seq") private Long diSeq;
     @Column(name = "di_min_price") private Integer diMinPrice;
     @Column(name = "di_price") private Integer diPrice;
     @Column(name = "di_day") private String diDay;
}
