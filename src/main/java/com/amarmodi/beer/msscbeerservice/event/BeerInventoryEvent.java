package com.amarmodi.beer.msscbeerservice.event;

import com.amarmodi.beer.msscbeerservice.model.BeerDto;

public class BeerInventoryEvent extends BeerEvent {
    BeerInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
