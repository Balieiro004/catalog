package com.balieiro.Catalog.repositories;

import com.balieiro.Catalog.entities.Category;
import com.balieiro.Catalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
