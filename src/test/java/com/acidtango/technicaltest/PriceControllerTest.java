package com.acidtango.technicaltest;

import java.net.URI;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.acidtango.technicaltest.api.v1.dtos.PriceResponseDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PriceControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private Integer localPort;

	private Long brandId = 1l;
	private Long productId = 35455l;

	@Test
	void PriceTestAtThe14At10() {

		String moment = "2020-06-14 10:00:00";

		ResponseEntity<PriceResponseDTO> response = this.request(brandId, productId, moment);

		PriceResponseDTO priceDTO = response.getBody();
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(35.5, priceDTO.getCost());
		Assertions.assertEquals(1, priceDTO.getPriceId());

	}

	@Test
	void PriceTestAtThe14At16() {

		String moment = "2020-06-14 16:00:00";

		ResponseEntity<PriceResponseDTO> response = this.request(brandId, productId, moment);

		PriceResponseDTO priceDTO = response.getBody();
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(2, priceDTO.getPriceId());
		Assertions.assertEquals(25.45, priceDTO.getCost());

	}

	@Test
	void PriceTestAtThe14At21() {

		String moment = "2020-06-14 21:00:00";

		ResponseEntity<PriceResponseDTO> response = this.request(brandId, productId, moment);

		PriceResponseDTO priceDTO = response.getBody();
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(1, priceDTO.getPriceId());
		Assertions.assertEquals(35.5, priceDTO.getCost());

	}

	@Test
	void PriceTestAtThe15At10() {
		Long brandId = 1l;
		Long productId = 35455l;
		String moment = "2020-06-15 10:00:00";

		ResponseEntity<PriceResponseDTO> response = this.request(brandId, productId, moment);

		PriceResponseDTO priceDTO = response.getBody();
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(3, priceDTO.getPriceId());
		Assertions.assertEquals(30.5, priceDTO.getCost());
	}

	@Test
	void PriceTestAtThe16At21() {
		Long brandId = 1l;
		Long productId = 35455l;
		String moment = "2020-06-16 21:00:00";

		ResponseEntity<PriceResponseDTO> response = this.request(brandId, productId, moment);

		PriceResponseDTO priceDTO = response.getBody();
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(4, priceDTO.getPriceId());
		Assertions.assertEquals(38.95, priceDTO.getCost());

	}

	private ResponseEntity<PriceResponseDTO> request(Long brandId, Long productId, String moment) {
		URI uri = UriComponentsBuilder
				.fromHttpUrl("http://localhost:" + localPort + "/" + brandId + "/" + productId + "/price")
				.queryParam("moment", moment).build()
				.encode()
				.toUri();

		return testRestTemplate.exchange(uri, HttpMethod.GET, null, PriceResponseDTO.class);

	}
};
