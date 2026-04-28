package com.mcsv.order.service;

import com.mcsv.order.model.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventario-service")
public interface InventarioRestClientService {

    @GetMapping("/productos/{id}?projection=fullProducto")
    public Producto productoById(@PathVariable Long id);

    @GetMapping("/productos?projection=fullProducto")
    public PagedModel<Producto> allProductos();
}
