package com.mcsv.facturacion.web;

import com.mcsv.facturacion.model.MyConsultConfig;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope           // Cargar las propiedades sin necesidad de reiniciar el servicio
public class MyConsultConfigController {

    private MyConsultConfig myConsultConfig;

    public MyConsultConfigController(MyConsultConfig myConsultConfig){
        this.myConsultConfig = myConsultConfig;
    }

    @GetMapping("/myconsultconfig")
    public MyConsultConfig myConfig(){
        return myConsultConfig;
    }
}
