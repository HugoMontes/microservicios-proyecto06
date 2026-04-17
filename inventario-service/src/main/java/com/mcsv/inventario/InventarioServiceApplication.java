package com.mcsv.inventario;

import com.mcsv.inventario.entity.Producto;
import com.mcsv.inventario.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableDiscoveryClient
public class InventarioServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventarioServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductoRepository productoRepository) {
        return args -> {
            Random random = new Random();
            for (int i = 1; i < 10; i++) {
                productoRepository.saveAll(List.of(
                        Producto.builder()
                                .nombre("Computadora " + i)
                                .precio(1200 + Math.random() * 10000)
                                .cantidad(1 + random.nextInt(200)).build()
                ));
            }
        };
    }
}
