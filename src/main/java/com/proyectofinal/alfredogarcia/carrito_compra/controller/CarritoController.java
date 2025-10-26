package com.proyectofinal.alfredogarcia.carrito_compra.controller;

import com.proyectofinal.alfredogarcia.carrito_compra.entity.CarritoItem;
import com.proyectofinal.alfredogarcia.carrito_compra.service.carrito.CarritoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar los endpoints del carrito de compras.
 */
@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    /**
     * Agregar un producto al carrito de un usuario.
     */
    @PostMapping("/agregar")
    public ResponseEntity<CarritoItem> agregarProducto(
            @RequestParam String codUsuario,
            @RequestParam Long idProducto,
            @RequestParam int cantidad) {
        CarritoItem item = carritoService.agregarProducto(codUsuario, idProducto, cantidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    /**
     * Listar los productos del carrito de un usuario.
     * Ejemplo: GET /api/carrito/alfredo
     */
    @GetMapping("/{codUsuario}")
    public ResponseEntity<List<CarritoItem>> listarPorUsuario(@PathVariable String codUsuario) {
        List<CarritoItem> items = carritoService.listarPorUsuario(codUsuario);
        return ResponseEntity.ok(items);
    }

    /**
     * Eliminar un ítem del carrito por su ID.
     * Ejemplo: DELETE /api/carrito/item/5
     */
    @DeleteMapping("/item/{idShoppingCart}")
    public ResponseEntity<Void> eliminarItem(@PathVariable Long idShoppingCart) {
        carritoService.eliminarItem(idShoppingCart);
        return ResponseEntity.noContent().build();
    }

    /**
     * Vaciar el carrito de un usuario.
     * Ejemplo: DELETE /api/carrito/vaciar/alfredo
     */
    @DeleteMapping("/vaciar/{codUsuario}")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable String codUsuario) {
        carritoService.vaciarCarrito(codUsuario);
        return ResponseEntity.noContent().build();
    }
}
