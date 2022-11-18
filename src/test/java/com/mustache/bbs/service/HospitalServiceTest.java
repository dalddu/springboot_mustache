package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.HospitalResponse;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.repository.HospitalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class HospitalServiceTest {

    private HospitalRepository hospitalRepository= Mockito.mock(HospitalRepository.class);

    private HospitalService hospitalService;

    @BeforeEach
    void setUp() {
        hospitalService = new HospitalService(hospitalRepository);
    }

    @Test
    void serviceTest() {
        Mockito.when(hospitalRepository.findById(1))
                .thenReturn(Optional.of(new Hospital(1,"병원이름","병원주소",13)));

        HospitalResponse hospitalResponse = hospitalService.getHospital(1);
        assertEquals("영업중",hospitalResponse.getBusinessStatusName());
    }
}