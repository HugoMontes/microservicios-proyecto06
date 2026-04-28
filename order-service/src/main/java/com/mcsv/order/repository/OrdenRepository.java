package com.mcsv.order.repository;

import com.mcsv.order.entity.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "ordenes", path = "ordenes")
public interface OrdenRepository extends JpaRepository<Orden, Long> {
}