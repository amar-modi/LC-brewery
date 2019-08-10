package com.amarmodi.beer.msscbeerservice.bootstrap;

import com.amarmodi.beer.msscbeerservice.domain.Beer;
import com.amarmodi.beer.msscbeerservice.model.BeerDto;
import com.amarmodi.beer.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeer();
    }

    private void loadBeer() {
        if(beerRepository.count() != 0) return;
        Beer beer = Beer.builder()
                .beerName("Lagunitas")
                .beerStyle("IPA")
                .minOnHand(12)
                .quantityToBrew(200)
                .upc(BEER_1_UPC)
                .price(new BigDecimal("12.95"))
                .build();

        Beer beer2 = Beer.builder()
                .beerName("Mad Elf")
                .beerStyle("IPA")
                .minOnHand(5)
                .quantityToBrew(100)
                .upc(BEER_2_UPC)
                .price(new BigDecimal("15.95"))
                .build();

        Beer beer3 = Beer.builder()
                .beerName("No Hammers on the Bar")
                .beerStyle("PALE_ALE")
                .minOnHand(5)
                .quantityToBrew(100)
                .upc(BEER_3_UPC)
                .price(new BigDecimal("15.95"))
                .build();

        this.beerRepository.save(beer);
        this.beerRepository.save(beer2);
        this.beerRepository.save(beer3);
    }
}
