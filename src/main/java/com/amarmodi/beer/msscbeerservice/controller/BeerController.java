package com.amarmodi.beer.msscbeerservice.controller;

import com.amarmodi.beer.msscbeerservice.model.BeerDto;
import com.amarmodi.beer.msscbeerservice.model.BeerPagedList;
import com.amarmodi.beer.msscbeerservice.model.BeerStyleEnum;
import com.amarmodi.beer.msscbeerservice.services.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
@Slf4j
@RequiredArgsConstructor
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final BeerService beerService;

    @GetMapping(produces = { "application/json" }, path = "beer")
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "beerName", required = false) String beerName,
                                                   @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
                                                   @RequestParam(required= false, defaultValue = "false") Boolean showInventoryOnHand ){


        if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        BeerPagedList beerList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);

        return new ResponseEntity<>(beerList, HttpStatus.OK);
    }

    @GetMapping("beer/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId")UUID beerId,
                                               @RequestParam(required = false, defaultValue = "false") Boolean showInventoryOnHand){
        return new ResponseEntity<>(beerService.getById(beerId, showInventoryOnHand), HttpStatus.OK);
    }


    @GetMapping("beerUpc/{upc}")
    public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable("upc") String upc,
                                                @RequestParam(required =false, defaultValue = "false") Boolean showInventoryOnHand){
        return new ResponseEntity<>(beerService.getByUPC(upc,showInventoryOnHand ), HttpStatus.OK);
    }

    @PostMapping("beer")
    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto){
        return new ResponseEntity(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);
    }

    @PutMapping("beer/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto){
        return new ResponseEntity(beerService.save(beerId, beerDto), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("beer/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId){

        log.error("Deleting the beer" + beerId.toString());
    }
}
