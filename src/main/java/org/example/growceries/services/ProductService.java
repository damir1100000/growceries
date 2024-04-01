package org.example.growceries.services;

import org.example.growceries.models.ProductDTO;
import org.example.growceries.entities.ProductEntity;
import org.example.growceries.repositories.CartRepository;
import org.example.growceries.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;

    public ProductDTO createProduct (ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity(productDTO);
        this.discounter(productEntity, productDTO);
        this.productRepository.save(productEntity);
        return productDTO;
    }

    public ProductDTO getProduct (long id) {
        this.verifyPresence(id);
        return new ProductDTO(this.productRepository.findById(id).get());
    }

    public ProductDTO updateProduct (long productId, ProductDTO productDTO) {
        this.verifyPresence(productId);
        ProductEntity productEntity = this.productRepository.findById(productId).get();
        productEntity.setPrice(productDTO.getPrice());
        this.discounter(productEntity, productDTO);
        productEntity.setMarket(productDTO.getMarket());
        productEntity.setMarketDistance(productDTO.getMarketDistance());
        productEntity.setDiscount(productDTO.getDiscount());
        this.productRepository.save(productEntity);
        return new ProductDTO(productEntity);
    }

    public Boolean deleteProduct (long productId) {
        this.verifyPresence(productId);
        this.productRepository.deleteById(productId);
        return true;
    }

    public boolean verifyPresence (long id) {
        if(this.productRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Data Exception: Product with id = " + id + " has not been found");
        }
        return this.productRepository.findById(id).isPresent();
    }

    public void discounter (ProductEntity productEntity, ProductDTO productDTO) {
        if(productDTO.getDiscount() > 0) {
            productEntity.setDiscount(productEntity.getDiscount());
        }
        productEntity.setDiscount(0);
    };
}
