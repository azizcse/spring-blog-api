package com.example.simplecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.simplecrud.model.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}