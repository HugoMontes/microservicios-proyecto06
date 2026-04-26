package com.mcsv.cliente.entity;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullCliente",types = Cliente.class)
public interface ClienteProjection {
    Long getId();
    String getNombre();
    String getEmail();
}
