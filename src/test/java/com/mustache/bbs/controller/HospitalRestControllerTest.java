package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.HospitalResponse;
import com.mustache.bbs.service.HospitalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HospitalRestController.class)
class HospitalRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean // 테스트를 위해 가짜 객체를 쓰겠다!
    HospitalService hospitalService;

    // {"id":2321,"roadNameAddress":"서울특별시 서초구 서초중앙로 230, 202호 (반포동, 동화반포프라자빌딩)",
// "hospitalName":"노소아청소년과의원","patientRoomCount":0,"totalNumberOfBeds":0,"businessTypeName":"의원","totalAreaSize":0.0,"businessStatusName":"영업중"}

    @Test
    @DisplayName("1개의 Json 형태로 Response가 잘 오는지") // 비지니스로직(Service)가 아니라 Contoller만 검증
    void JsonResponse() throws Exception {
        HospitalResponse hospitalResponse = HospitalResponse.builder()
                .id(2321)
                .roadNameAddress("서울특별시 서초구 서초중앙로 230, 202호 (반포동, 동화반포프라자빌딩)")
                .hospitalName("노소아청소년과의원")
                .patientRoomCount(0)
                .totalNumberOfBeds(0)
                .businessTypeName("의원")
                .totalAreaSize(0.0f)
                .businessStatusName("영업중")
                .build();

        given(hospitalService.getHospital(2321)).willReturn(hospitalResponse);

        int hospitalId = 2321;
        String url = String.format("/api/v1/hospitals/%d",hospitalId);

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hospitalName").exists()) // $는 루트 $아래에 hospitalName이 있어야 함
                .andExpect(jsonPath("$.hospitalName").value("노소아청소년과의원"))
                .andExpect(jsonPath("$.businessStatusName").exists()) // $는 루트 $아래에 businessStatusName이 있어야 함
                .andExpect(jsonPath("$.businessStatusName").value("영업중"))
                .andDo(print()); // http request, response 내역을 출력

        // getHospital() 메소드의 호출이 이었는지 확인
        verify(hospitalService).getHospital(hospitalId);

    }
}
