package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);
}
