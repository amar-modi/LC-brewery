package com.amarmodi.beer.msscbeerservice.controller;

import com.amarmodi.beer.msscbeerservice.domain.Beer;
import com.amarmodi.beer.msscbeerservice.model.BeerDto;
import com.amarmodi.beer.msscbeerservice.model.BeerStyleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.UUID;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {
        //Does a get on this endpoint using the provided url and then accepts xyz params and expects an Ok status code
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer/" + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerDTOJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDTOJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        UUID id = UUID.randomUUID();
        BeerDto beerDto = getValidBeerDto();
        String beerDTOJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/beer/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDTOJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    private BeerDto getValidBeerDto(){
        return BeerDto.builder()
                .beerName("Mount Hair")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .price(new BigDecimal("2.34"))
                .upc(2457654323456L)
                .build();
    }
}