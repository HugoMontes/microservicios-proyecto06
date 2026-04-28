package com.mcsv.facturacion.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope           // Cargar las propiedades sin necesidad de reiniciar el servicio
public class ConsulConfigRestController {

    @Value("${token.accessTokenTimeout}")  // Inyectar el valor desde Consul KV
    private long accessTokenTimeout;

    @Value("${token.refreshTokenTimeout}") // Inyectar el valor desde Consul KV
    private long refreshTokenTimeout;

    @GetMapping("/myConfig")
    public Map<String, Object> myConfig(){
        // Retornar los valores
        return Map.of(
                "accessTokenTimeout", accessTokenTimeout,
                "refreshTokenTimeout", refreshTokenTimeout
        );
    }
}
