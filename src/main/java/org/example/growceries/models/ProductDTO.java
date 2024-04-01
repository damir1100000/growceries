package org.example.growceries.models;

import lombok.*;
import org.example.growceries.entities.ProductEntity;

import java.sql.Timestamp;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductDTO {
    private String name;
    private String category;
    private double price;
    private Timestamp updatedAt;
    private int discount;
    private String manufacturer;
    private String market;
    private double marketDistance;
    private CartDTO cartDTO;

    public ProductDTO (ProductEntity productEntity) {
        this.name = productEntity.getName();
        this.category = productEntity.getCategory();
        this.price = productEntity.getPrice();
        this.discount = productEntity.getDiscount();
        this.manufacturer = productEntity.getManufacturer();
        this.market = productEntity.getMarket();
        this.marketDistance = productEntity.getMarketDistance();
    }
}
