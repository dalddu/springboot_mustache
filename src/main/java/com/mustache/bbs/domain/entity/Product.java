package com.mustache.bbs.domain.entity;


import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")   // 생략 가능
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;    //상품 번호

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private Integer stock;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

