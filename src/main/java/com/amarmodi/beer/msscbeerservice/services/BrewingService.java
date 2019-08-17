package com.amarmodi.beer.msscbeerservice.services;

import com.amarmodi.beer.msscbeerservice.config.JmsConfiguration;
import com.amarmodi.beer.msscbeerservice.domain.Beer;
import com.amarmodi.beer.msscbeerservice.event.BrewBeerEvent;
import com.amarmodi.beer.msscbeerservice.mappers.BeerMapper;
import com.amarmodi.beer.msscbeerservice.repositories.BeerRepository;
import com.amarmodi.beer.msscbeerservice.services.inventory.BeerInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000) // Every 5 seconds
    public void checkForLowInventory(){
        List<Beer> beers = beerRepository.findAll();

        beers.forEach(beer ->{
            Integer invQuantity =  beerInventoryService.getOnhandInventory(beer.getId());
            log.info("The amount of beer minimum on hand is " + beer.getMinOnHand());
            log.info("The amount of beer available is " + invQuantity);

            if(beer.getMinOnHand() >= invQuantity){
                jmsTemplate.convertAndSend(JmsConfiguration.BREWING_QUEUE, new BrewBeerEvent(beerMapper.BeerToBeerDto(beer)));
            }
        });
    }
}
