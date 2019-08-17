package com.amarmodi.beer.msscbeerservice.services.brewing;

import com.amarmodi.beer.msscbeerservice.config.JmsConfiguration;
import com.amarmodi.beer.msscbeerservice.domain.Beer;
import com.amarmodi.beer.msscbeerservice.event.BeerInventoryEvent;
import com.amarmodi.beer.msscbeerservice.event.BrewBeerEvent;
import com.amarmodi.beer.msscbeerservice.model.BeerDto;
import com.amarmodi.beer.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfiguration.BREWING_QUEUE)
    public void listen( BrewBeerEvent brewBeerEvent){
        BeerDto beerDto = brewBeerEvent.getBeerDto();
        log.info("The beer received for new inventory is {}", beerDto);
        Beer beer = beerRepository.getOne(beerDto.getId());
        /**
         * This is how we are brewing.
         */
        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        BeerInventoryEvent beerInventoryEvent = new BeerInventoryEvent(beerDto);
        log.info("The beer event for new inventory is ", beerInventoryEvent);
        jmsTemplate.convertAndSend(JmsConfiguration.NEW_INVENTORY_QUEUE, beerInventoryEvent);
    }

}
