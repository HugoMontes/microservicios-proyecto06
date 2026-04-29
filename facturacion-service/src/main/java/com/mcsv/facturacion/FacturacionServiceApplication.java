package com.mcsv.facturacion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.vault.core.VaultTemplate;

import java.util.Map;

@SpringBootApplication
public class FacturacionServiceApplication {

	// VaultTemplate permite leer y escribir secretos en Vault
	private final VaultTemplate vaultTemplate;

	// Inyecta VaultTemplate automáticamente
	public FacturacionServiceApplication(VaultTemplate vaultTemplate) {
		this.vaultTemplate = vaultTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(FacturacionServiceApplication.class, args);
	}

	// Este metodo se ejecuta automáticamente al iniciar la app
	@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {
			// Escribe un secreto en Vault: "keypair" con dos campos (privKey y pubKey)
			vaultTemplate.opsForVersionedKeyValue("secret")
					.put("keypair", Map.of("privKey","54321","pubKey","8999"));
		};
	}

}
