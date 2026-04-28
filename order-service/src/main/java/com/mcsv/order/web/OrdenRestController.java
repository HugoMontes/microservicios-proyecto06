package com.mcsv.order.web;

import com.mcsv.order.entity.Orden;
import com.mcsv.order.model.Cliente;
import com.mcsv.order.model.Producto;
import com.mcsv.order.repository.OrdenRepository;
import com.mcsv.order.repository.ProductoItemRepository;
import com.mcsv.order.service.ClienteRestClientService;
import com.mcsv.order.service.InventarioRestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdenRestController {

    // Inyeccion de dependencias por constructor
    private final OrdenRepository ordenRepository;                           // Acceso a BD de órdenes
    private final ProductoItemRepository productoItemRepository;             // Acceso a BD de items
    private final ClienteRestClientService clienteRestClientService;         // Cliente Feign para cliente-service
    private final InventarioRestClientService inventarioRestClientService;   // Cliente Feign para inventario-service

    // Constructor (inyección de dependencias - buena práctica)
    public OrdenRestController(
            OrdenRepository ordenRepository,
            ProductoItemRepository productoItemRepository,
            ClienteRestClientService clienteRestClientService,
            InventarioRestClientService inventarioRestClientService) {
        this.ordenRepository = ordenRepository;
        this.productoItemRepository = productoItemRepository;
        this.clienteRestClientService = clienteRestClientService;
        this.inventarioRestClientService = inventarioRestClientService;
    }

    // ========== ENDPOINT PERSONALIZADO ==========
    // GET http://localhost:8083/fullOrden/{id}
    // Retorna una orden COMPLETA (con datos de cliente y productos)
    @GetMapping("/fullOrden/{id}")
    public Orden obtenerOrden(@PathVariable Long id) {
        // Obtener la orden desde la base de datos local
        Orden orden = ordenRepository.findById(id).get();
        // Añadimos a la orden los datos del cliente desde cliente-service
        Cliente cliente = clienteRestClientService.clienteById(orden.getClienteId());
        orden.setCliente(cliente);
        // Añadimos a la orden los datos del producto desde inventario-service
        orden.getProductoItems().forEach(pi -> {
            // Para cada item, obtiene el producto completo por su ID
            Producto producto = inventarioRestClientService.productoById(pi.getProductoId());
            pi.setProducto(producto); // Asigna el producto al campo @Transient del item
        });
        return orden;
    }
}
