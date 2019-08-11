package com.amarmodi.beer.msscbeerservice.mappers;

import com.amarmodi.beer.msscbeerservice.domain.Beer;
import com.amarmodi.beer.msscbeerservice.model.BeerDto;
import com.amarmodi.beer.msscbeerservice.services.inventory.BeerInventoryService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BeerMapperDecorator implements BeerMapper {
    private BeerInventoryService beerInventoryService;
    private BeerMapper mapper;

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setMapper(BeerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BeerDto BeerToBeerDto(Beer beer) {
        BeerDto beerDto = mapper.BeerToBeerDto(beer);
        beerDto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
        System.out.println(beerDto);
        return beerDto;
    }

    @Override
    public BeerDto BeerToBeerDtoNoInventory(Beer beer) {
        BeerDto beerDto = mapper.BeerToBeerDto(beer);
        System.out.println(beerDto);
        return beerDto;
    }


    @Override
    public Beer BeerDtoToBeer(BeerDto beerDto) {
        return mapper.BeerDtoToBeer(beerDto);
    }
}