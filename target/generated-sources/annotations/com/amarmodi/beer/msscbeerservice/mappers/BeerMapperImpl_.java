package com.amarmodi.beer.msscbeerservice.mappers;

import com.amarmodi.beer.msscbeerservice.domain.Beer;
import com.amarmodi.beer.msscbeerservice.domain.Beer.BeerBuilder;
import com.amarmodi.beer.msscbeerservice.model.BeerDto;
import com.amarmodi.beer.msscbeerservice.model.BeerDto.BeerDtoBuilder;
import com.amarmodi.beer.msscbeerservice.model.BeerStyleEnum;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-08-17T13:19:59-0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.4 (Oracle Corporation)"
)
@Component
@Qualifier("delegate")
public class BeerMapperImpl_ implements BeerMapper {

    @Autowired
    private DateMapper dateMapper;

    @Override
    public BeerDto BeerToBeerDto(Beer beer) {
        if ( beer == null ) {
            return null;
        }

        BeerDtoBuilder beerDto = BeerDto.builder();

        beerDto.id( beer.getId() );
        if ( beer.getVersion() != null ) {
            beerDto.version( beer.getVersion().intValue() );
        }
        beerDto.createdDate( dateMapper.asOffsetDateTime( beer.getCreatedDate() ) );
        beerDto.lastModifiedDate( dateMapper.asOffsetDateTime( beer.getLastModifiedDate() ) );
        beerDto.beerName( beer.getBeerName() );
        if ( beer.getBeerStyle() != null ) {
            beerDto.beerStyle( Enum.valueOf( BeerStyleEnum.class, beer.getBeerStyle() ) );
        }
        beerDto.upc( beer.getUpc() );
        beerDto.price( beer.getPrice() );

        return beerDto.build();
    }

    @Override
    public Beer BeerDtoToBeer(BeerDto beerDto) {
        if ( beerDto == null ) {
            return null;
        }

        BeerBuilder beer = Beer.builder();

        beer.id( beerDto.getId() );
        if ( beerDto.getVersion() != null ) {
            beer.version( beerDto.getVersion().longValue() );
        }
        beer.createdDate( dateMapper.asTimestamp( beerDto.getCreatedDate() ) );
        beer.lastModifiedDate( dateMapper.asTimestamp( beerDto.getLastModifiedDate() ) );
        beer.beerName( beerDto.getBeerName() );
        if ( beerDto.getBeerStyle() != null ) {
            beer.beerStyle( beerDto.getBeerStyle().name() );
        }
        beer.upc( beerDto.getUpc() );
        beer.price( beerDto.getPrice() );

        return beer.build();
    }

    @Override
    public BeerDto BeerToBeerDtoNoInventory(Beer beer) {
        if ( beer == null ) {
            return null;
        }

        BeerDtoBuilder beerDto = BeerDto.builder();

        beerDto.id( beer.getId() );
        if ( beer.getVersion() != null ) {
            beerDto.version( beer.getVersion().intValue() );
        }
        beerDto.createdDate( dateMapper.asOffsetDateTime( beer.getCreatedDate() ) );
        beerDto.lastModifiedDate( dateMapper.asOffsetDateTime( beer.getLastModifiedDate() ) );
        beerDto.beerName( beer.getBeerName() );
        if ( beer.getBeerStyle() != null ) {
            beerDto.beerStyle( Enum.valueOf( BeerStyleEnum.class, beer.getBeerStyle() ) );
        }
        beerDto.upc( beer.getUpc() );
        beerDto.price( beer.getPrice() );

        return beerDto.build();
    }
}
