package com.acidtango.technicaltest.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.acidtango.technicaltest.domain.Price;

public interface PriceRepository extends Repository<Price, Long> {

  @Query("SELECT p FROM Price p WHERE p.productId = :productId AND p.brandId = :brandId AND :moment BETWEEN p.startDate AND p.endDate")
  public List<Price> getPriceInRange(Long productId, Long brandId, Timestamp moment);
}
