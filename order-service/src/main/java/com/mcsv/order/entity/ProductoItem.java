package com.mcsv.order.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mcsv.order.model.Producto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productoId;

    @Transient
    private Producto producto;

    private double precio;

    private int cantidad;

    private double descuento;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Orden orden;
}