package com.mcsv.inventario.entity;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullProducto",types = Producto.class)
public interface ProductoProjection {
    Long getId();
    String getNombre();
    double getPrecio();
    int getCantidad();
}
