package com.example.online_store.repository;

import com.example.online_store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE CONCAT(p.name, ' ', p.brand, ' ', p.madeIn, ' ', p.price) LIKE %?1%")
    public List<Product> search(String keyword);
}
