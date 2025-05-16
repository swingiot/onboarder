package com.swingiot.onboarder.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
  private final ProductsService productsService;

  public ProductsController(ProductsService productsService) {
    this.productsService = productsService;
  }

  @GetMapping
  public ResponseEntity<List<Product>> getProducts() {
    return ResponseEntity.ok(productsService.getAllProducts());
  }

  @PostMapping
  public ResponseEntity<Product> createProducts(@RequestBody Product product) {
    return productsService.getProduct(product.getName()).map(p -> {
      Product updated = Product.builder()
          .id(p.getId())
          .name(product.getName())
          .description(product.getDescription())
          .components(product.getComponents())
          .build();
      return ResponseEntity.status(HttpStatus.OK).body(productsService.updateProduct(updated));
    }).orElseGet(() -> ResponseEntity.status(HttpStatus.CREATED).body(productsService.createProduct(product)));
  }
}
