package com.acidtango.technicaltest.services;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acidtango.technicaltest.domain.Price;
import com.acidtango.technicaltest.repositories.PriceRepository;

@Service
public class PriceService {

  @Autowired
  private PriceRepository priceRepository;

  public Optional<Price> getPrice(Timestamp moment, Long productId, Long brandId) {
    List<Price> prices = priceRepository.getPriceInRange(productId, brandId, moment);
    Optional<Price> selectedPrice = prices.stream().max(Comparator.comparing(e -> e.getPriority()));
    return selectedPrice;

  };

}
