package com.mcsv.facturacion.web;

import com.mcsv.facturacion.model.MyConsultConfig;
import com.mcsv.facturacion.model.MyVaultConfig;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope           // Cargar las propiedades sin necesidad de reiniciar el servicio
public class MyConsultConfigController {

    private final MyConsultConfig myConsultConfig;
    private final MyVaultConfig myVaultConfig;

    public MyConsultConfigController(MyConsultConfig myConsultConfig, MyVaultConfig myVaultConfig) {
        this.myConsultConfig = myConsultConfig;
        this.myVaultConfig = myVaultConfig;
    }

    @GetMapping("/myconsultconfig")
    public MyConsultConfig myConfig() {
        return myConsultConfig;
    }

    @GetMapping("/myvaultconfig")
    public Map<String, Object> myVaultConfig() {
        return Map.of("consulConfig", myConsultConfig, "vaultConfig", myVaultConfig);
    }
}
