package org.example.growceries.controllers;

import org.example.growceries.models.CartDTO;
import org.example.growceries.models.ProductDTO;
import org.example.growceries.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_USER') and #userId == authentication.principal.id")
    private ResponseEntity<CartDTO> createCart (@RequestBody String name, @PathVariable Long userId) {
        return ResponseEntity.ok(this.cartService.createCart(name, userId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartDTO>> getAllShoppingCarts (@PathVariable Long userId) {
        return ResponseEntity.ok(this.cartService.getAllCarts(userId));
    }

    @GetMapping("/{userId}/{cartId}")
    public ResponseEntity<Set<ProductDTO>> getCartProducts (@PathVariable Long userId, @PathVariable Long cartId) {
        return ResponseEntity.ok(this.cartService.getCartProducts(userId, cartId));
    }

    @PutMapping("/{userId}/{cartId}")
    public ResponseEntity<CartDTO> updateCartById (@RequestBody CartDTO cartDTO, Long userId) {
        return ResponseEntity.ok(this.cartService.updateCart(cartDTO, userId));
    }

    @DeleteMapping("/{userId}/{cartId}")
    public ResponseEntity<Boolean> deleteCartById (@PathVariable Long userId, Long cartId) {
        return ResponseEntity.ok(this.cartService.deleteCartById(userId, cartId));
    }
}
