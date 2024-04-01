package org.example.growceries.controllers;

import org.example.growceries.models.ProductDTO;
import org.example.growceries.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductDTO> createProduct (@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(this.productService.createProduct(productDTO));
    }
    //Admin Only
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProduct (@PathVariable Long productId) {
        return ResponseEntity.ok(this.productService.getProduct(productId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct (@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(this.productService.updateProduct(productId, productDTO));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(this.productService.deleteProduct(productId));
    }
}
