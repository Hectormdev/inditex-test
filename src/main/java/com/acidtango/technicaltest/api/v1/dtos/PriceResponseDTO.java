package com.acidtango.technicaltest.api.v1.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

import com.acidtango.technicaltest.domain.Price;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceResponseDTO implements Serializable {
  private Long PriceId;
  private Long productId;
  private Long brandId;
  private Timestamp startDate;
  private Timestamp endDate;
  private Double cost;
  private String currency;

  public PriceResponseDTO(Price price, String moment) {
    this.PriceId = price.getPriceId();
    this.productId = price.getProductId();
    this.brandId = price.getBrandId();
    this.startDate = price.getStartDate();
    this.endDate = price.getEndDate();
    this.cost = price.getCost();
    this.currency = price.getCurrency();
  }

}
