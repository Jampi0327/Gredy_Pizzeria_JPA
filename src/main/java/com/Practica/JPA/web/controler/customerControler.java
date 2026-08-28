package com.Practica.JPA.web.controler;

import com.Practica.JPA.persistence.entity.CustomerEntity;
import com.Practica.JPA.persistence.entity.OrderEntity;
import com.Practica.JPA.service.OrderService;
import com.Practica.JPA.service.customerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Clientes", description = "Operaciones de consulta y administración de clientes")
public class customerControler {

    private final customerService customerService;
    private final OrderService orderService;

    @Autowired
    public customerControler(customerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping("/phone/{phone}")
    @Operation(summary = "Buscar cliente por teléfono", description = "Consulta los datos de un cliente a través de su número de teléfono único")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<CustomerEntity> getByPhone(
            @Parameter(description = "Número telefónico del cliente", example = "3155551234") @PathVariable String phone) {
        return ResponseEntity.ok(this.customerService.findByPhone(phone));
    }
    @GetMapping("/customers/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders( @PathVariable String id) {
        return ResponseEntity.ok(this.orderService.getCustomerOrders(id));
    }
}