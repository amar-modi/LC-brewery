package com.amarmodi.beer.msscbeerservice.services;

import com.amarmodi.beer.msscbeerservice.domain.Beer;
import com.amarmodi.beer.msscbeerservice.mappers.BeerMapper;
import com.amarmodi.beer.msscbeerservice.model.BeerDto;
import com.amarmodi.beer.msscbeerservice.model.BeerPagedList;
import com.amarmodi.beer.msscbeerservice.model.BeerStyleEnum;
import com.amarmodi.beer.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@Service("beerService")
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    @Cacheable(cacheNames = "beerCache", key="#beerId" ,condition="#showInventoryOnHand == false")
    public BeerDto getById(UUID beerId, boolean showInventoryOnHand) {
        BeerDto beerDto = null;
        if(showInventoryOnHand){
            beerDto = beerMapper.BeerToBeerDto(
                    beerRepository.findById(beerId).orElseThrow(() -> new NotFoundException()));
        }else{
            beerDto = beerMapper.BeerToBeerDtoNoInventory(
                    beerRepository.findById(beerId).orElseThrow(() -> new NotFoundException()));
        }
        return beerDto;
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        Beer beer = beerMapper.BeerDtoToBeer(beerDto);
        return beerMapper.BeerToBeerDto(beerRepository.save(beer));
    }

    @Override
    public BeerDto save(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());
        return beerMapper.BeerToBeerDto(beerRepository.save(beer));
    }

    //The cache condition is going to occur in the incident that this happened.
    @Override
    @Cacheable(cacheNames = "beerListCache", condition="#showInventoryOnHand == false")
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {

        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search both
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
            //search beer_service name
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search beer_service style
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        if(showInventoryOnHand){
            beerPagedList = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::BeerToBeerDto)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(beerPage.getPageable().getPageNumber(),
                                    beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
        } else{
            beerPagedList = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::BeerToBeerDtoNoInventory)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(beerPage.getPageable().getPageNumber(),
                                    beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
        }

        return beerPagedList;
    }

    @Override
    @Cacheable(cacheNames = "beerUpcCache", key = "#upc")
    public BeerDto getByUPC(String upc,  boolean showInventoryOnHand) {
        Beer beer = beerRepository.findByUpc(upc).orElseThrow(NotFoundException::new);
        if(showInventoryOnHand){
            return beerMapper.BeerToBeerDto(beer);
        } else {
            return beerMapper.BeerToBeerDtoNoInventory(beer);
        }

    }
}
