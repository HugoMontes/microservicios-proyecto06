package com.mcsv.cliente;

import com.mcsv.cliente.entity.Cliente;
import com.mcsv.cliente.repository.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class ClienteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienteServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ClienteRepository clienteRepository){
		return args -> {
			// Guardar clientes
			clienteRepository.saveAll(List.of(
					Cliente.builder().nombre("Christian").email("c1@gmail.com").build(),
					Cliente.builder().nombre("Julen").email("j1@gmail.com").build(),
					Cliente.builder().nombre("Biaggio").email("b1@gmail.com").build()
			));
			// Retornar listado de clientes
			clienteRepository.findAll().forEach(System.out::println);
		};
	}

}
