package com.mcsv.order.service;

import com.mcsv.order.model.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cliente-service")
public interface ClienteRestClientService {

    @GetMapping("/clientes/{id}?projection=fullCliente")
    public Cliente clienteById(@PathVariable Long id);

    @GetMapping("/clientes?projection=fullCliente")
    public PagedModel<Cliente> allClientes();
}

