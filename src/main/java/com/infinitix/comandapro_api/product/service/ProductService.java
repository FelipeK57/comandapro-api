package com.infinitix.comandapro_api.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infinitix.comandapro_api.product.dto.CreateProductRequest;
import com.infinitix.comandapro_api.product.entitiy.Product;
import com.infinitix.comandapro_api.product.repository.ProductRepository;
import com.infinitix.comandapro_api.restaurant.entity.Restaurant;
import com.infinitix.comandapro_api.restaurant.repository.RestaurantRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private RestaurantRepository restaurantRepository;

    //crear producto desde DTO
    public Product createProduct(CreateProductRequest request) {
        // Buscar el restaurante
        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
            .orElseThrow(() -> new IllegalArgumentException("Restaurante no encontrado con ID: " + request.getRestaurantId()));
        
        // Crear el producto
        Product product = new Product();
        product.setRestaurant(restaurant);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setImageUrl(request.getImageUrl());
        product.setAvailable(request.isAvailable());
        
        return productRepository.save(product);
    }

    //crear producto (método original para compatibilidad)
    public Product saveProduct(Product product) {
        // Validar que los campos obligatorios estén presentes
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }
        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new IllegalArgumentException("El precio del producto debe ser mayor a 0");
        }
        if (product.getRestaurant() == null) {
            throw new IllegalArgumentException("El restaurante es obligatorio");
        }
        
        return productRepository.save(product);
    }

    //obtener producto por id
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    //obtener todos los productos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    //actualizar producto
    public Product updateProduct(Long id, Product product) {
        product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setName(product.getName());
            product.setDescription(product.getDescription());
            product.setPrice(product.getPrice());
            product.setAvailable(product.isAvailable());
            return productRepository.save(product);
        }
        return null;
    }

    //eliminar producto
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
