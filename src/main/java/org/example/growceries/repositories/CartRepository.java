package org.example.growceries.repositories;

import org.example.growceries.entities.CartEntity;
import org.example.growceries.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    Optional<CartEntity> findByName (String name);
    @Query(value = "SELECT p.* FROM product_entity p INNER JOIN cart_product c ON p.id = c.product_id WHERE c.cart_id = :cartId", nativeQuery = true)
    Optional<List<ProductEntity>> getProductEntitiesByCartId (@Param("cartId") long cartId);
}
