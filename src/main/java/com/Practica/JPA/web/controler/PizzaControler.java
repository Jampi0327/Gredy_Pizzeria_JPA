package com.Practica.JPA.web.controler;

import com.Practica.JPA.persistence.entity.PizzaEntity;
import com.Practica.JPA.service.PizzaService;
import com.Practica.JPA.service.dto.updatePizzaPriceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
@Tag(name = "Pizzas", description = "Operaciones de administración y consulta del menú de pizzas")
public class PizzaControler {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaControler(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    @Operation(summary = "Listar todas las pizzas", description = "Obtiene una página con el listado completo de pizzas registradas")
    @ApiResponse(responseCode = "200", description = "Página de pizzas obtenida exitosamente")
    public ResponseEntity<Page<PizzaEntity>> getAll(
            @Parameter(description = "Número de página (inicia en 0)", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Cantidad de elementos por página", example = "8") @RequestParam(defaultValue = "8") int elements) {
        return ResponseEntity.ok(this.pizzaService.getAll(page, elements));
    }

    @GetMapping("/{idPizza}")
    @Operation(summary = "Buscar pizza por ID", description = "Recupera los datos de una pizza específica mediante su identificador único")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pizza encontrada"),
            @ApiResponse(responseCode = "404", description = "Pizza no encontrada")
    })
    public ResponseEntity<PizzaEntity> get(
            @Parameter(description = "ID único de la pizza", example = "1") @PathVariable int idPizza) {
        return ResponseEntity.ok(this.pizzaService.get(idPizza));
    }

    @GetMapping("/available")
    @CrossOrigin(origins = "http://localhost:4200")
    @Operation(summary = "Listar pizzas disponibles", description = "Obtiene pizzas con estado disponible aplicando paginación y ordenamiento dinámico")
    @ApiResponse(responseCode = "200", description = "Página de pizzas disponibles")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(
            @Parameter(description = "Número de página", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Elementos por página", example = "8") @RequestParam(defaultValue = "8") int elements,
            @Parameter(description = "Campo de ordenamiento", example = "price") @RequestParam(defaultValue = "price") String sortBy,
            @Parameter(description = "Dirección del orden (ASC o DESC)", example = "ASC") @RequestParam(defaultValue = "ASC") String sortDirection) {
        return ResponseEntity.ok(this.pizzaService.getAvailable(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Buscar pizza por nombre", description = "Obtiene una pizza a partir de su nombre exacto")
    @ApiResponse(responseCode = "200", description = "Pizza encontrada")
    public ResponseEntity<PizzaEntity> getByname(
            @Parameter(description = "Nombre exacto de la pizza", example = "Pepperoni") @PathVariable String name) {
        return ResponseEntity.ok(this.pizzaService.getByname(name));
    }

    @GetMapping("/with/{ingredient}")
    @Operation(summary = "Buscar pizzas con ingrediente", description = "Devuelve todas las pizzas que contienen el ingrediente especificado en su descripción")
    @ApiResponse(responseCode = "200", description = "Lista de pizzas que contienen el ingrediente")
    public ResponseEntity<List<PizzaEntity>> getWith(
            @Parameter(description = "Nombre del ingrediente", example = "Mozzarella") @PathVariable String ingredient) {
        return ResponseEntity.ok(this.pizzaService.getWith(ingredient));
    }

    @GetMapping("/without/{ingredient}")
    @Operation(summary = "Buscar pizzas sin ingrediente", description = "Devuelve todas las pizzas que no contienen el ingrediente especificado")
    @ApiResponse(responseCode = "200", description = "Lista de pizzas sin el ingrediente")
    public ResponseEntity<List<PizzaEntity>> getWithout(
            @Parameter(description = "Nombre del ingrediente a excluir", example = "Pineapple") @PathVariable String ingredient) {
        return ResponseEntity.ok(this.pizzaService.getWithout(ingredient));
    }

    @GetMapping("/cheapest/{price}")
    @Operation(summary = "Buscar pizzas más baratas", description = "Lista pizzas con precio menor o igual al valor indicado")
    @ApiResponse(responseCode = "200", description = "Lista de pizzas filtradas por precio tope")
    public ResponseEntity<List<PizzaEntity>> getCheapes(
            @Parameter(description = "Precio tope de búsqueda", example = "20.00") @PathVariable Double price) {
        return ResponseEntity.ok(this.pizzaService.getCheapest(price));
    }

    @PostMapping
    @Operation(summary = "Crear nueva pizza", description = "Registra una nueva pizza en la base de datos verificando que el ID no exista previamente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pizza creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "El ID ya existe o la estructura es inválida")
    })
    public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizza) {
        if (pizza.getId() == null || !this.pizzaService.exists(pizza.getId())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    @Operation(summary = "Actualizar pizza", description = "Actualiza la información general de una pizza existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pizza actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "El ID de la pizza no existe")
    })
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza) {
        if (pizza.getId() != null && this.pizzaService.exists(pizza.getId())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/price")
    @Operation(summary = "Actualizar precio de pizza", description = "Modifica exclusivamente el precio de una pizza mediante consulta directa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Precio actualizado"),
            @ApiResponse(responseCode = "400", description = "El ID de la pizza no existe")
    })
    public ResponseEntity<Void> updatePrice(@RequestBody updatePizzaPriceDto dto) {
        if (this.pizzaService.exists(dto.getPizzaId())) {
            this.pizzaService.updatePrice(dto);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    @Operation(summary = "Eliminar pizza", description = "Elimina permanentemente una pizza del menú a partir de su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pizza eliminada"),
            @ApiResponse(responseCode = "400", description = "La pizza no existe")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID de la pizza a eliminar", example = "1") @PathVariable int idPizza) {
        if (this.pizzaService.exists(idPizza)) {
            this.pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}