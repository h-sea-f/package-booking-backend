package com.oocl.packagebooking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.packagebooking.model.Waybill;
import com.oocl.packagebooking.service.WaybillService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(WaybillController.class)
public class WaybillControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private WaybillService waybillService;

    @Test
    void should_add_and_return_waybill() throws Exception {
        Waybill waybill = new Waybill();
        waybill.setId("123456");
        waybill.setPhoneNumber("15574957517");
        waybill.setConsignee("Sean");
        waybill.setWeight(3.0);
        waybill.setStatus(0);
        when(waybillService.save(ArgumentMatchers.any(Waybill.class))).thenReturn(waybill);
        ResultActions result = mvc.perform(post("/waybills")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(waybill)));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123456"))
                .andExpect(jsonPath("$.phoneNumber").value("15574957517"));
    }

    @Test
    void should_return_waybill_list_when_getAll() throws Exception {
        Waybill waybill = new Waybill();
        waybill.setId("123456");
        waybill.setPhoneNumber("15574957517");
        waybill.setConsignee("Sean");
        waybill.setWeight(3.0);
        waybill.setStatus(0);
        Waybill waybill1 = new Waybill();
        waybill1.setId("123456789");
        waybill1.setPhoneNumber("15574957517");
        waybill1.setConsignee("Sean123");
        waybill1.setWeight(3.0);
        waybill1.setStatus(0);
        List<Waybill> waybills = new ArrayList<>();
        waybills.add(waybill);
        waybills.add(waybill1);
        when(waybillService.getAll()).thenReturn(waybills);
        ResultActions result = mvc.perform(get("/waybills"));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value("123456"))
                .andExpect(jsonPath("$.[1].id").value("123456789"))
                .andExpect(jsonPath("$.[0].consignee").value("Sean"))
                .andExpect(jsonPath("$.[1].consignee").value("Sean123"));

    }

    @Test
    public void should_change_statue_to_finished() throws Exception {
        Waybill waybill = new Waybill();
        waybill.setId("123456");
        waybill.setPhoneNumber("15574957517");
        waybill.setConsignee("Sean");
        waybill.setWeight(3.0);
        waybill.setStatus(1);

        when(waybillService.finished(anyString())).thenReturn(waybill);
        ResultActions result = mvc.perform(post("/waybills/{id}", waybill.getId()));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123456"))
                .andExpect(jsonPath("$.consignee").value("Sean"));


    }

    @Test
    public void should_add_time_and_change_statue_to_ordered() throws Exception {
        Waybill waybill = new Waybill();
        waybill.setId("123456");
        waybill.setPhoneNumber("15574957517");
        waybill.setConsignee("Sean");
        waybill.setWeight(3.0);
        waybill.setStatus(1);

        when(waybillService.ordered(anyString(),ArgumentMatchers.any(Waybill.class))).thenReturn(waybill);
        ResultActions result = mvc.perform(patch("/waybills/{id}", waybill.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(waybill)));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123456"))
                .andExpect(jsonPath("$.consignee").value("Sean"));

    }

}
