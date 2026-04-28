package com.mcsv.facturacion.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "token")
@Data
public class MyConsultConfig {
    private long accessTokenTimeout;
    private long refreshTokenTimeout;
}
