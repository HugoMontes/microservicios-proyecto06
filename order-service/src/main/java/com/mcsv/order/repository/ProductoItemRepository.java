package com.mcsv.order.repository;

import com.mcsv.order.entity.ProductoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductoItemRepository extends JpaRepository<ProductoItem, Long> {
}