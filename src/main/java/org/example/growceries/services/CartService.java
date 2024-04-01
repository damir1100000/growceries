package org.example.growceries.services;

import org.example.growceries.entities.CartEntity;
import org.example.growceries.entities.ProductEntity;
import org.example.growceries.entities.UserEntity;
import org.example.growceries.models.CartDTO;
import org.example.growceries.models.ProductDTO;
import org.example.growceries.repositories.CartRepository;
import org.example.growceries.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;

    public List<CartDTO> getAllCarts (Long userId) {
        List<CartDTO> cartDTOS = new ArrayList<>();
        List<CartEntity> cartEntities = this.cartRepository.findAll();
        for(CartEntity cartEntity : cartEntities) {
            cartDTOS.add(new CartDTO(cartEntity));
        }
        return cartDTOS;
    }

    public CartDTO createCart (String cartName, Long userId) {
        Optional<UserEntity> userEntityOpt = this.userRepository.findById(userId);
        if(userEntityOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        CartEntity cartEntity = new CartEntity(cartName, userEntityOpt.get());
        this.cartRepository.save(cartEntity);
        return new CartDTO(cartEntity);
    }

    public Set<ProductDTO> getCartProducts (Long cartId, Long userId) {
        Set<ProductDTO> productDTOS = new HashSet<>();
        Optional<List<ProductEntity>> productEntitiesOpt = this.cartRepository.getProductEntitiesByCartId(cartId);
        if(productEntitiesOpt.isEmpty()) {
            throw new RuntimeException("Product entities couldn't be found");
        }
        for(ProductEntity entity : productEntitiesOpt.get()) {
            productDTOS.add(new ProductDTO(entity));
        }
        return productDTOS;
    }

    public Boolean deleteCartById (Long cartId, Long userId) {
        CartEntity entity = this.verifyAndReturn(cartId, userId);
        this.cartRepository.delete(entity);
        return true;
    }

    public CartDTO updateCart (CartDTO cartDTO, Long userId) {
        CartEntity cartEntity = this.verifyAndReturn(cartDTO.getName(), userId);
        cartEntity.setName(cartDTO.getName());

        Set<ProductEntity> productEntities = new HashSet<>();
        for(ProductDTO productDTO : cartDTO.getProducts()) {
            productEntities.add(new ProductEntity(productDTO));
        }

        cartEntity.setProductEntities(productEntities);
        return new CartDTO(cartEntity);
    }

    public CartEntity verifyAndReturn (Long cartId, Long userId) {
        Optional<CartEntity> cartEntityOpt = this.cartRepository.findById(cartId);

        if (cartEntityOpt.isEmpty()) {
            throw new RuntimeException("Cart with id = " + cartId + " has not been found");
        }
        CartEntity cartEntity = cartEntityOpt.get();

        if(cartEntity.getUserEntity().getId() != userId) {
            throw new RuntimeException("Unauthorized. You are not allowed to do this operation");
        }

        return cartEntity;
    }

    public CartEntity verifyAndReturn (String cartName, Long userId) {
        Optional<CartEntity> cartEntityOpt = this.cartRepository.findByName(cartName);

        if (cartEntityOpt.isEmpty()) {
            throw new RuntimeException("Cart with name = " + cartName + " has not been found");
        }
        CartEntity cartEntity = cartEntityOpt.get();

        if(cartEntity.getUserEntity().getId() != userId) {
            throw new RuntimeException("Unauthorized. You are not allowed to do this operation");
        }

        return cartEntity;
    }
}
