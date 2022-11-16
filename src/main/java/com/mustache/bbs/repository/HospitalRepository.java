package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    // JPA 기능 활용
    // 이름(HospitalName)으로 검색
    Page<Hospital> findByHospitalNameContaining(String searchKeyword, Pageable pageable);

    // 업태구분명(businessType)으로 검색
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypesName);

    // 주소(roadNameAddress) + 업태구분명(businessType)
    List<Hospital> findByRoadNameAddressContainingAndBusinessTypeNameContaining(String roadNameAddress, String businessTypesName);

    // 병상수가 특정 수 조건 사이 검색
    List<Hospital> findByTotalNumberOfBedsBetween(Integer row, Integer high);
}