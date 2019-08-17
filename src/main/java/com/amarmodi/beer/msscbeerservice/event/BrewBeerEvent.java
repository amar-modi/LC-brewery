package com.amarmodi.beer.msscbeerservice.event;

import com.amarmodi.beer.msscbeerservice.model.BeerDto;

public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
