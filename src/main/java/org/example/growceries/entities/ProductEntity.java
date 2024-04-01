    package org.example.growceries.entities;

    import jakarta.persistence.*;
    import lombok.Getter;
    import org.example.growceries.models.ProductDTO;

    import java.util.Date;

    @Entity
    @Table(name = "products")
    @Getter
    public class ProductEntity {
        @Id
        @GeneratedValue (strategy = GenerationType.AUTO)
        private long id;

        @Column
        private String name;

        public void setPrice(double price) {
            this.price = price;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public void setMarket(String market) {
            this.market = market;
        }

        public void setMarketDistance(double marketDistance) {
            this.marketDistance = marketDistance;
        }

        //enum
        @Column
        private String category;

        @Column
        private double price;

        @Column
        private Date updatedAt;

        @Column
        private int discount;

        @Column
        private String manufacturer;

        @Column
        private String market;

        @Column
        private double marketDistance;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "cart_id")
        private CartEntity cartEntity;

        public ProductEntity() {

        }

        public ProductEntity(ProductDTO productDTO) {
            this.name = productDTO.getName();
            this.category = productDTO.getCategory();
            this.price = productDTO.getPrice();
            this.discount = productDTO.getDiscount();
            this.manufacturer = productDTO.getManufacturer();
            this.market = productDTO.getMarket();
            this.marketDistance = productDTO.getMarketDistance();
        }
    }
