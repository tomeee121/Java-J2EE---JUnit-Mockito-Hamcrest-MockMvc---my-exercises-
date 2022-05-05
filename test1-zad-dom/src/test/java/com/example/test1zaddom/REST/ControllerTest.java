package com.example.test1zaddom.REST;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.example.test1zaddom.CargoRepository;
import com.example.test1zaddom.Unit;
import com.example.test1zaddom.UnitRepository;
import com.example.test1zaddom.UnitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Mock
    CargoRepository cargoRepository;
    @Mock
    UnitRepository unitRepository;

    @InjectMocks
    UnitService unitService;

    @Test
    void getUnitsByMaxCargoWeightShouldCorrectlyFilterMapInServiceMethod() throws Exception {

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/10500")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();
        //then
        Unit[] units = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Unit[].class);
        assertThat(units.length,is(3));
    }
}