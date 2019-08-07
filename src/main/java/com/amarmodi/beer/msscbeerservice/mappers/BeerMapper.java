package com.amarmodi.beer.msscbeerservice.mappers;

import com.amarmodi.beer.msscbeerservice.domain.Beer;
import com.amarmodi.beer.msscbeerservice.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses= {DateMapper.class})
public interface BeerMapper {

    BeerDto BeerToBeerDto(Beer beer);
    Beer BeerDtoToBeer(BeerDto beerDto);
}
