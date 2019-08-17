package com.amarmodi.beer.msscbeerservice.bootstrap;

import com.amarmodi.beer.msscbeerservice.domain.Beer;
import com.amarmodi.beer.msscbeerservice.model.BeerDto;
import com.amarmodi.beer.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "234200036";
    public static final String BEER_2_UPC = "234300019";
    public static final String BEER_3_UPC = "783375213";
    public static final String BEER_4_UPC = "345676543";


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
                .createdDate(new Timestamp(new Date().getTime()))
                .lastModifiedDate(new Timestamp(new Date().getTime()))
                .build();

        Beer beer3 = Beer.builder()
                .beerName("No Hammers on the Bar")
                .beerStyle("PALE_ALE")
                .minOnHand(5)
                .quantityToBrew(100)
                .upc(BEER_3_UPC)
                .price(new BigDecimal("15.95"))
                .build();

        Beer beer4 = Beer.builder()
                .beerName("Mango Bobs")
                .beerStyle("IPA")
                .minOnHand(12)
                .quantityToBrew(200)
                .upc(BEER_4_UPC)
                .price(new BigDecimal("12.95"))
                .build();

        this.beerRepository.save(beer);
        this.beerRepository.save(beer2);
        this.beerRepository.save(beer3);
        this.beerRepository.save(beer4);
    }
}
