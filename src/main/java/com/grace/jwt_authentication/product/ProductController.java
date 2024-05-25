package com.grace.jwt_authentication.product;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<String> getProduct(){
        return ResponseEntity.ok("hello for this secured endpoind");
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
    }
}
