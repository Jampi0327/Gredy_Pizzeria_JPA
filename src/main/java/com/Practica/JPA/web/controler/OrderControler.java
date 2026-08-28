package com.Practica.JPA.web.controler;

import com.Practica.JPA.persistence.entity.OrderEntity;
import com.Practica.JPA.persistence.projection.OrderSumary;
import com.Practica.JPA.service.OrderService;
import com.Practica.JPA.service.dto.RandonOrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Órdenes", description = "Operaciones de gestión, resúmenes y registro de órdenes")
public class OrderControler {

    private final OrderService orderService;

    @Autowired
    public OrderControler(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @Operation(summary = "Listar todas las órdenes", description = "Recupera la lista histórica de todas las órdenes registradas")
    @ApiResponse(responseCode = "200", description = "Lista de órdenes recuperada")
    public ResponseEntity<List<OrderEntity>> getAll() {
        return ResponseEntity.ok(this.orderService.getAll());
    }

    @GetMapping("/today")
    @Operation(summary = "Órdenes de hoy", description = "Lista las órdenes emitidas durante la fecha actual")
    @ApiResponse(responseCode = "200", description = "Lista de órdenes del día")
    public ResponseEntity<List<OrderEntity>> getTodayOrders() {
        return ResponseEntity.ok(this.orderService.getTodayOrders());
    }

    @GetMapping("/outside")
    @Operation(summary = "Órdenes para entrega fuera del local", description = "Obtiene las órdenes con métodos de entrega 'D' (Delivery) o 'C' (Carryout)")
    @ApiResponse(responseCode = "200", description = "Lista de órdenes fuera de local")
    public ResponseEntity<List<OrderEntity>> getOutsideOrders() {
        return ResponseEntity.ok(this.orderService.getOutsideOrders());
    }

    @GetMapping("/customers/{id}")
    @Operation(summary = "Órdenes por cliente", description = "Lista todas las órdenes asociadas al número de identificación de un cliente")
    @ApiResponse(responseCode = "200", description = "Historial de órdenes del cliente")
    public ResponseEntity<List<OrderEntity>> getOutsideOrders(
            @Parameter(description = "Identificación del cliente", example = "863264988") @PathVariable String id) {
        return ResponseEntity.ok(this.orderService.getCustomerOrders(id));
    }

    @GetMapping("/summary/{id}")
    @Operation(summary = "Resumen de orden (Proyección)", description = "Obtiene el resumen consolidado de la orden con cálculo de ítems usando una proyección SQL nativa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resumen de la orden generado"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada")
    })
    public ResponseEntity<OrderSumary> getSummary(
            @Parameter(description = "ID de la orden", example = "1") @PathVariable int id) {
        return ResponseEntity.ok(this.orderService.getSumary(id));
    }

    @PostMapping("/randow")
    @Operation(summary = "Crear orden aleatoria con descuento", description = "Ejecuta el Stored Procedure `take_random_pizza_order` para asignar una pizza aleatoria con 20% de descuento")
    @ApiResponse(responseCode = "200", description = "Resultado booleano de la ejecución del procedimiento")
    public ResponseEntity<Boolean> randomOrder(@RequestBody RandonOrderDto dto) {
        return ResponseEntity.ok(this.orderService.saveRandorOrder(dto));
    }
}