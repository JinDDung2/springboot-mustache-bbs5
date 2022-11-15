package com.example.bbsexercise.controller;

import com.example.bbsexercise.domain.dto.HospitalResponseDto;
import com.example.bbsexercise.service.HospitalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.verify;

@WebMvcTest(HospitalRestController.class)
class HospitalRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean // 가짜 HospitalService 객체 사용
    HospitalService hospitalService; // 가짜 객체 덕에 DB와 상관없이 테스트 가능

    @Test
    @DisplayName("1개의 json 형태로 Response가 잘 오는지")
    void jsonResponse() throws Exception {
        // {"id":2231,"roadNameAddress":"서울특별시 서초구 방배로20길 4 (방배동,5층)",
        // "hospitalName":"박상균치과의원","patientRoomCount":0,"totalNumberOfBeds":0,
        // "businessTypeName":"치과의원","businessStatusName":"영업중","totalAreaSize":197.94}

        HospitalResponseDto hospitalResponseDto = HospitalResponseDto.builder()
                .id(2231)
                .roadNameAddress("서울특별시 서초구 방배로20길 4 (방배동,5층)")
                .hospitalName("박상균치과의원")
                .patientRoomCount(0)
                .totalNumberOfBeds(0)
                .businessTypeName("치과의원")
                .businessStatusName("영업중")
                .totalAreaSize(197.94f)
                .build();

        given(hospitalService.getHospital(2231))
                .willReturn(hospitalResponseDto);

        int hospitalId = 2231;
        String url = String.format("/api/v1/hospitals/%d", hospitalId);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hospitalName").exists())
                .andExpect(jsonPath("$.hospitalName").value("박상균치과의원"))
                .andDo(print()); // http request, response 내역 출력

        //getHospital()메소드의 호출이 있었는지 확인
        verify(hospitalService).getHospital(hospitalId);

    }
}