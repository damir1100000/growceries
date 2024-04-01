package org.example.growceries.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Getter
@Table(name = "carts")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;
    @CreationTimestamp
    private Timestamp createdOn;
    @Column
    private double totalPrice;
    @OneToMany(mappedBy = "cartEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductEntity> productEntities;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public void setName(String name) {
        this.name = name;
    }

    public void updateTotalPrice (double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setProductEntities (Set<ProductEntity> updatedCart) {
        this.productEntities = updatedCart;
    }

    public CartEntity() {

    }
    public CartEntity(String cartName, UserEntity userEntity) {
        this.name = cartName;
        this.userEntity = userEntity;
    }
}
