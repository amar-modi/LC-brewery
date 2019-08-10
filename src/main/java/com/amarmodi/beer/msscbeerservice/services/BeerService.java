package com.amarmodi.beer.msscbeerservice.services;

import com.amarmodi.beer.msscbeerservice.model.BeerDto;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface BeerService {


    BeerDto getById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto save(UUID beerId, BeerDto beerDto);
}
