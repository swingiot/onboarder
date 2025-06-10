package com.swingiot.onboarder.product;

import com.swingiot.onboarder.exception.InvalidRequestException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
  private final ProductsRepository productsRepository;

  public ProductsService(ProductsRepository productsRepository) {
    this.productsRepository = productsRepository;
  }

  public List<Product> getAllProducts() {
    return productsRepository.findAll();
  }

  public Optional<Product> getProduct(String productName) {
    return productsRepository.getProductByName(productName);
  }

  public Product createProduct(Product product) {
    if (!StringUtils.isEmpty(product.getId())) {
      throw new InvalidRequestException("ProductId already exists: " + product.getId());
    }
    return productsRepository.save(product);
  }

  public Product updateProduct(Product product) {
    if (StringUtils.isEmpty(product.getId())) {
      throw new InvalidRequestException("ProductId could not be empty");
    }
    return productsRepository.save(product);
  }

  public void deleteProduct(Product product) {
    productsRepository.delete(product);
  }
}
