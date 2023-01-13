package com.developer.repository;

import com.developer.model.Inventory;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory , Long> {
    Optional<Inventory> findBySkuCode(String skuCode);
}
