package com.Robert.project.model.repository;

import com.Robert.project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Find products by name containing a specific string
    List<Product> findByNameContaining(String name);
}