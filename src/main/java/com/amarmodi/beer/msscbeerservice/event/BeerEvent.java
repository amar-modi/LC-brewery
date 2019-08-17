package com.amarmodi.beer.msscbeerservice.event;

import com.amarmodi.beer.msscbeerservice.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent {

    private final BeerDto beerDto;
}
