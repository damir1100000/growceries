package org.example.growceries.models;

import lombok.*;
import org.example.growceries.entities.CartEntity;
import org.springframework.lang.NonNull;

import java.sql.Timestamp;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CartDTO {
    @NonNull
    private String name;
    private double totalPrice;
    private List<ProductDTO> products;

    public CartDTO(String cartName) {
        this.name = cartName;
    }

    public CartDTO(CartEntity cartEntity) {
        this.name = cartEntity.getName();
        this.totalPrice = cartEntity.getTotalPrice();
    }
}
