package com.mcsv.order.entity;

import com.mcsv.order.enums.OrdenStatus;
import com.mcsv.order.model.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ordenes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fechaCreacion;

    private OrdenStatus ordenStatus;

    private Long clienteId;

    @Transient
    private Cliente cliente;

    @OneToMany(mappedBy = "orden")
    private List<ProductoItem> productoItems;
}