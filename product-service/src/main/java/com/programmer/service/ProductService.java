package com.programmer.service;

import com.programmer.dto.ProductRequest;
import com.programmer.dto.ProductResponse;
import com.programmer.model.Product;
import com.programmer.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor @Slf4j
public class ProductService {

    private final ProductRepository productRepository ;

    // create product
    public void createProduct(ProductRequest productRequest)
    {
        Product product = Product.builder()
                .name(productRequest.getName())
                .decription(productRequest.getDecription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} is saved " , product.getId());
    }

    // get the list of products
    public List<ProductResponse> getAllProducts()
    {
        List<Product> products =
                productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .decription(product.getDecription())
                .price(product.getPrice())
                .build();
    }

}
