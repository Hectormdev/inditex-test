package com.acidtango.technicaltest.api.v1;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acidtango.technicaltest.api.v1.dtos.PriceResponseDTO;
import com.acidtango.technicaltest.domain.Price;
import com.acidtango.technicaltest.services.PriceService;

@RestController
public class PriceController {

  @Autowired
  PriceService priceService;

  @GetMapping(value = "{brandId}/{productId}/price")
  public PriceResponseDTO getPrices(@PathVariable Long brandId,
      @PathVariable Long productId,
      @RequestParam @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss") String moment) {
    Optional<Price> price = priceService.getPrice(Timestamp.valueOf(moment), productId, brandId);
    if (!price.isPresent())
      throw new Error();
    return new PriceResponseDTO(price.get(), moment);
  }
}
