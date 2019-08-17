package com.amarmodi.beer.msscbeerservice.event;

import com.amarmodi.beer.msscbeerservice.model.BeerDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
