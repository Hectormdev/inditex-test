package com.acidtango.technicaltest.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRICES")
public class Price {
  @Id
  private Long priceId;

  private Long brandId;

  private Long productId;

  private Integer priority;

  private Double cost;

  private String currency;

  private Timestamp startDate;

  private Timestamp endDate;

}