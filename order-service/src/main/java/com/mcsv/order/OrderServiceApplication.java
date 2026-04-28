package com.mcsv.order;

import com.mcsv.order.entity.Orden;
import com.mcsv.order.entity.ProductoItem;
import com.mcsv.order.enums.OrdenStatus;
import com.mcsv.order.model.Cliente;
import com.mcsv.order.model.Producto;
import com.mcsv.order.repository.OrdenRepository;
import com.mcsv.order.repository.ProductoItemRepository;
import com.mcsv.order.service.ClienteRestClientService;
import com.mcsv.order.service.InventarioRestClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients             // Escanea y crea implementaciones de clientes feign
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(OrdenRepository ordenRepository,
                            ProductoItemRepository productoItemRepository,
                            ClienteRestClientService clienteRestClientService,
                            InventarioRestClientService inventarioRestClientService) {
        return args -> {
            // Obtiene todos los clientes desde cliente-service (vía Feign)
            List<Cliente> clientes = clienteRestClientService.allClientes().getContent().stream().toList();
            // Obtiene todos los productos desde inventario-service (vía Feign)
            List<Producto> productos = inventarioRestClientService.allProductos().getContent().stream().toList();
            // Imprime todos los clientes (verificación)
            clientes.forEach(cliente -> {
                System.out.println("Cliente: " + cliente.getId() + " - " + cliente.getNombre() + " - " + cliente.getEmail());
            });
            // Imprime todos los productos (verificación)
            productos.forEach(producto -> {
                System.out.println("Producto: " + producto.getId() + " - " + producto.getNombre()
                        + " - Precio: " + producto.getPrecio());
            });
            // Generar datos de prueba aleatorios
            Random random = new Random();
            // Crear 20 ordenes de prueba
            for (int i = 0; i < 20; i++) {
                Orden orden = Orden.builder()
                        .clienteId(clientes.get(random.nextInt(clientes.size())).getId())
                        .ordenStatus(Math.random() > 0.5 ? OrdenStatus.PENDIENTE : OrdenStatus.CREADO)
                        .fechaCreacion(new Date())
                        .build();
                // Guardar la orden en la base de datos
                Orden ordenGuardada = ordenRepository.save(orden);
                // Agregar items de productos a la orden
                for (int j = 0; j < productos.size(); j++) {
                    if (Math.random() > 0.70) {
                        ProductoItem productoItem = ProductoItem.builder()
                                .orden(ordenGuardada)                    // Relación con la orden
                                .productoId(productos.get(j).getId())    // ID del producto
                                .precio(productos.get(j).getPrecio())    // Precio actual del producto
                                .cantidad(1 + random.nextInt(10))  // Cantidad aleatoria (1-10)
                                .descuento(Math.random())                // Descuento aleatorio (0-1)
                                .build();
                        productoItemRepository.save(productoItem);
                    }
                }
            }
            // Mensaje de confirmación
            System.out.println("=== Datos de prueba generados correctamente ===");
        };
    }
}
