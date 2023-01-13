package com.programmer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programmer.dto.ProductRequest;
import com.programmer.dto.ProductResponse;
import com.programmer.repository.ProductRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest @Testcontainers @AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
	@Autowired
	private MockMvc mockMvc ;
	@Autowired
	private ObjectMapper objectMapper ;
	@Autowired
	private ProductRepository productRepository ;

	@DynamicPropertySource
	static  void  setProperties(DynamicPropertyRegistry dynamicPropertyRegistry)
	{
		dynamicPropertyRegistry.add("spring.data.mongodb.uri" , mongoDBContainer::getReplicaSetUrl);
	}
	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest = getProductRequest();
		String productResponseString = objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productResponseString))
				.andExpect(status().isCreated());
		Assert.assertEquals(1,productRepository.findAll().size());
	}

	private ProductRequest getProductRequest() {
		return  ProductRequest.builder()
				.name("Vivo V15")
				.decription("Electronics")
				.price(BigDecimal.valueOf(23000))
				.build();
	}

	@Test
	void shouldGetAllProducts() throws Exception
	{
		ProductRequest productRequest = getProductRequest();
		String productResponseString = objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productResponseString))
				.andExpect(status().isOk());
	}
}
